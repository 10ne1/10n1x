do_install_append() {
	if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'false', 'true', d)}; then
		rm -rf ${D}${sysconfdir}/init.d/
	fi
}
