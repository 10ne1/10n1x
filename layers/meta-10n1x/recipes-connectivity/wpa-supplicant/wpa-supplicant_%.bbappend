FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PACKAGECONFIG_remove += "gnutls"
PACKAGECONFIG += "openssl"
