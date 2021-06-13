# Kafka Recovery/Retry

## Producer

The first step is to have a minimun number of in-sync replicas, you can configure this using the `min.insync.replicas=<number>` property at the topic level:

```
kafka-topics.bat --alter --zookeeper localhost:2181 --topic library-events --config min.insync.replicas=2
```

Alternatively, you could configure this at the _Broker_ level.

Depending on the amount of replicas you are using, you can configure `kafka.producer.properties`: `acks: all|<number>`, `retries` and `retry.backoff.ms`, etc...

## Consumer

### Retry

![](2021-06-13-01-05-39.png)

### Recovery

When Retry max attemps have been reached (_exhausted_) then recovery process comes into play.

#### Type 1

![](2021-06-13-01-04-57.png)

#### Type 2

![](2021-06-13-01-05-13.png)