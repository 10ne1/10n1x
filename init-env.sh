TEMPLATECONF=${TEMPLATECONF:-$PWD/layers/meta-10n1x/conf}
BITBAKEDIR=${BITBAKEDIR:-$PWD/bitbake}
BUILDDIR=${BUILDDIR:-$PWD/build}

export PS1="(bb) $PS1"
export TEMPLATECONF
source $PWD/layers/openembedded-core/oe-init-build-env

kill "$(ps aux | grep -i python3 | head -n1 | awk '{print $2}')" 2>&1 >/dev/null
pushd tmp-musl/deploy/ipk >/dev/null 2>&1
python3 -m http.server >/dev/null 2>&1 &
popd >/dev/null 2>&1
