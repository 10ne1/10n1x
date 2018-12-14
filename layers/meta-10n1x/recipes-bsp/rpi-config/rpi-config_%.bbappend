GPU_MEM="16"

DISABLE_SPLASH = "1"

BOOT_DELAY = "0"
BOOT_DELAY_MS = "0"

do_deploy_append() {
	echo "avoid_warnings=2" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "disable_splash" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "device_tree=bcm2837-rpi-3-b.dtb" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
}
