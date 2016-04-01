#!/bin/sh

. ./vm.properties

eval "VBoxManage snapshot '$vm_db_name' restore '$vm_db_snapshot'"