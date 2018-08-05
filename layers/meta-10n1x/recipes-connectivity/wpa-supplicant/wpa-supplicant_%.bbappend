FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
	file://wpa_supplicant-wlan0.conf \
	file://lulzNet.gpg \
	file://NI.gpg \
"

PACKAGECONFIG_remove += "gnutls"
PACKAGECONFIG += "openssl"

DEPENDS += "gnupg-native"

do_install_append () {
	install -d ${D}${sysconfdir}/wpa_supplicant
	install -m 0644 ${WORKDIR}/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_supplicant

	lulzpass=$(gpg --yes --decrypt ${WORKDIR}/lulzNet.gpg)
	nipass=$(gpg --yes --decrypt ${WORKDIR}/NI.gpg)
	sed -i s/%%CHANGEME1%%/${lulzpass}/ ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf
	sed -i s/%%CHANGEME2%%/${nipass}/   ${D}${sysconfdir}/wpa_supplicant/wpa_supplicant-wlan0.conf
}
