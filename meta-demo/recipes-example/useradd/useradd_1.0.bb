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
USER_NAME = "knw"
USER_GROUP = "knw"
USER_PASSWORD = "\$6\$AgSux3MUxIAxRpyc\$JQMUaAPwbuVnaJM/c6gjw9IeVuJpsTMr6hRochzLKi/Fb.Op3PUJouggeraRLH/uuOQJjeUeVtQKqrJ2fcOyR0"
USER_HOME_DIR = "/home/${USER_NAME}"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "--system ${USER_GROUP}"
USERADD_PARAM:${PN} = "--system -g ${USER_GROUP} --password '${USER_PASSWORD}' --shell /bin/bash --home-dir ${USER_HOME_DIR} ${USER_NAME}"

do_install () {
    install -d -o ${USER_NAME} -g ${USER_GROUP} ${D}${USER_HOME_DIR}
}

FILES:${PN} = "${USER_HOME_DIR}"
