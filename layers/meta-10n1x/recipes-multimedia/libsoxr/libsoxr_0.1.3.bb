DESCRIPTION="The SoX Resampler library"
HOMEPAGE = "https://sourceforge.net/projects/soxr"
SECTION = "audio"

SRC_URI = "${SOURCEFORGE_MIRROR}/soxr/soxr-${PV}-Source.tar.xz"
SRC_URI[md5sum] = "3f16f4dcb35b471682d4321eda6f6c08"
SRC_URI[sha256sum] = "b111c15fdc8c029989330ff559184198c161100a59312f5dc19ddeb9b5a15889"

S = "${WORKDIR}/soxr-${PV}-Source"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING.LGPL;md5=8c2e1ec1540fb3e0beb68361344cba7e"

inherit cmake

DEPENDS = "ffmpeg"

RDEPENDS_${PN} += "libavutil"

EXTRA_OECMAKE = "\
	-DCMAKE_BUILD_TYPE=Release \
	-Wno-dev \
"

INSANE_SKIP_${PN} += "already-stripped"
