DESCRIPTION = "Simple ssh based audio player for my scw1 server"
HOMEPAGE = "http://dave.null"
SECTION = "devel/libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://scw1play.sh"

RDEPENDS_${PN} = "bash sox frankl-stereo pipe-size"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/scw1play.sh ${D}${bindir}
}
