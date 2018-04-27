DESCRIPTION = "A secure, hardened, and improved implementation of Network Time Protocol derived from NTP Classic"
HOMEPAGE = "https://www.ntpsec.org/"
SECTION = "base/shell"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9492720a903f5832c8c7c2e505296e90"

PV = "1.1.0+git${SRCPV}"
SRCREV = "0b2beb1ffbebed5bd6d9ef615aac905df7e2f220"

SRC_URI = "git://gitlab.com/NTPsec/ntpsec.git;protocol=https"

S = "${WORKDIR}/git"

DEPENDS += "openssl python"

RDEPENDS_${PN} += "bash"

EXTRA_OECONF_append += "\
	CC=${BUILD_CC} \
	--cross-compiler='${CC}' \
	--cross-cflags='${CFLAGS}' \
	--cross-ldflags='${LDFLAGS}' \
	--enable-debug-gdb \
"

FILES_${PN} += "/usr/lib/python2.7/site-packages"

inherit waf pythonnative
