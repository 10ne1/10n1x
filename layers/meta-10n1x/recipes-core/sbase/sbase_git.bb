DESCRIPTION = "a collection of unix tools that are portable across unix-systems."
HOMEPAGE = "https://core.suckless.org/sbase"
SECTION = "base/shell"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://LICENSE;md5=df8c101dca8aa076eeda51e3afc7847f"

SRCREV = "d098ac4abc805d6bdfc2b331de4633d7bda03b00"

SRC_URI = "git://git.suckless.org/sbase \
           file://0001-config.mk-make-safe-for-cross-compilation-within-OE.patch \
           "

S = "${WORKDIR}/git"

do_compile() {
	oe_runmake
}

do_install() {
	oe_runmake install DESTDIR="${D}" PREFIX="${prefix}" MANPREFIX="${mandir}"
}
