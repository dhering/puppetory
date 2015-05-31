#!/bin/sh

# copy mongodb data files and install mongodb

if [ ! -f /apt-get-install-mongodb ]; then
    mkdir -p /var/lib/mongodb
    cp /var/puppetory/data/backup/four_server/* /var/lib/mongodb

    apt-get -y install mongodb && touch /apt-get-install-mongodb;

    sed -i 's/bind_ip = 127.0.0.1/bind_ip = 0.0.0.0/g' /etc/mongodb.conf
    service mongodb restart
fi