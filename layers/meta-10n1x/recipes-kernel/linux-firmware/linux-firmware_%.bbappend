FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
	file://brcmfmac43456-sdio.bin \
	file://brcmfmac43456-sdio.clm_blob \
	file://brcmfmac43456-sdio.txt \
"

do_install_append() {
	install -d ${nonarch_base_libdir}/firmware/brcm
	install -m 0600 ${WORKDIR}/brcmfmac43456-sdio.bin ${D}${nonarch_base_libdir}/firmware/brcm
	install -m 0600 ${WORKDIR}/brcmfmac43456-sdio.clm_blob ${D}${nonarch_base_libdir}/firmware/brcm
	install -m 0600 ${WORKDIR}/brcmfmac43456-sdio.txt ${D}${nonarch_base_libdir}/firmware/brcm
}

PACKAGES =+ "\
	 ${PN}-bcm43456 \
"

FILES_${PN}-bcm43456 = "\
	${nonarch_base_libdir}/firmware/brcm/brcmfmac43456-sdio.bin \
	${nonarch_base_libdir}/firmware/brcm/brcmfmac43456-sdio.clm_blob \
	${nonarch_base_libdir}/firmware/brcm/brcmfmac43456-sdio.txt \
"