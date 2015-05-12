#!/bin/sh

cd /var/puppetory/

mvn clean install

service tomcat7 stop

if [ -d /var/lib/tomcat7/webapps/ROOT ]; then
    rm -r /var/lib/tomcat7/webapps/ROOT
fi

cp target/puppetory-*.war /var/lib/tomcat7/webapps/ROOT.war

chown tomcat7:tomcat7 /var/lib/tomcat7/webapps/ROOT.war

service tomcat7 start