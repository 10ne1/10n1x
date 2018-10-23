#include <sys/types.h>
#include <getopt.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <alsa/asoundlib.h>
#include "cprefresh.h"

#define BYTESPERFRAME	8 /* 2 channels, 4 bytes per sample */
#define NRCHANNELS	2
#define	RATE		192000
#define PERIODSIZE	32
#define WAKEUP_NSEC	166667

int main(int argc, char *argv[])
{
	int i;
	long ilen, sleep, wakeup_nsec;
	void *iptr;
	struct timespec mtime, nowtime;
	snd_pcm_t *pcm_handle;
	snd_pcm_hw_params_t *hwparams;
	char *pcm_name, started = 0;
	snd_pcm_uframes_t hwbufsize, offset, frames;
	snd_pcm_sframes_t avail;
	const snd_pcm_channel_area_t *areas;

	hwbufsize = 320;
	pcm_name = NULL;
	sleep = 0;

	while ((i = getopt_long(argc, argv, "D:c:d:", 0, &optind)) != -1)
		switch (i) {
		case 'c':
			hwbufsize = atoi(optarg);
			break;
		case 'd':
			pcm_name = optarg;
			break;
		case 'D':
			sleep = atoi(optarg);
			break;
		default:
			fprintf(stderr, "ERROR: Unknown arg '%c'\n", i);
			exit(2);
		}

	if (!pcm_name) {
		fprintf(stderr, "ERROR: Must supply a pcm device using '-d' option\n");
		exit(2);
	}

	/* setup sound device */
	snd_pcm_hw_params_malloc(&hwparams);
	if (snd_pcm_open(&pcm_handle, pcm_name, SND_PCM_STREAM_PLAYBACK, 0) < 0) {
		fprintf(stderr, "playhrt: Error opening PCM device %s\n", pcm_name);
		exit(5);
	}
	if (snd_pcm_nonblock(pcm_handle, 1) < 0) {
		fprintf(stderr, "playhrt: Cannot set non-block mode.\n");
		exit(6);
	}
	if (snd_pcm_hw_params_any(pcm_handle, hwparams) < 0) {
		fprintf(stderr, "playhrt: Cannot configure this PCM device.\n");
		exit(7);
	}
	if (snd_pcm_hw_params_set_access(pcm_handle, hwparams, SND_PCM_ACCESS_MMAP_INTERLEAVED) < 0) {
		fprintf(stderr, "playhrt: Error setting access.\n");
		exit(8);
	}
	if (snd_pcm_hw_params_set_format(pcm_handle, hwparams, SND_PCM_FORMAT_S32_LE) < 0) {
		fprintf(stderr, "playhrt: Error setting format.\n");
		exit(9);
	}
	if (snd_pcm_hw_params_set_rate(pcm_handle, hwparams, RATE, 0) < 0) {
		fprintf(stderr, "playhrt: Error setting rate.\n");
		exit(10);
	}
	if (snd_pcm_hw_params_set_channels(pcm_handle, hwparams, NRCHANNELS) < 0) {
		fprintf(stderr, "playhrt: Error setting channels to %d.\n", NRCHANNELS);
		exit(11);
	}
	if (snd_pcm_hw_params_set_period_size(pcm_handle, hwparams, PERIODSIZE, 0) < 0) {
		fprintf(stderr, "playhrt: Error setting period size to %ld.\n", PERIODSIZE);
		exit(11);
	}
	if (snd_pcm_hw_params_set_buffer_size(pcm_handle, hwparams, hwbufsize) < 0) {
		fprintf(stderr, "\nplayhrt: Error setting buffersize to %ld.\n", hwbufsize);
		exit(12);
	}
	snd_pcm_hw_params_get_buffer_size(hwparams, &hwbufsize);
	if (snd_pcm_hw_params(pcm_handle, hwparams) < 0) {
		fprintf(stderr, "playhrt: Error setting HW params.\n");
		exit(13);
	}
	snd_pcm_hw_params_free(hwparams);

	/* short delay to allow input to fill buffer */
	if (sleep > 0) {
		mtime.tv_sec = sleep/1000000;
		mtime.tv_nsec = 1000*(sleep - mtime.tv_sec*1000000);
		nanosleep(&mtime, NULL);
	}

	if (clock_gettime(CLOCK_MONOTONIC, &mtime) < 0) {
		fprintf(stderr, "playhrt: Cannot get monotonic clock.\n");
		exit(19);
	}

	while (1) {
		frames = PERIODSIZE;

		avail = snd_pcm_avail(pcm_handle);
		if (avail < PERIODSIZE)
			continue;

		if (avail < 0) {
			fprintf(stderr, "ERROR: %s (%ld)\n", strerror(errno), errno);
			break;
		}

		if (!started && avail <= PERIODSIZE) {
			snd_pcm_start(pcm_handle);
			started = 1;
		}

		snd_pcm_mmap_begin(pcm_handle, &areas, &offset, &frames);

		ilen = frames * BYTESPERFRAME;
		iptr = areas[0].addr + offset * BYTESPERFRAME;

		memclean(iptr, ilen);
		i = read(0, iptr, ilen);
		if (i <= 0) {
			fprintf(stderr, "ERROR: %s (%ld)\n", strerror(errno), errno);
			break;
		}

		/* compute time for next wakeup */
		mtime.tv_nsec += WAKEUP_NSEC;
		if (mtime.tv_nsec > 999999999) {
			mtime.tv_nsec -= 1000000000;
			mtime.tv_sec++;
		}

		refreshmem(iptr, i);
		clock_nanosleep(CLOCK_MONOTONIC, TIMER_ABSTIME, &mtime, NULL);

		refreshmem(iptr, i);
		snd_pcm_mmap_commit(pcm_handle, offset, frames);
	}

	snd_pcm_drain(pcm_handle);
	snd_pcm_close(pcm_handle);

	return 0;
}
