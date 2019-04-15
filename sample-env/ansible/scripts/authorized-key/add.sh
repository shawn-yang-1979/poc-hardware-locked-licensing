#!/bin/bash
rm -f ~/.ssh/known_hosts
cd ../common
./play.sh authorized-key-add "" --ask-pass