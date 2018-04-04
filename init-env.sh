TEMPLATECONF=${TEMPLATECONF:-$PWD/meta/conf}
BITBAKEDIR=${BITBAKEDIR:-$PWD/sources/bitbake}
BUILDDIR=${BUILDDIR:-$PWD/build}

if [ ! -f $BUILDDIR/conf/bblayers.conf ]; then
    sed "s?##PWD##?$PWD?g" $TEMPLATECONF/bblayers.conf.sample > $BUILDDIR/conf/bblayers.conf
fi

export PS1="(bb) $PS1"
export TEMPLATECONF
source $PWD/sources/openembedded-core/oe-init-build-env
