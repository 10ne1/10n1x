FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PACKAGECONFIG_remove += "libsolv"
PACKAGECONFIG += "curl"

SRC_URI += "\
            file://opkg.conf \
"