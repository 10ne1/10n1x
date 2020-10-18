FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_rock-pi-4 += "\
	file://0001-dts-config-rk3399-rock-pi-4-change-baudrate-to-more-.patch \
"
