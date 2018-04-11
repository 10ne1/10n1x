FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
	file://fuse.conf \
"

do_install_append() {
	install -m 0644 ${WORKDIR}/fuse.conf ${D}${sysconfdir}
}
