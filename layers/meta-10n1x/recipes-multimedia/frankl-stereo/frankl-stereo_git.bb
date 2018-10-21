DESCRIPTION = "A collection of programs and scripts to be used for stereo playback"
HOMEPAGE = "http://frankl.luebecknet.de/frankl/stereoutils/index.html"
SECTION = "devel/libs"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"

SRC_URI = "git://github.com/10ne1/frankl_stereo;branch=dev/aratiu/simplify-play-loop"

S = "${WORKDIR}/git"

DEPENDS += "alsa-lib libsndfile1 libsoxr"

RDEPENDS_${PN} += "libsoxr"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
    oe_runmake REFRESH=VFP
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/bin/* ${D}${bindir}
}
