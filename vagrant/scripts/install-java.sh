#!/bin/sh

# install openjdk-7-jre

if [ ! -f /apt-get-install-openjdk-7-jre ]; then
    apt-get -y install openjdk-7-jre && touch /apt-get-install-openjdk-7-jre;
fi