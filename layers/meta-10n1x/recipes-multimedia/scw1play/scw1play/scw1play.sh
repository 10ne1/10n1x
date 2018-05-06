#!/bin/bash

audio_formats="flac\|mp3\|wv" #etc

channels=2
bitdepth=32
samplerate=192000
sampleformat=S32_LE

alsadevice=hw:0,0
hw_buffer=65536
loops_per_second=1500

tmpdir="/tmp/playhrt"

cleanup() {
    touch $tmpdir/do_exit
    pkill pipe-size
    pkill sox
    pkill scw1play.sh
}

trap 'cleanup >/dev/null 2>&1' EXIT

rm -rf $tmpdir && mkdir -p $tmpdir
mkfifo $tmpdir/{unpack,repack,playhrt}_fifo

for path in "$@"; do
    [ -f $tmpdir/do_exit ] && exit 0

    [ ! -d "$path" ] && [ ! -f "$path" ] && \
	echo "WARNING: '$path' does not exist" && \
	continue

    audio_files=$(find "$path" -type f | grep -ie "$audio_formats" | sort -V)
    echo Playing $'\n'"$audio_files"

    IFS=$'\n'
    for file in $audio_files; do
	[ -f $tmpdir/do_exit ] && exit 0

	sox "$file" -t raw -e float -b 64 $tmpdir/unpack_fifo &

	pipe-size $tmpdir/unpack_fifo 4194304 & # 2^22 ~ 4mb

	resample_soxr -i $(sox --i -r "$file") -o $samplerate \
	     <$tmpdir/unpack_fifo >$tmpdir/repack_fifo &

	pipe-size $tmpdir/repack_fifo 4194304 & # 2^22 ~ 4mb

	sox -t raw -e float -b 64 -c $channels -r $samplerate \
	     $tmpdir/repack_fifo -e signed -b 32 -t raw $tmpdir/playhrt_fifo
    done
done &

pipe-size $tmpdir/playhrt_fifo 268435456 & # 2^28 ~ 268mb

chrt -f 90 playhrt < $tmpdir/playhrt_fifo \
     -SMNvv \
     -d $alsadevice \
     -s $samplerate \
     -f $sampleformat \
     -n $loops_per_second \
     -c $hw_buffer \
     -D 4000000
