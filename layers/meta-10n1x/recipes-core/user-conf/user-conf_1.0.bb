DESCRIPTION = "Basic user configuration"
HOMEPAGE = "https://github.com/10ne1/meta-ionel"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/id_rsa.gpg;md5=9889c38d232f8998e6a573e899e83235"

# these keys are examples, add your own here
SRC_URI = "\
	file://id_rsa.gpg \
	file://id_rsa.pub \
	file://authorized_keys \
	file://ssh_config \
"

DEPENDS += "gnupg-native"

inherit allarch useradd extrausers

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = " -u 1111 -m -s /bin/bash -G audio,video,games,tty,lp,shutdown -P ${USER} ${USER}; "

do_install() {
	install -d ${D}/home/${USER}/{music,.ssh}
	install -d ${D}/home/root/.ssh

	install -m 0644 ${WORKDIR}/id_rsa.pub		${D}/home/${USER}/.ssh/
	install -m 0644 ${WORKDIR}/authorized_keys 	${D}/home/${USER}/.ssh/
	install -m 0644 ${WORKDIR}/ssh_config		${D}/home/${USER}/.ssh/config
	install -m 0644 ${WORKDIR}/ssh_config		${D}/home/root/.ssh/config

	gpg --output ${WORKDIR}/id_rsa --decrypt ${WORKDIR}/id_rsa.gpg
	install -m 0600 ${WORKDIR}/id_rsa ${D}/home/${USER}/.ssh/id_rsa
	install -m 0600 ${WORKDIR}/id_rsa ${D}/home/root/.ssh/id_rsa
	rm ${WORKDIR}/id_rsa

	chown -R ${USER}:${GROUP} ${D}/home/${USER}

	install -d ${D}${sysconfdir}/sudoers.d
	echo "${USER} ALL=(ALL) NOPASSWD: ALL" > ${D}${sysconfdir}/sudoers.d/001_${USER}
}

FILES_${PN} += "\
	/home/${USER}/.ssh/id_rsa.pub \
	/home/${USER}/.ssh/id_rsa \
	/home/${USER}/.ssh/authorized_keys \
	/home/${USER}/.ssh/config \
	/home/${USER}/music \
	/home/root/.ssh/id_rsa \
	/home/root/.ssh/config \
"
