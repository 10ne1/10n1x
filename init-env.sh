TEMPLATECONF=${TEMPLATECONF:-$PWD/meta/conf}
BITBAKEDIR=${BITBAKEDIR:-$PWD/bitbake}
BUILDDIR=${BUILDDIR:-$PWD/build}

export PS1="(bb) $PS1"
export TEMPLATECONF
source $PWD/layers/openembedded-core/oe-init-build-env
