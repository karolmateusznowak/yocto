SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "Recipe created by bitbake-layers"
LICENSE = "MIT"

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*  Example recipe created by bitbake-layers   *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build

S = "${WORKDIR}"

inherit useradd

# echo -n knw | mkpasswd -m sha512crypt -s | sed -e 's,\$,\\$,g'
KNW_USER_NAME = "knw"
KNW_USER_GROUP = "knw"
KNW_USER_PASSWORD = "\$6\$AgSux3MUxIAxRpyc\$JQMUaAPwbuVnaJM/c6gjw9IeVuJpsTMr6hRochzLKi/Fb.Op3PUJouggeraRLH/uuOQJjeUeVtQKqrJ2fcOyR0"
KNW_USER_HOME_DIR = "/home/${KNW_USER_NAME}"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system ${KNW_USER_GROUP}"
USERADD_PARAM:${PN} = "--system -g ${KNW_USER_GROUP} --password '${KNW_USER_PASSWORD}' --shell /bin/bash --home-dir ${KNW_USER_HOME_DIR} ${KNW_USER_NAME}"

do_install () {
    mkdir -p ${D}${KNW_USER_HOME_DIR}
    chown -R ${KNW_USER_NAME}:${KNW_USER_GROUP} ${D}${KNW_USER_HOME_DIR}
}

FILES:${PN} = "${KNW_USER_HOME_DIR}"
