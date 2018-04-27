DESCRIPTION = "a secure, hardened, and improved implementation of Network Time Protocol derived from NTP Classic"
HOMEPAGE = "https://www.ntpsec.org/"
SECTION = "base/shell"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9492720a903f5832c8c7c2e505296e90"

SRCREV = "0b2beb1ffbebed5bd6d9ef615aac905df7e2f220"

SRC_URI = "git://gitlab.com/NTPsec/ntpsec.git;protocol=https"

S = "${WORKDIR}/git"

DEPENDS += "openssl python"

EXTRA_OECONF_append += "\
	--cross-compiler='${CC}' \
	--cross-cflags='${CFLAGS}' \
	--cross-ldflags='${LDFLAGS}' \
"

inherit waf pythonnative
