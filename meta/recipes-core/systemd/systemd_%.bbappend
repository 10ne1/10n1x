FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
	file://0001-Fix-strerror_r-segfault-on-non-glibc-systems.patch \
	file://70-wifi-powersave.rules \
"

PACKAGECONFIG_append += "serial-getty-generator"

do_install_append() {
	install -d ${D}${sysconfdir}/udev/rules.d/
	install -m 0644 ${WORKDIR}/70-wifi-powersave.rules ${D}${sysconfdir}/udev/rules.d/
}
