#!/bin/sh

. vm.properties

cp _restore-backup.sh _restore-backup-actual.sh

eval "sed -i -e 's:__BACKUP__:$db_backup_name:g' _restore-backup-actual.sh"
eval "sed -i -e 's:__DATA__:$db_data_location:g' _restore-backup-actual.sh"

eval "scp $db_backup_name root@$vm_db_host:~/$db_backup_name"

eval "ssh root@$vm_db_host 'sh -v -s' < _restore-backup-actual.sh"

rm _restore-backup-actual.sh