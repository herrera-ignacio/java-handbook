# 34: Use enums instead of `int` constants

> An *enumerated type* is a type whose legal values consist of a fixed set of constants, such as the seasons of the year, the planets in the solar system, or the suits in a deck of playing cards.

* `int` enum pattern
* `enum` type pattern
* Strategy `enum` pattern

## `int` enum pattern

Before enum types were added to the language, a common pattern for representing enumerated types was to declare a group of named `int` constants, one for each member of the type:

```java
// The int enum pattern - severely deficient!
public static final int APPLE_FUJI = 0;
public static final int APPLE_PIPPIN = 1;
public static final int APPLE_GRANNY_SMITH = 2;

public static final int ORANGE_NAVEL = 0;
public static final int ORANGE_TEMPLE = 1;
public static final int ORANGE_BLOOD = 2;
```

* Provides no type safety and little expressiveness.
  * Compiler won't complain if you pass an apple to a method that expects an orange, compare apples to oranges with the `==` operator, etc...

* No namepsace. Thus you need to use prefixes for preventing name clashes when two int enum groups have identically named constants (e.g., `ELEMENT_MERCURY` and `PLANET_MERCURY`).

* *Constant variables*. if the value associated with an `int` enum is changed, its clients must be recompiled.

* No reliable way to iterate over all the `int` enum constants in a group, or even to obtain the size of it.

## `enum` type pattern

```java
public enum Apple { FUHI, PIPPIN, GRANNY_SMITH }
public enum Orange { NAVEL, TEMPLE, BLOOD }
```

* Full-fledged classes that export one instance for each enumeration constant via a public static final field.

* *Instance-controlled*, generalization of *singletons*, because clients can neither create instances of an enum type nor extend it, there can be no instances but the declared enum constants.

* Compile-time type safety.

* Each type has its own namespace.

* Let you add arbitrary methods and fields and implement arbitrary interfaces. Thus an enum type can start life as a simple collection of enum constants and evolve over time into a full-featured abstraction.

* They implement high-quality implementations of all the `Object` methods. The

* They implement `Comparable` and `Serializable`.

* Switches on enums are good for augmenting enum types with constant-specific behavior.

* Use enums any time you need a set of constants whose members are known at compile time. It is not necessary that the set of constants in an enum type stay fixed for all time.

```java
public enum Planet {
  MERCURY(3.302e+23, 2.439e6),
  VENUS(4.869e+24, 6.052e6),
  EARTH(5.975e+24, 6.378e6),
  MARS(6.419e+23, 3.393e6),
  JUPITER(1.899e+27, 7.149e7),
  SATURN(5.685e+26, 6.027e7),
  URANUS(8.683e+25, 2.556e7),
  NEPTUNE(1.024e+26, 2.477e7);

  private final double mass; // kilograms
  private final double radius; // meters
  private final double surfaceGravity; // m/s^2

  // Universal gravitational constant in m^3/kg s^2
  private static final double G = 6.67300E-11;

  // Constructor
  Planet(double mass, double radius) {
    this.mass = mass;
    this.radius = radius;
    surfaceGravity = G * mass / (radius * radius);
  }

  public double mass() { return mass; }
  public double radius() { return radius; }
  public double surfaceGravity() { return surfaceGravity; }

  public double surfaceWeight(double mass) {
    return mass * surfaceGravity; // F = ma
  }
}
```

### Strategy `enum` pattern

You can use this pattern for constant-specific calculations, eliminating the need for a `switch` statement or constant-specific method implementation spread around classes. While this pattern is less concise than the `switch` statement, it is safer and more flexible.

```java
// The strategy enum pattern
enum PayrollDay {
  MONDAY(WEEKDAY), TUESDAY(WEEKDAY), WEDNESDAY(WEEKDAY),
  THURSDAY(WEEKDAY), FRIDAY(WEEKDAY),
  SATURDAY(WEEKEND), SUNDAY(WEEKEND);

  private final PayType payType;

  PayrollDay(PayType payTime) { this.payType = payType; }

  int pay(int minutesWorked, int payRate) {
    return payType.pay(minutesWorked, payRate);
  }

  // The strategy enum type
  private enum PayType {
    WEEKDAY {
      int overtimePay(int minsWorked, int payRate) {
        return minsWorked <= MINS_PER_SHIFT ? 0 : (minsWorked - MINS_PERSHIFT) * payRate / 2;
      }
    },
    WEEKEND {
      int overtimePay(int minsWorked, int payRate) {
        return minsWorked * payRate / 2;
      }
    };
  }

  abstract int overtimePay(int mins, int payRate);
  private static final int MINS_PER_SHIFT = 8 * 60;

  int pay(int minsWOrked, int payRate) {
    int basePay = minsWorked * payRate;
    return basePay + overtimePay(minsWorked, payRate);
  }
}
```
