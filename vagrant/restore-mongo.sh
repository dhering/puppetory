#!/bin/sh

echo "### restore mongo DB"

mongorestore -h localhost --db puppetory --drop /opt/data/puppetory_mongodb_dump/ && sudo touch /mongo-restore; 

update-rc.d restore-mongo.sh remove

rm /etc/init.d/restore-mongo.sh

echo "### finish restoring"