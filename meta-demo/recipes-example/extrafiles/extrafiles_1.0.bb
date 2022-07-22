SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"

DEPENDS += "useradd"

S = "${WORKDIR}"

do_install () {
    mkdir -p ${D}/home/knw
    touch ${D}/home/knw/file
}

FILES:${PN} = "/home/knw/*"
