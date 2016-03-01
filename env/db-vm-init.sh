#!/bin/sh

. vm.properties

cp _prepare-db.sh _prepare-db-actual.sh

eval "sed -i -e 's/__SERVICE__/$db_service/g' _prepare-db-actual.sh"
eval "sed -i -e 's/__USER__/$db_username/g' _prepare-db-actual.sh"
eval "sed -i -e 's/__PASSWORD__/$db_password/g' _prepare-db-actual.sh"

eval "ssh root@$vm_db_host 'sh -v -s' < _prepare-db-actual.sh"

rm _prepare-db-actual.sh