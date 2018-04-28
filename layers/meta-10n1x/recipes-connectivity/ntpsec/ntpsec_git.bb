DESCRIPTION = "A secure, hardened, and improved implementation of Network Time Protocol derived from NTP Classic"
HOMEPAGE = "https://www.ntpsec.org/"
SECTION = "net"
LICENSE = "NTP"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9492720a903f5832c8c7c2e505296e90"

PV = "1.1.0+git${SRCPV}"
SRCREV = "0b2beb1ffbebed5bd6d9ef615aac905df7e2f220"

SRC_URI = "git://gitlab.com/NTPsec/ntpsec.git;protocol=https"

S = "${WORKDIR}/git"

DEPENDS += "asciidoc-native libxslt-native openssl python3"

EXTRA_OECONF_append += "\
	CC=${BUILD_CC} \
	--cross-compiler='${CC}' \
	--cross-cflags='${CFLAGS}' \
	--cross-ldflags='${LDFLAGS}' \
	--nopycache --nopyc --nopyo \
	--enable-debug-gdb \
	--enable-doc \
"

PACKAGES =+ "${PN}-tools"

RDEPENDS_${PN}-tools += "bash python3"

FILES_${PN}-tools += "\
	${bindir} \
	${libdir}/python* \
"

inherit waf python3native
