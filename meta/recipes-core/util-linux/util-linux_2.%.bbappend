pkg_postinst_ontarget_${PN}() {
    setcap CAP_SYS_NICE=+eip ${bindir}/chrt.${BPN}
}
