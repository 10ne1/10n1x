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

do_install_basefilesissue () {
	if [ "${hostname}" ]; then
		echo ${hostname} > ${D}${sysconfdir}/hostname
	fi

	install -m 644 ${WORKDIR}/issue	${D}${sysconfdir}
	printf "Welcome to 10n1x \\\l\n\n"		>> ${D}${sysconfdir}/issue
	printf "wlan0: \\\4{wlan0} \\\6{wlan0}\n"	>> ${D}${sysconfdir}/issue
	printf "eth0: \\\4{eth0} \\\6{eth0}\n\n"	>> ${D}${sysconfdir}/issue
}
