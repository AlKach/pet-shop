#!/bin/sh

. ./vm.properties

cp _prepare-backup.sh _prepare-backup-actual.sh

eval "sed -i -e 's:__BACKUP__:$db_backup_name:g' _prepare-backup-actual.sh"
eval "sed -i -e 's:__DATA__:$db_data_location:g' _prepare-backup-actual.sh"

eval "ssh root@$vm_db_host 'sh -v -s' < _prepare-backup-actual.sh"

eval "scp root@$vm_db_host:~/$db_backup_name $db_backup_name"

rm _prepare-backup-actual.sh