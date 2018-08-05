SUMMARY = "Xapian is an Open Source Search Engine Library"
HOMEPAGE = "http://xapian.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4325afd396febcb659c36b49533135d4"

SRC_URI = "http://oligarchy.co.uk/xapian/${PV}/${BPN}-${PV}.tar.xz"
SRC_URI[md5sum] = "f321cddd39cf68d027d13d58c7557a95"
SRC_URI[sha256sum] = "13f08a0b649c7afa804fa0e85678d693fd6069dd394c9b9e7d41973d74a3b5d3"

inherit autotools

DEPENDS = "util-linux zlib"

EXTRA_OECONF += "--enable-backend-glass --disable-backend-honey"

sysroot_stage_all_append () {
    sysroot_stage_dir ${D}${bindir} ${SYSROOT_DESTDIR}${bindir_crossscripts}
}