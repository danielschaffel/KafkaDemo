# KafkaDemo

## To clone the repo:
```bash
$  git clone https://github.com/danielschaffel/KafkaDemo.git
```

## To run Kafka:
-  Move the demo.sh file to where you unzipped kafka
-  ```bash
    $ ./demo.sh topic-name
    ```

## To run the Consumer:
```bash
$ mvn exec:java -Dexec.mainClass=yu.edu.ad.Consumer -Dexec.args="config-file topic-name"
```
## To run the Producer:
```bash
mvn exec:java -Dexec.mainClass=yu.edu.ad.Producer -Dexec.args="config-file path-to-input-file topic-name"
```

## Consumer Status
To see what is going on with the consumer:
```bash
$ bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --all-groups --describe
```
## Notes
### Running Order
1. Demo script to run Kafka broker
2. Start the consumers
3. Start the producer
4. Get consumer status 
   
### Multiple Consumers
consumer.props and consumer1.props are part of group1, while consumer2.props and consumer3.props are part of group2.

My computer couldn't handle to many consumers in a group at the same time so only one consumer from each group was running at a time.
If you kill the two that are running then the others ones will start running pretty quickly.