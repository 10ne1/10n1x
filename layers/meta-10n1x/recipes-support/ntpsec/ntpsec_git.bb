DESCRIPTION = "A secure, hardened, and improved implementation of Network Time Protocol derived from NTP Classic"
HOMEPAGE = "https://www.ntpsec.org/"
SECTION = "net"
LICENSE = "NTP"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9492720a903f5832c8c7c2e505296e90"

PV = "1.1.1+git${SRCPV}"
SRCREV = "cc47b037ef4d33d7e3fe07545a67d54c30a8e025"

SRC_URI = "\
	git://gitlab.com/NTPsec/ntpsec.git;protocol=https \
	file://ntp.conf \
"

S = "${WORKDIR}/git"

DEPENDS += "asciidoc-native libxslt-native openssl python3 bison-native"

EXTRA_OECONF_append += "\
	CC=${BUILD_CC} \
	--cross-compiler='${CC}' \
	--cross-cflags='${CFLAGS}' \
	--cross-ldflags='${LDFLAGS}' \
	--nopycache --nopyc --nopyo \
	--enable-debug-gdb \
	--disable-manpage \
"

PACKAGES =+ "${PN}-tools"

RDEPENDS_${PN}-tools += "bash python3"

FILES_${PN}-tools += "\
	${bindir} \
	${libdir}/python* \
"

inherit waf python3native

do_install_append() {
	install -d ${D}/${sysconfdir}
	install -m 0644 ${WORKDIR}/ntp.conf ${D}/${sysconfdir}
}
