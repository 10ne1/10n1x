DESCRIPTION = "Small 'net top' tool. Breaks traffic down by bandwidth per process."
HOMEPAGE = "https://github.com/raboof/nethogs"
SECTION = "devel/libs"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "0.8.5+git${SRCPV}"

SRCREV = "2e8a4f8b8bec91eab52dfa1eca11faa6f6b43f59"

SRC_URI = "git://github.com/raboof/nethogs.git;protocol=https"

S = "${WORKDIR}/git"

DEPENDS += "libpcap ncurses"

do_compile() {
    oe_runmake nethogs
}

do_install() {
    oe_runmake install PREFIX="/usr" DESTDIR=${D} MANDIR=${mandir}
}

pkg_postinst_ontarget_${PN}() {
    setcap cap_net_raw,cap_net_admin=eip /usr/sbin/nethogs
}
