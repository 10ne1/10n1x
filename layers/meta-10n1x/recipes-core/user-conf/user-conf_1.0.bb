DESCRIPTION = "Basic user configuration"
HOMEPAGE = "https://github.com/10ne1/10n1x"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/ssh_config;md5=b72ac72df37b001f03952d7e4adb8fe2"

# these keys are examples, add your own here
SRC_URI = "\
	git:///home/adi/workspace/random-dev;protocol=file \
	file://authorized_keys \
	file://ssh_config \
"

SRCREV = "${AUTOREV}"

DEPENDS += "gnupg-native"

inherit allarch useradd extrausers

S = "${WORKDIR}/git"

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = " -u 1000 -m -s /bin/bash -G audio,video,games,tty,lp,shutdown -P ${USER} ${USER}; "

do_install() {
	install -d ${D}/home/${USER}/{.ssh,ssh_nas,nfs_nas}
	install -d ${D}/home/root/.ssh

	install -m 0644 ${WORKDIR}/authorized_keys 	${D}/home/${USER}/.ssh/
	install -m 0644 ${WORKDIR}/ssh_config		${D}/home/${USER}/.ssh/config
	install -m 0644 ${WORKDIR}/ssh_config		${D}/home/root/.ssh/config

	install -m 0600 ${S}/10n1x/rpi_id_rsa		${D}/home/${USER}/.ssh/id_rsa
	install -m 0644 ${S}/10n1x/rpi_id_rsa.pub	${D}/home/${USER}/.ssh/id_rsa.pub
	install -m 0600 ${S}/10n1x/rpi_id_rsa		${D}/home/root/.ssh/id_rsa

	chown -R ${USER}:${GROUP} ${D}/home/${USER}

	install -d ${D}${sysconfdir}/sudoers.d
	echo "${USER} ALL=(ALL) NOPASSWD: ALL" > ${D}${sysconfdir}/sudoers.d/001_${USER}

	install -d ${D}${sysconfdir}/wpa_supplicant
	install -m 0644 ${S}/10n1x/wpa_supplicant-wlan0.conf ${D}${sysconfdir}/wpa_supplicant
}

FILES_${PN} += "\
	/home/${USER}/.ssh/id_rsa.pub \
	/home/${USER}/.ssh/id_rsa \
	/home/${USER}/.ssh/authorized_keys \
	/home/${USER}/.ssh/config \
	/home/${USER}/ssh_nas \
	/home/${USER}/nfs_nas \
	/home/root/.ssh/id_rsa \
	/home/root/.ssh/config \
"

INSANE_SKIP_${PN} += "host-user-contaminated"
