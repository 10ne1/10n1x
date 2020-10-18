LICENSE="MIT"

inherit image

IMAGE_INSTALL += " \
	alsa-utils \
	alsacap \
	bash-completion \
	base-files \
	base-passwd \
	binutils \
	coreutils \
	curl \
	dhcpcd \
	e2fsprogs-resize2fs \
	file \
	findutils \
	gawk \
	git \
	grep \
	gzip \
	iproute2 \
	inetutils-ping \
	less \
	libcap-bin \
	net-tools \
	netbase \
	nethogs \
	nfs-utils \
	ntpsec \
	openssh-ssh \
	openssh-sshd \
	openssh-scp \
	opkg \
	parted \
	procps \
	sed \
	shplay \
	sinit \
	sshfs-fuse \
	strace \
	sudo \
	smdev \
	rsyslog \
	rt-tests \
	tar \
	util-linux \
	util-linux-agetty \
	update-alternatives-opkg \
	user-conf \
	vim-tiny \
	which \
	iw \
	wpa-supplicant \
"

IMAGE_INSTALL_append_rock-pi-4 += "\
	linux-firmware-bcm43456 \
"

IMAGE_FEATURES += "package-management"
