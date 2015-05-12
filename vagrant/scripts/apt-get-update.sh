#!/bin/sh

# update apt database

if [ ! -f /apt-get-update ]; then
    apt-get -y update && touch /apt-get-update;
fi