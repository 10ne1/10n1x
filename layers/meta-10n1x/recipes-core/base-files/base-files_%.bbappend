FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

hostname="ionelpi"

do_install_append() {
	rm -rf ${D}/tmp
	rm -rf ${D}/var/tmp
	rm -rf ${D}/var/volatile
	ln -sf ../run/tmp ${D}/tmp
	ln -sf ../run/tmp ${D}/var/tmp
	ln -sf ../run ${D}/var/volatile
}
