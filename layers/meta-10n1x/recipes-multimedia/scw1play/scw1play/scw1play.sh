#!/bin/bash

hw_buffer=24000 # 32768 # 65536
wakeup_ns=125000

tmpdir="/tmp/playhrt"

cleanup() {
    touch $tmpdir/do_exit
    fuser -k -w $tmpdir/{unpack,repack,playhrt}_fifo
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

    audio_files=$(find "$path" -type f | grep -ie "flac\|mp3\|wv\|ogg" | sort -V)
    >&2 echo Playing $'\n'"$audio_files"

    IFS=$'\n'
    for file in $audio_files; do
	[ -f $tmpdir/do_exit ] && exit 0

	taskset -c 1,2 chrt -f 50 sox "$file" -t raw -e float -b 64 $tmpdir/unpack_fifo &

	pipe-size $tmpdir/unpack_fifo 2097152 & # 2^21 ~ 2mb

	taskset -c 1,2 chrt -f 60 resample_soxr -i $(sox --i -r "$file") -o 192000 \
	     <$tmpdir/unpack_fifo >$tmpdir/repack_fifo &

	pipe-size $tmpdir/repack_fifo 2097152 & # 2^21 ~ 2mb

	taskset -c 1,2 chrt -f 70 sox -t raw -e float -b 64 -r 192000 \
	     $tmpdir/repack_fifo -e signed -b 32 -t raw $tmpdir/playhrt_fifo
    done
done &

pipe-size $tmpdir/playhrt_fifo $((2**25)) & # 2^24 ~ 16 mb

taskset -c 3 chrt -f 99 playhrt < $tmpdir/playhrt_fifo \
     -v \
     -d "hw:0,0" \
     -n $wakeup_ns \
     -c $hw_buffer \
     -D 2000000
