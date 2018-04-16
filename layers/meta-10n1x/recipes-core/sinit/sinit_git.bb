DESCRIPTION = "sinit is a suckless init, initially based on Rich Felker’s minimal init."
HOMEPAGE = "https://core.suckless.org/sinit"
SECTION = "base/shell"
LICENSE = "MIT-X"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1d1472b4fe357433ed8567058835451e"

SRCREV = "28c44b6b94a870f2942c37f9cfbae8b770595712"

SRC_URI = "\
	git://git.suckless.org/sinit \
	file://0001-config.mk-remove-cross-compile-incompatible-variable.patch \
	file://rc.init \
	file://rc.shutdown \
"

S = "${WORKDIR}/git"

do_compile() {
	oe_runmake
}

do_install() {
	oe_runmake install DESTDIR="${D}" PREFIX="${prefix}" MANPREFIX="${mandir}"

	install -m 755 ${WORKDIR}/rc.init	${D}${bindir}
	install -m 755 ${WORKDIR}/rc.shutdown	${D}${bindir}

	cd ${D}/${bindir}
	ln -s sinit init
}
