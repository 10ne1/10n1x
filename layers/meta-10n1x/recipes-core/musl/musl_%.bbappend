FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0002-musl-sched-bring-back-linux-sched_-functions-syscall.patch"

do_install_append() {
	ln -s /usr/lib/ld-musl-armhf.so.1 ${D}/usr/lib/ld-linux-armhf.so.3
}
