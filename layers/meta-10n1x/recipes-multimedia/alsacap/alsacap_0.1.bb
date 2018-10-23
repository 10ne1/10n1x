DESCRIPTION = "List capabilities of sound devices"
HOMEPAGE = "http://www.volkerschatz.com/noise/alsa.html#alsacap"
SECTION = "devel/libs"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9e398ba76be3f67dfe58740aa067c50c"

SRC_URI = "http://www.volkerschatz.com/noise/alsacap.tgz"

SRC_URI[md5sum] = "19642bdffd4666caa53344271aa585e9"

DEPENDS = "alsa-lib"
RDEPENDS_${PN} = "alsa-lib"

S = "${WORKDIR}/alsacap"

do_configure() {
	sed -i -e 's!gcc!$(CC) $(CFLAGS) $(LDFLAGS)!' Makefile
}

do_compile() {
	oe_runmake
}

do_install() {
	install -d ${D}/usr/bin/
	install -m 755 alsacap ${D}/usr/bin/
}
