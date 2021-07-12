# In public classes, use accessor methods, not public fields

* Overview
* In Depth
* Immutable fields

## Overview

In summary, **public classes should never expose mutable fields**. It is less harmful, though still questinoable, for public classes to expose immutable fields. It is, however, sometimes desirable for package-private or private nested classes to expose fields, whether mutable or immutable.

## In-Depth

Some degenerate classes serve no purpose other than to group instance fields:

```java
// Degenerate classes like this should not be public!
class Point {
  public double x;
  public double y;
}
```

Thse classes do not offer the benefits of *encapsulation* ([Item 15](../15)). You can't change the representation without changing the API, you can't enforce invariants, and you can't take auxiliary action when a field is accessed.


```java
// Encapsulation of data by accessor methods and mutators
class Point {
  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() { return x; }
  public double getY() { return y; }

  public void setX(double x) { this.x = x; }
  public void setY(double y) { this.y = y; }
}
```

This is correct when it comes to public classes to **preserve the flexibility to change the class's internal representation**. However, if a class is *package-private* or is a *private nested class*, there is nothing inherently wrong with exposing its data fields.

> While the client code is tied to the class's internal representation, this code is confined to the package/class containing the class. You can make a change in representation without touching any code outside the package/class.

## Immutable Fields

While it is never a good idea for a public class to expsoe fields directly , it is less harmful if the fields are immutable. You can't change the implementation of such a class withoug changing its API, and you can't take auxiliary actions when a field is read, but **you can enforce invariants**

For example, this class guarantees that each instance represents a valid time:

```java
public final class Time {
  private static final int HOURS_PER_DAY = 24;
  private static final int MINUTES_PER_HOUR = 60;

  public final int hour;
  public final int minute;

  public Time(int hour, int minute) {
    if (hour < 0 || hour >= HOURS_PER_DAY)
      throw new IllegalArgumentException("Hour: " + hour);
    if (minute < 0 ||  minute >= MINUTES_PER_HOUR)
      throw new IllegalArgumentException("Min: " + minute);
    this.hour = hour;
    this.minute = minute;
  }

  // ... Remainder ommited
}
```
