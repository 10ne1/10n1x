FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
	file://brcmfmac43456-sdio.bin \
	file://brcmfmac43456-sdio.clm_blob \
	file://brcmfmac43456-sdio.radxa,rockpi4.txt \
"

BRPATH = "${nonarch_base_libdir}/firmware/brcm"

do_install_append() {
	install -d ${BRPATH}
	install -m 0600 ${WORKDIR}/brcmfmac43456-sdio.bin ${D}${BRPATH}
	install -m 0600 ${WORKDIR}/brcmfmac43456-sdio.clm_blob ${D}${BRPATH}
	install -m 0600 ${WORKDIR}/brcmfmac43456-sdio.radxa,rockpi4.txt ${D}${BRPATH}
}

PACKAGES =+ "\
	 ${PN}-bcm43456 \
"

FILES_${PN}-bcm43456 = "\
	${BRPATH}/brcmfmac43456-sdio.bin \
	${BRPATH}/brcmfmac43456-sdio.clm_blob \
	${BRPATH}/brcmfmac43456-sdio.radxa,rockpi4.txt \
"
