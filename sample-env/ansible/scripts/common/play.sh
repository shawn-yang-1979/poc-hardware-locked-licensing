#!/bin/bash
cd ../../
ansible-playbook -i inventories/hosts $1.yml --extra-vars "$2" $3 
cd `dirname $0`