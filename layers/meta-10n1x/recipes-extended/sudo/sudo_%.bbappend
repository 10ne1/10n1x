do_install_append () {
    echo "${IONEL_USER} ALL=(ALL) NOPASSWD: ALL" > ${D}${sysconfdir}/sudoers.d/001_first
}
