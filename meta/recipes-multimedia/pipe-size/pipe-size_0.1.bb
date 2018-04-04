DESCRIPTION = "Simple ssh based audio player for my scw1 server"
HOMEPAGE = "https://gist.github.com/cyfdecyf/1ee981611050202d670c"
SECTION = "devel/libs"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "\
	file://Makefile \
	file://pipe-size.c \
	file://test.sh \
"

TARGET_CC_ARCH += "${LDFLAGS}"

inherit autotools

DEPENDS += "libcap-native"

S = "${WORKDIR}"
B = "${S}"

pkg_postinst_ontarget_${PN}() {
    setcap CAP_SYS_RESOURCE=+ep ${bindir}/pipe-size
}
