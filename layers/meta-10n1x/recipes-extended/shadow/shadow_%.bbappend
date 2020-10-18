pkg_postinst_${PN}_append () {
	NEWLINE=$(cat $D/etc/shadow | awk -F: -v pass='$6$CGK6SHL6.PJyFk8O$jyOO8BxvxSUfQE6a50SQOmUn3ouAW.z03fWFg69il3bTp7BMFcmc.hZ23z3hPlxlJ2wfJzduFHVGR65NZDBbQ.' '$1 ~ /${USER}/ {$2 = pass; print $0}' OFS=:)
	sed -i '/${USER}/c\'"$NEWLINE" $D/etc/shadow
}
