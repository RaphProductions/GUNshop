#!/bin/bash

## This is a small utility to put the plugin JARfile onto a Minecraft server over Secure Copy Protocol (SCP)
## Please replace "raphm@192.168.1.27:/home/raphm/srv/plugins/gunshop.jar" with the destinative (example: foo@256.256.256.256:/gunshop.jar (copies to /gunshop.jar on 256.256.256.256) using the "foo" user)

./gradlew jar

sshpass -f ../pass scp ./build/libs/GUNshop-1.0-SNAPSHOT.jar raphm@192.168.1.27:/home/raphm/srv/plugins/gunshop.jar