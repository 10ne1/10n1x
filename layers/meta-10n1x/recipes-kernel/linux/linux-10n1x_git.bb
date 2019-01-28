DESCRIPTION = "Linux kernel for 10n1x"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

LINUX_VERSION = "4.19"

SRCREV = "${AUTOREV}"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI = "\
	git://github.com/10ne1/linux;branch=linux-${LINUX_VERSION}.y-rt-rpi \
	file://defconfig \
"

STAGING_KERNEL_DIR = "${WORKDIR}/git"

INITRAMFS_IMAGE = ""

KERNEL_VERSION_SANITY_SKIP="1"

COMPATIBLE_MACHINE = "^rpi|x64$"

SDIMG_KERNELIMAGE_raspberrypi3 = "zImage"

CMDLINE = "console=tty1 loglevel=7 logo.nologo"
CMDLINE_append_raspberrypi3 += "root=/dev/mmcblk0p2 rootfstype=ext4 rootwait isolcpus=3"

DEPENDS_append_raspberrypi3 += "\
	linux-firmware-rpidistro \
	wireless-regdb \
"

do_compile_prepend_raspberrypi3() {
	fwdir="${STAGING_DIR_TARGET}${libdir}/firmware"
	cp -arv $fwdir/brcm		${S}/firmware
	cp $fwdir/regulatory.db		${S}/firmware
}

kernel_do_deploy_append_raspberrypi3() {
	install -d ${DEPLOYDIR}/bcm2835-bootfiles
	echo "${CMDLINE}" > ${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt
}

inherit kernel
