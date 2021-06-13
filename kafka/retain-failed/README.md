# Retain/Recover Failed Records in Kafka Producer

## Approach 1

![](2021-06-13-01-31-34.png)

1. You need to store the failed records.
2. A Scheduler will pull faile records and retry to publish them.

## Approach 2

![](2021-06-13-01-32-30.png)

Instead of using a database to persist failed records, you can use a `recovery-topic` to *"re-publish"* the failed records through a specific Kafka Consumer than can handle feeding back the record into a Producer.
