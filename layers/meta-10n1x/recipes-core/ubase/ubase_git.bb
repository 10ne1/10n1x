DESCRIPTION = "ubase is a collection of unportable tools, similar to util-linux"
HOMEPAGE = "https://core.suckless.org/ubase"
SECTION = "base/shell"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://LICENSE;md5=20219e9cef81caa3183e331eed8bc808"

SRCREV = "55795531f03ccb5a41cf80fd564b862c103252cc"

SRC_URI = "git://git.suckless.org/ubase \
           file://0001-config.mk-remove-unsafe-for-cross-compile-vars.patch \
           file://0001-mount-don-t-call-realpath-on-root-target.patch \
           "

S = "${WORKDIR}/git"

CFLAGS += "-D_GNU_SOURCE"

do_compile() {
	oe_runmake
}

do_install() {
	oe_runmake install DESTDIR="${D}" PREFIX="${prefix}" MANPREFIX="${mandir}"
	rm ${D}${base_bindir}/{login,su,mknod,ps,stat,watch,dd,pidof,df,lastlog,passwd,id,free,truncate,pwdx,uptime,who}
}
