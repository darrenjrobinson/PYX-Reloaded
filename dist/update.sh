#!/usr/bin/env bash
cd "$(dirname "$0")"
sudo mv ./PYX-Reloaded.jar ./PYX-Reloaded.old.jar
sudo java -jar ./PYX-Reloaded.old.jar --update
sudo rm ./PYX-Reloaded.old.jar