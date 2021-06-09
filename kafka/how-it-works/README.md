# Kafka: How it works?

Kafka is a distributed system consisting of **servers** and **clients** that communicate via a high-performance TCP network protocol.

## Servers

Kafka is run as a cluster of one or more servers that can span multiple datacenters or cloud regions. Some of these servers form the **storage layer** called the **Brokers**. Other servers run **Kafka Connect** to continuously import and export data as event streams to integrate Kafka with your existing systems such as relational databases as well as other Kafka clusters.

> Kafka cluster is highly scalable and fault-tolerant: if any of its servers fails, the other servers will take over their work to ensure continuous operations without any data loss.

## Clients

Clients allow you to write distributed applications and mciroservices that read, write, and process streams of events in parallel, at scale, and in a fault-tolerant manner even in the case of network problems or machine failures.

Kafka ships with some such clients included, which are augmented by the Kafka community.

There are clients available for:

* Java and Scala (including the higher-level *Kafka Streams* library)
* Go
* Python
* C/C++
* Other programming languages and REST APIs
