DESCRIPTION = "Basic user configuration"
HOMEPAGE = "https://github.com/10ne1/meta-ionel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/id_rsa.gpg;md5=9889c38d232f8998e6a573e899e83235"

# these keys are examples, add your own here
SRC_URI = "\
	file://id_rsa.gpg \
	file://id_rsa.pub \
	file://authorized_keys \
"

DEPENDS += "gnupg-native"

inherit allarch useradd extrausers

USERADD_PACKAGES = "${PN}"

USER = "adi"

USERADD_PARAM_${PN} = " -u 1111 -m -s /bin/bash -G audio,video,games,tty,lp,shutdown -P ${USER} ${USER}; "

do_install() {
	install -d ${D}/home/${USER}/{music,.ssh}

	gpg --output ${D}/home/${USER}/.ssh/id_rsa --decrypt ${WORKDIR}/id_rsa.gpg
	chmod 0600 ${D}/home/${USER}/.ssh/id_rsa
	install -m 0644 ${WORKDIR}/id_rsa.pub	   ${D}/home/${USER}/.ssh/
	install -m 0644 ${WORKDIR}/authorized_keys ${D}/home/${USER}/.ssh/

	chown -R ${USER}:${GROUP} ${D}/home/${USER}
}

FILES_${PN} += "\
	/home/${USER}/.ssh/id_rsa.pub \
	/home/${USER}/.ssh/id_rsa \
	/home/${USER}/.ssh/authorized_keys \
	/home/${USER}/music \
"
