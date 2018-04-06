FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://dhcpcd.service"

FILES_${PN} += "${libdir}/systemd/system/dhcpcd.service"

do_install_append () {
	install -d ${D}${libdir}/systemd/system/
	install -m 0644 ${WORKDIR}/dhcpcd.service ${D}${libdir}/systemd/system
	install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
	ln -sf ${libdir}/systemd/system/dhcpcd.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/dhcpcd.service
}




