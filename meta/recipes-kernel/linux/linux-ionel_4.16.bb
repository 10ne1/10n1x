FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

require recipes-kernel/linux/linux-raspberrypi.inc

DEPENDS += "linux-firmware-raspbian"

LINUX_VERSION = "4.16"

SRCREV = "${AUTOREV}"

SRC_URI = "\
	git:///home/adi/workspace/linux;branch=dev/aratiu/master-iqaudio-cherry-pick \
	file://defconfig \
"

ARM_KEEP_OABI = "0"
INITRAMFS_IMAGE_BUNDLE = "0"
KERNEL_ENABLE_CGROUPS = "1"
DISABLE_RPI_BOOT_LOGO = "1"

KCONFIG_MODE = "--olddefconfig"
KBUILD_DEFCONFIG_raspberrypi3 = ""

KERNEL_VERSION_SANITY_SKIP="1"

CMDLINE += "console=tty1 loglevel=7"

do_compile_prepend() {
	cp -arv ${STAGING_DIR_TARGET}/lib/firmware/brcm/ ${S}/firmware
}