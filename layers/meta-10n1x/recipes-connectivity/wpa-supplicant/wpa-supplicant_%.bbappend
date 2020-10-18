FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PACKAGECONFIG_remove += "gnutls"
PACKAGECONFIG += "openssl"

do_install_append () {
	install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
	ln -sf ${libdir}/systemd/system/wpa_supplicant@.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/wpa_supplicant@wlan0.service
}
