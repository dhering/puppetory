#!/bin/sh

# install tomcat7

if [ ! -f /apt-get-install-tomcat7 ]; then
    apt-get -y install tomcat7 && touch /apt-get-install-tomcat7;

    cp /var/puppetory/vagrant/conf/tomcat/setenv.sh /usr/share/tomcat7/bin
    chmod 755 /usr/share/tomcat7/bin/setenv.sh
fi