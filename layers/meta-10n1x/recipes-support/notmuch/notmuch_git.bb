DESCRIPTION = "notmuch is a xapian-based email index and search engine"
HOMEPAGE = "https://notmuchmail.org/"
SECTION = "base/shell"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=494b4ff8f3dacddf602b4b7e6910a966"

SRCREV = "186d4983bf52f019fb8fdd61a5927d7a2a21962f"

SRC_URI = "\
	git://github.com/10ne1/notmuch;branch=dev/aratiu/filesize-query-master-rebase \
	file://0001-configure-enable-cross-compilation.patch \
"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

DEPENDS += "xapian-core libtalloc gmime glib-2.0"
RDEPENDS_${PN} += "xapian-core libtalloc gmime glib-2.0"

PACKAGES =+ "${PN}-emacs ${PN}-completion"

FILES_${PN}-emacs = "/usr/share/emacs"

FILES_${PN}-completion = "/usr/share/bash-completion /usr/share/zsh"