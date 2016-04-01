#!/bin/sh

. ./vm.properties

ssh_host=$vm_db_host

. _add-ssh-key.sh

cp _prepare-db.sh _prepare-db-actual.sh

eval "sed -i -e 's:__SERVICE__:$db_service:g' _prepare-db-actual.sh"
eval "sed -i -e 's:__USER__:$db_username:g' _prepare-db-actual.sh"
eval "sed -i -e 's:__PASSWORD__:$db_password:g' _prepare-db-actual.sh"
eval "sed -i -e 's:__TEST_SERVICE__:$db_test_service:g' _prepare-db-actual.sh"
eval "sed -i -e 's:__TEST_USER__:$db_test_username:g' _prepare-db-actual.sh"
eval "sed -i -e 's:__TEST_PASSWORD__:$db_test_password:g' _prepare-db-actual.sh"
eval "sed -i -e 's:__CONFIG__:$db_config_location:g' _prepare-db-actual.sh"

eval "ssh root@$vm_db_host 'sh -v -s' < _prepare-db-actual.sh"

rm _prepare-db-actual.sh