pkg_postinst_ontarget_${PN}() {
    setcap cap_net_raw,cap_net_admin=eip /usr/sbin/nethogs
}
