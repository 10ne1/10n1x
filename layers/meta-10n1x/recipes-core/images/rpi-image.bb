LICENSE="MIT"

IMAGE_INSTALL += " \
	alsa-utils \
	bash-completion \
	base-files \
	base-passwd \
	coreutils \
	dhcpcd \
	file \
	findutils \
	gawk \
	git \
	grep \
	gzip \
	iproute2 \
	iputils-ping \
	less \
	libcap-bin \
	net-tools \
	netbase \
	nethogs \
	ntpsec \
	openssh-ssh \
	openssh-sshd \
	openssh-scp \
	opkg \
	procps \
	scw1play \
	sed \
	sinit \
	sshfs-fuse \
	strace \
	sudo \
	smdev \
	rsyslog \
	rt-tests \
	util-linux \
	util-linux-agetty \
	update-alternatives-opkg \
	user-conf \
	vim-tiny \
	wget \
	which \
	wireless-tools \
	wpa-supplicant \
"

IMAGE_FEATURES += "package-management"

IMAGE_OVERHEAD_FACTOR = "5"

inherit image

set_user_pass() {
	NEWLINE=$(cat ${IMAGE_ROOTFS}/etc/shadow | awk -F: -v pass='$6$CGK6SHL6.PJyFk8O$jyOO8BxvxSUfQE6a50SQOmUn3ouAW.z03fWFg69il3bTp7BMFcmc.hZ23z3hPlxlJ2wfJzduFHVGR65NZDBbQ.' '$1 ~ /${USER}/ {$2 = pass; print $0}' OFS=:)
	sed -i '/${USER}/c\'"$NEWLINE" ${IMAGE_ROOTFS}/etc/shadow
}

ROOTFS_POSTPROCESS_COMMAND += "set_user_pass; "

DEPENDS_remove += "${MLPREFIX}qemuwrapper-cross"
