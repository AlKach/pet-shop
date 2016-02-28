#!/bin/sh

. vm.properties

echo '----------------------------'
echo $vm_db_name
echo '----------------------------'
echo

eval "VBoxHeadless -s '$vm_db_name'"