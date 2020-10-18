do_install_append() {
	echo "session required pam_limits.so" >> ${D}${sysconfdir}/pam.d/common-session
	echo "session required pam_limits.so" >> ${D}${sysconfdir}/pam.d/common-session-noninteractive
	echo "* hard memlock 10240" >> ${D}${sysconfdir}/security/limits.conf
	echo "* soft memlock 10240" >> ${D}${sysconfdir}/security/limits.conf
}