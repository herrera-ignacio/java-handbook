# Apache Kafka

Kafka combines three key capabilities so you can implement your *use cases* for *event streaming* end-to-end with a single solution:

1. To **publish** (write) and **subscribe to** (read) streams of events, including continuous import/export of your data from other systems.

2. To **store** streams of events durably and reliably for as long as you want.

3. To **process** streams of events as they occur or retrospectively.

And all this functionality is provided in a distributed, highly scalable, elastic, fault-tolerant, and secure manner. Kafka can be deployed on bare-metal hardware, virtual machines, and containers, and on-premises as well as in the cloud. You can choose between self-managing your Kafka environments and using fully managed services offered by a variety of vendors.

![](2021-06-09-16-50-07.png)

## Kafka vs Traditional message System

| Traditional Messaging System                               | Kafka Streaming Platform                                       |
|------------------------------------------------------------|----------------------------------------------------------------|
| Transient message persistance                              | Stores events based on a retention time. Events are immutable. |
| Brokers responsibility to keep track of consumed messages. | Consumers responsiblity to keep track of consumed messages.    |
| Target a specific Consumer.                                | Any Consumer can access a message from the Broker.             |
| Not a distributed system.                                  | Distributed streaming system.                                  |

## Use Cases

* To process payments and financial transactions in real-time, such as in stock exchanges, banks, and insurances.

* To track and monitor cars, trucks, fleets, and shipments in real-time, such as in logistics and the automotive industry.

* To continuously capture and analyze sensor data from IoT devices or other equipment, such as in factories and wind parks.

* To collect and immediately react to customer interactions and orders, such as in retail, the hotel and travel industry, and mobile applications.

* To monitor patients in hospital care and predict changes in condition to ensure timely treatment in emergencies.

* To connect, store, and make available data produced by different divisions of a company.

* To serve as the foundation for data platforms, event-driven architectures, and microservices.

![](2021-06-08-12-13-18.png)
