TEMPLATECONF=${TEMPLATECONF:-$PWD/sources/meta-ionel/conf}
BITBAKEDIR=${BITBAKEDIR:-$PWD/sources/bitbake}
BUILDDIR=${BUILDDIR:-$PWD/build}

if [ ! -f $BUILDDIR/conf/bblayers.conf ]; then
    sed -i.bak "s?##PWD##?$PWD?g" $TEMPLATECONF/bblayers.conf.sample
fi

function cleanup() {
    if [ -f $TEMPLATECONF/bblayers.conf.sample.bak ]; then
	mv $TEMPLATECONF/bblayers.conf.sample.bak $TEMPLATECONF/bblayers.conf.sample
    fi
}

trap "cleanup" EXIT

export PS1="(bb) $PS1"
export TEMPLATECONF
source $PWD/sources/openembedded-core/oe-init-build-env

cleanup
