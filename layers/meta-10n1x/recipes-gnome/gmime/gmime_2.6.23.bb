LICENSE = "LGPL-2.1"
DESCRIPTION = "Runtime libraries for parsing and creating MIME mail"
SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "glib-2.0 zlib"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

inherit gnome autotools lib_package binconfig gobject-introspection

SRC_URI[archive.md5sum] = "247072236d84bd0fbbff299d69bdf333"
SRC_URI[archive.sha256sum] = "7149686a71ca42a1390869b6074815106b061aaeaaa8f2ef8c12c191d9a79f6a"

SRC_URI += "file://iconv-detect.h"

CACHED_CONFIGUREVARS += "ac_cv_have_iconv_detect_h=yes"

do_configure_append() {
	cp ${WORKDIR}/iconv-detect.h ${S}
}

DEPENDS += "libgpg-error"

EXTRA_OECONF += "--disable-mono"

# we do not need GNOME 1 gnome-config support
do_install_append () {
		  rm -f ${D}${libdir}/gmimeConf.sh
		  
}
