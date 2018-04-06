FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PACKAGECONFIG_remove += "libsolv"

SRC_URI += "\
            file://opkg.conf \
"