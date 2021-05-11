# Mixins

Define additional capability of subclasses, it is not thought to be instantiated by itself.

In Java they are achieved by interface inheritance.

```java
public class ElectricGuitar extends Instrument implements StringInstrument, ElectricInstrument, Comparable {
    // ...
}
```
