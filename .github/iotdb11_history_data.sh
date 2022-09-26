#!/bin/sh

set -e

sh -c "wget -nv https://github.com/thulab/IginX-benchmarks/raw/main/resources/apache-iotdb-0.11.4-bin.zip"

sh -c "unzip apache-iotdb-0.11.4-bin.zip"

sh -c "sleep 10"

sh -c "ls ./"

sh -c "echo ========================="

sh -c "ls apache-iotdb-0.11.4"

sh -c "sudo cp -r apache-iotdb-0.11.4/ apache-iotdb2-0.11.4/"

sh -c "sudo sed -i 's/6667/6668/g' apache-iotdb2-0.11.4/conf/iotdb-engine.properties"

sudo sh -c "cd apache-iotdb-0.11.4/; nohup sbin/start-server.sh &"

sudo sh -c "cd apache-iotdb2-0.11.4/; nohup sbin/start-server.sh &"

sed -i "s/has_data=false/has_data=true#data_prefix=ln.wf01/g" conf/config.properties