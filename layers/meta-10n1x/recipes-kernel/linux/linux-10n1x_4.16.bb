DESCRIPTION = "Linux kernel for 10n1x"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LINUX_VERSION = "4.16"

SRCREV = "${AUTOREV}"

SRC_URI = "\
	git:///home/adi/workspace/linux;branch=linux-4.16.y-rt-rpi \
	file://defconfig \
"

STAGING_KERNEL_DIR = "${WORKDIR}/git"

INITRAMFS_IMAGE = ""

KERNEL_VERSION_SANITY_SKIP="1"

COMPATIBLE_MACHINE = "^rpi|x64$"

CMDLINE = "console=tty1 loglevel=7 logo.nologo"
CMDLINE_append_raspberrypi3 += "root=/dev/mmcblk0p2 rootfstype=ext4 rootwait isolcpus=3"

DEPENDS_append_raspberrypi3 += "linux-firmware"

do_compile_prepend_raspberrypi3() {
	cp -arv ${STAGING_DIR_TARGET}/usr/lib/firmware/brcm/ ${S}/firmware
}

kernel_do_deploy_append_raspberrypi3() {
	install -d ${DEPLOYDIR}/bcm2835-bootfiles
	echo "${CMDLINE}" > ${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt
}

inherit kernel
