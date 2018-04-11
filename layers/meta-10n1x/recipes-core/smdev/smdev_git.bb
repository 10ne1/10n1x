DESCRIPTION = "smdev is a simple program to manage device nodes."
HOMEPAGE = "https://core.suckless.org/smdev"
SECTION = "base/shell"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://LICENSE;md5=59ce6089b74da318d2359ad7ab5b1aed"

SRCREV = "8d07540c417e3a31942028318197c89b640278d5"

SRC_URI = "\
	git://git.suckless.org/smdev \
	file://udev.pc.in \
	file://0001-config.mk-remove-unsafe-for-cross-compilation-vars.patch \
"

PROVIDES = "udev"

RPROVIDES_${PN} = "hotplug udev"

CONFLICT_DISTRO_FEATURES = "systemd"

S = "${WORKDIR}/git"

PACKAGES = "${PN} ${PN}-dbg"

do_compile() {
	oe_runmake
}

do_install() {
	oe_runmake install DESTDIR="${D}"
	install -d ${D}${libdir}/pkgconfig
	sed 's/%SMDEV_VERSION%/${PV}/' ${WORKDIR}/udev.pc.in > ${D}${libdir}/pkgconfig/udev.pc
}

FILES_${PN} += "${libdir}/pkgconfig"
