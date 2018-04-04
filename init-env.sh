TEMPLATECONF=${TEMPLATECONF:-$PWD/meta/conf}
BITBAKEDIR=${BITBAKEDIR:-$PWD/bitbake}
BUILDDIR=${BUILDDIR:-$PWD/build}

if [ ! -f $BUILDDIR/conf/bblayers.conf ]; then
    sed "s?##PWD##?$PWD?g" $TEMPLATECONF/bblayers.conf.sample > $BUILDDIR/conf/bblayers.conf
fi

export PS1="(bb) $PS1"
export TEMPLATECONF
source $PWD/layers/openembedded-core/oe-init-build-env
