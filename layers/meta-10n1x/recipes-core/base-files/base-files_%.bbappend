FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

hostname="${MACHINE}"

do_install_basefilesissue () {
	echo ${hostname} > ${D}${sysconfdir}/hostname

	install -m 644 ${WORKDIR}/issue	${D}${sysconfdir}
	printf "Welcome to 10n1x \\\l\n\n"		>> ${D}${sysconfdir}/issue
	printf "wlan0: \\\4{wlan0} \\\6{wlan0}\n"	>> ${D}${sysconfdir}/issue
	printf "eth0: \\\4{eth0} \\\6{eth0}\n\n"	>> ${D}${sysconfdir}/issue
}
