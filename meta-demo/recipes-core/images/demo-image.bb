SUMMARY = "A small image just capable of allowing a device to boot."
LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-x11 \
    kernel-modules \
    xfce4-terminal \
    ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "
IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE:append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "", d)}"


inherit features_check
REQUIRED_DISTRO_FEATURES = "x11 opengl"

QB_MEM = "-m 2G"

IMAGE_INSTALL:append = "\
    bash \
    sudo \
    example \
"

# echo -n toor | mkpasswd -m sha512crypt -s | sed -e 's,\$,\\$,g'
ROOT_PASSWORD="\$6\$/04x.drsD2EQLEP9\$TM9hwfLgQbBSknvj2/WLErtB6ATbp4.lQ1kVTxJizvn6hMODouGH9DdRJqyM7VJdw.24FIdjVbvTGaelIyF9O0"

inherit extrausers
EXTRA_USERS_PARAMS = "usermod -p '${ROOT_PASSWORD}' root;"
