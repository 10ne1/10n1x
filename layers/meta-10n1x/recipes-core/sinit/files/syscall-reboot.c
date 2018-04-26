#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <sys/reboot.h>

int main(int argc, char** argv) {
	if (argc != 2) {
		printf("Usage: %s <reboot|poweroff>\n", argv[0]);
		return 0;
	}
	sync();
	if (!strncmp(argv[1], "poweroff", 8)) {
		reboot(RB_POWER_OFF);
	} else {
		reboot(RB_AUTOBOOT);
	}
	return 0;
}
