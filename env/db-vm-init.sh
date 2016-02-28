#!/bin/sh

. vm.properties

cp prepare-db.sh prepare-db-actual.sh

eval "sed -i -e 's/__SERVICE__/$db_service/g' prepare-db-actual.sh"
eval "sed -i -e 's/__USER__/$db_username/g' prepare-db-actual.sh"
eval "sed -i -e 's/__PASSWORD__/$db_password/g' prepare-db-actual.sh"

eval "ssh root@$vm_db_host 'sh -v -s' < prepare-db-actual.sh"

rm prepare-db-actual.sh