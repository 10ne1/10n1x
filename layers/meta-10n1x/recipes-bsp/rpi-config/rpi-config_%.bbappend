GPU_MEM="16"

do_deploy_append() {
	echo "disable_splash" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
	echo "device_tree=bcm2837-rpi-3-b.dtb" >> ${DEPLOYDIR}/bcm2835-bootfiles/config.txt
}
