#!/bin/bash

mkdir -p target/logs
docker run -it --rm -v `pwd`/target/logs/:/var/log/loadgen/ -e APP_BASE_URL=http://host.docker.internal:8080 koduki/loadgen
