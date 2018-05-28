DESCRIPTION = "A collection of programs and scripts to be used for stereo playback"
HOMEPAGE = "http://frankl.luebecknet.de/frankl/stereoutils/index.html"
SECTION = "devel/libs"
LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "cf3c157070a45e739834fe87019f0e7a5c237296"

SRC_URI = "git://bitbucket.org/frank_l/frankl_stereo.git;protocol=https \
           file://0001-playhrt-simplify-mmap-writeloop.patch \
           file://0002-resample_soxr-simplify-fix-segfault.patch \
           "

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
