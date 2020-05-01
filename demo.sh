#! /bin/sh

bin/zookeeper-server-start.sh config/zookeeper.properties > zkdemo.log &
ZK=$!
sleep 1s
bin/kafka-server-start.sh config/server.properties > server0.log &
BROKER0=$!
sleep 2s
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4 --topic yellow-taxis
sleep 5m

kill $BROKER0
kill $ZK

rm -rf /tmp/kafka-logs
rm -rf /tmp/zookeeper
