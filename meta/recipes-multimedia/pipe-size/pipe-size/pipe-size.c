#include <unistd.h>
#include <errno.h>
#include <stdio.h>
#include <fcntl.h>
#include <limits.h>
#include <stdlib.h>

#define F_SETPIPE_SZ 1031
#define F_GETPIPE_SZ 1032

int main(int argc, char *argv[]) {
	if (argc != 3) {
		printf("Usage: %s <fifo> <new-size>\n\n"
		       "Set pipe buffer size of <fifo> to <new-size>\n",
		       argv[0]);
		return 1;
	}

	errno = 0;
	char *p = argv[2];
	long size_conv = strtol(argv[2], &p, 10);

	if (errno != 0 || *p != '\0' || size_conv > INT_MAX) {
		perror("bad pike size");
		return 1;		
	}

	int fd = -1;
        fd = open(argv[1], O_RDONLY);
        if (fd < 0) {
		perror("open failed");
		return 1;
        }
    
/*	long pipe_size = (long)fcntl(fd, F_GETPIPE_SZ);
	if (pipe_size == -1) {
		perror("get pipe size failed.");
	}
	printf("default pipe size: %ld\n", pipe_size); */

	int ret = fcntl(fd, F_SETPIPE_SZ, size_conv);
	if (ret < 0) {
		perror("set pipe size failed.");
		return 1;
	}

/*	pipe_size = (long)fcntl(fd, F_GETPIPE_SZ);
	if (pipe_size == -1) {
		perror("get pipe size 2 failed.");
	}
	printf("new pipe size: %ld\n", pipe_size);*/

	close(fd);
	return 0;
}
