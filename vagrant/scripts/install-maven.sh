#!/bin/sh

# install maven

if [ ! -f /apt-get-install-maven ]; then
    apt-get -y install maven && touch /apt-get-install-maven;
fi