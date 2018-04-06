FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
	file://fuse.conf \
"

hostname="ionelpi"

do_install_append() {
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/fuse.conf ${D}${sysconfdir}
}

FILES_${PN} += "\
	${sysconfdir}/fuse.conf \
"
