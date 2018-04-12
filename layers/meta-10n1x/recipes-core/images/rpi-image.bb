# Base this image on core-image-minimal
#include recipes-core/images/core-image-minimal.bb

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
	openssh-ssh \
	openssh-sshd \
	openssh-scp \
	opkg \
	polkit \
	procps \
	psmisc \
	scw1play \
	sed \
	sinit \
	sshfs-fuse \
	strace \
	sudo \
	smdev \
	syslog-ng \
	ubase \
	update-alternatives-opkg \
	user-conf \
	vim-tiny \
	wget \
	which \
	wireless-tools \
	wpa-supplicant \
"

IMAGE_FEATURES += "package-management"

IMAGE_OVERHEAD_FACTOR = "1.8"

inherit image
