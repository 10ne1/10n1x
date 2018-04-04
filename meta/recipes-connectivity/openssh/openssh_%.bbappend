FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
	file://ssh_host_dsa_key \
	file://ssh_host_ecdsa_key \
	file://ssh_host_ed25519_key \
	file://ssh_host_rsa_key \
	file://ssh_host_dsa_key.pub \
	file://ssh_host_ecdsa_key.pub \
        file://ssh_host_ed25519_key.pub \
	file://ssh_host_rsa_key.pub \
"

do_install_append() {
	install -d ${D}${sysconfdir}/ssh
	install -m 0600 ${WORKDIR}/ssh_host_dsa_key ${D}${sysconfdir}/ssh
	install -m 0600 ${WORKDIR}/ssh_host_ecdsa_key ${D}${sysconfdir}/ssh
	install -m 0600 ${WORKDIR}/ssh_host_ed25519_key ${D}${sysconfdir}/ssh
	install -m 0600 ${WORKDIR}/ssh_host_rsa_key ${D}${sysconfdir}/ssh
	install -m 0644 ${WORKDIR}/ssh_host_dsa_key.pub ${D}${sysconfdir}/ssh
	install -m 0644 ${WORKDIR}/ssh_host_ecdsa_key.pub ${D}${sysconfdir}/ssh
        install -m 0644 ${WORKDIR}/ssh_host_ed25519_key.pub ${D}${sysconfdir}/ssh
	install -m 0644 ${WORKDIR}/ssh_host_rsa_key.pub ${D}${sysconfdir}/ssh
}

FILES_${PN}-sshd += "\
	${sysconfdir}/ssh/ssh_host_dsa_key \
	${sysconfdir}/ssh/ssh_host_ecdsa_key \
	${sysconfdir}/ssh/ssh_host_ed25519_key \
	${sysconfdir}/ssh/ssh_host_rsa_key \
	${sysconfdir}/ssh/ssh_host_dsa_key.pub \
	${sysconfdir}/ssh/ssh_host_ecdsa_key.pub \
	${sysconfdir}/ssh/ssh_host_ed25519_key.pub \
	${sysconfdir}/ssh/ssh_host_rsa_key.pub \
"
