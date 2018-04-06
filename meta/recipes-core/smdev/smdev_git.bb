DESCRIPTION = "smdev is a simple program to manage device nodes."
HOMEPAGE = "https://core.suckless.org/smdev"
SECTION = "base/shell"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://LICENSE;md5=59ce6089b74da318d2359ad7ab5b1aed"

SRCREV = "8d07540c417e3a31942028318197c89b640278d5"

SRC_URI = "\
	git://git.suckless.org/smdev \
	file://0001-config.mk-remove-unsafe-for-cross-compilation-vars.patch \
"

S = "${WORKDIR}/git"

do_compile() {
	oe_runmake
}

do_install() {
	oe_runmake install DESTDIR="${D}"
}
