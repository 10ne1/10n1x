FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
	file://fuse.conf \
"

hostname="ionelpi"

do_install_append() {
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/fuse.conf ${D}${sysconfdir}

	rm -rf ${D}/tmp
	rm -rf ${D}/var/tmp
	rm -rf ${D}/var/volatile
	ln -sf ../run/tmp ${D}/tmp
	ln -sf ../run/tmp ${D}/var/tmp
	ln -sf ../run ${D}/var/volatile
}

FILES_${PN} += "\
	${sysconfdir}/fuse.conf \
"
