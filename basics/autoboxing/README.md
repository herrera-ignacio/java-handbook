# Autoboxing and Unboxing

*Autoboxing* is the automatic that the Java compiler makes between the *primitive* types and their corresponding *object wrapper classes*. if the conversion goes the other way, this is called *unboxing*.

> For example, *autoboxing* is converting an `int` to an `Integer`, a `double` to a `Double`, and so on.

The Java compiler applies *autoboxing* when a *primitive* value is (the equivalent happens with *unboxing* and *object wrapper classes*):

* Passed as a parameter to a method that expects an object from the corresponding wrapper class.
* Assigned to a variable of the corresponding wrapper class.

|Primitive type |	Wrapper class |
| - | - |
|boolean |	Boolean |
|byte |	Byte |
|char |	Character |
|float |	Float |
|int |	Integer |
|long |	Long |
|short |	Short |
|double |	Double |

## Autoboxing example

```java
// Autoboxing to 'Character' object wrapper class from pritive 'a'.
Character ch = 'a';
```

Another example using Generics:

```java
List<Integer> li = new ArrayList<>();
for (int i = 1; i < 50; i += 2)
  li.add(i);
```

Although you add the `int` values as primitive types to `li`, rather than `Integer` objects, the code compiles. The compiler converts the previous code to the following at runtime:

```java
List<Integer> li = new ArrayList<>();
for (int i = 1; i < 50; i += 2)
  li.add(Integer.valueOf(i));
```

## Unboxing example

```java
// Original code
public static int sumEven(List<Integer> li) {
    int sum = 0;
    for (Integer i: li)
        if (i % 2 == 0)
            sum += i;
        return sum;
}

// Compiler output
public static int sumEven(List<Integer> li) {
    int sum = 0;
    for (Integer i : li)
        if (i.intValue() % 2 == 0)
            sum += i.intValue();
        return sum;
}
```

Consider this other *unboxing* example:

```java
import java.util.ArrayList;
import java.util.List;

public class Unboxing {

    public static void main(String[] args) {
        Integer i = new Integer(-8);

        // 1. Unboxing through method invocation
        int absVal = absoluteValue(i);
        System.out.println("absolute value of " + i + " = " + absVal);

        List<Double> ld = new ArrayList<>();
        ld.add(3.1416);    // Π is autoboxed through method invocation.

        // 2. Unboxing through assignment
        double pi = ld.get(0);
        System.out.println("pi = " + pi);
    }

    public static int absoluteValue(int i) {
        return (i < 0) ? -i : i;
    }
}
```

This last example will print:

```
absolute value of -8 = 8
pi = 3.1416
```
