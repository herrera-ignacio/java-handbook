# Kafka local development

> Source: https://github.com/dilipsundarraj1/kafka-for-developers-using-spring-boot/blob/master/SetUpKafka.md

## Start Zookeeper and Kafka Broker

We'll see how to run one Broker.

1. Start up the Zookeper

```
.\zookeeper-server-start.bat ..\..\config\zookeeper.properties

./zookeeper-server-start.sh ../config/zookeeper.properties
```

2. (Optional) Update `server.properties`

```
listeners=PLAINTEXT://localhost:9092
auto.create.topics.enable=false
```

3.. Start up Kafka Broker

```
kafka-server-start.bat ..\..\config\server.properties

./kafka-server-start.sh ../config/server.properties
```

## Create a Topic

```
kafka-topics.bat --create --topic test-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 4

./kafka-topics.sh --create --topic test-topic -zookeeper localhost:2181 --replication-factor 1 --partitions 4
```

## Instantiate a Console Producer

### Without Key

```
kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic

./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic
```

### With Key

```
kafka-console-producer.bat --broker-list localhost:9092 --topic test-topic --property "key.separator=-" --property "parse.key=true"

./kafka-console-producer.sh --broker-list localhost:9092 --topic test-topic --property "key.separator=-" --property "parse.key=true"
```

> With the `-` separator, the message `A-test` for example will have key `A`. Remember that messages with the same key will arrive to the same partition, thus preserving order.

## Instantiate a Console Consumer

### Without Key

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning

./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```

### With Key

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator=-" --property "print.key=true""

./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning -property "key.separator=-" --property "print.key=true"
```

### With Consumer Group

```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --group <group-name>

./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --group <group-name>
```

## Setting up Multiple Kafka Brokers

* Update `server.properties`.

```
broker.id=<unique-broker-d>
listeners=PLAINTEXT://localhost:<unique-port>
log.dirs=/tmp/<unique-kafka-folder>
auto.create.topics.enable=false
```

* Start new Broker

```
./kafka-server-start.sh ../config/server-1.properties
./kafka-server-start.sh ../config/server-2.properties
# etc ...
```

## Advanced Kafka CLI Operations

### List topics in a cluster

```
kafka-topics.bat --zookeeper localhost:2181 --list
```

### Describe topic

```
kafka-topics.bat --zookeeper localhost:2181 --describe
kafka-topics.bat --zookeeper localhost:2181 --describe --topic <topic-name>
```

### Delete a topic

```
kafka-topics.bat --zookeeper localhost:2181 --delete --topic <topic-name>
```

### View Consumer Groups

```
kafka-consumer-groups.bat --bootstrap-server localhost:9092 --list
kafka-consumer-groups.bat --bootstrap-server localhost:9092 --describe --group console-consumer-27773
```