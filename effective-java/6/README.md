# 6: Avoid creating unnecessary objects

> The counterpoint of this item is [item 50 ("Don't reuse an existing object when you should create a new one")](../50) on **defensive copying**. Note that the **penalty for reusing an object when defensive copying is called for is far greater than the penalty for needlessly creating a duplicate object**. Failing o make defensive copies where required can lead to insidious bugs and security holes; creating objects unnecessarily merely affects style and performance.

**Reusing a single object** instead of creating a new functionally equivalent object can be both **faster and more stylish**.

> An object can always be reused if it is immutable ([Item 17](../17))

As an extreme example of what not to do, consider this statement:

```java
String s = new String("bikini"); // DON'T DO THIS!
```

The statement creates a new `String` instance each time it is executed, and none of those object creations is necessary. The argument of the `String` constructor `("bikini")` is itself a `String` instance, functionally identical to all of the objects created by the constructor.

```java
// Instead to this
String s = "bikini";
```

This version uses a single `String` instance, rather than creatng a new one each time it is executed. Furthermore, it is guaranteed that the object will be reused.

**Some object creations are much more expensive than others**. If you're going to need such an "expensive object" repeatedly, it may be advisable to **cache it for reuse**. Unfortunately, it's not always obvious when you're creating such an object.

This item should not be misconstrued to imply that object creation is expensive and should be avoided. On the contrary, the creation and reclamation of small objects whose constructors do little explicit work is cheap, especially on modern JVM implementations. **Creating additional objects to enhance the clarity, simplificity, or power of a program is generally a good thing**.

Conversely, avoiding object creation by maintaining your own *object pool* is a bad idea unless the objects in te pool are extremly heavyweight.

> The classic example of an object that *does* justify an object pool is a database connection. The cost of establishing the connection is sufficiently high that it makes sense to reuse these objects.

Generally speaking, maintaining your own object pools clutters your code, increases memory footprint, and harms performance. Modern JVM implementations have highly ptimized garbage collections that easily outperform such object pools on lightweight objects.

## Case study: Regular Expressions

Suppose you want to write a method to determine whether a string is a valid Roman numeral. Here's the easiest way to do this using a regular expression:

```java
// Performance can be greatly improved!
static boolean isRomanNumeral(String s) {
  return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" + ("(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"));
}
```

While `String.matches` is the easiest way to check if a string matches a regular expression, **it's not suitable for repeated use in performance-critical situations**.

> The problem is that it internally creates a `Pattern` instance for the regular expression and uses it only once, after which it becomes eligible for garbage collection. Creating a `Pattern` instance is expensive because it requires compiling the regular expression into a finite state machine.

To improve the performance, **explicitly compile the regular expression into a `Pattern` instance (which is immutable) as part of the class initialization, cache it, and reuse** the same instance for every invocation of the `isRomanNumeral` method.

```java
// Reusing expensive object for improved performance
public class RomanNumerals {
  private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})" + ("(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

  static boolean isRomanNumeral(String s) {
    return ROMAN.matcher(s).matches();
  }
}
```

The improved version of `isRomanNumeral` provides significant performance gains if invoked frequently.

If the class containing the improved version of the `isRomanNumeral` method is initialized but the method is never invoked, the field `ROMAN` will be intialized needlessly. It would be possible to improve this by *lazily initialization*.

## Case study: Adapters (Gamma95) / Views

When an object is immutable, it is obvious it can be reused safely, but there are other situations where it is far less obvious, even counterintuitive.

Consider the case of *adapters* (Gamma95), also known as *views*. An adapter is **an object that delegates to a backing object, providing an alternative interface**. Because an adapter has no state beyond that of its backing object, there's no need to create more than one instance of a given adapter to a given object.

For exmple, the `keySet` method of the `Map` interface returns a `Set` view of the `Map` object, consisting of all the keys in the map. Naively, it would seem that every call to `keySet` would have to create a new `Set` instance, but every call to `keySet` on a given `Map` object may return the same `Set` instance.

This is because although the returned `Set` instance is typically mutable, all of the returned objects are functionally identical: when one of the returned object changes, so do all the others, beccause they're all backed by the same `Map` instance. While it is largely harmless to create multiple instances of the `keySet` view object, it is unnecessary and has no benefits.

## Case study: Autoboxing

Autoboxing allows the programmer to mix primitive and boxed primitive types, boxing and unboxing automatically as needed. **Autoboxing blurs but does not erase the distinction between primitive and boxed primitive types**. There are subtle semantic distinctions and not-so-subtle performance differences ([Item 61](../61)).

Consider the following method, which calculates the sum of all the positive `int` values. To do this, the program has to use `long` arithmetic because an `int` is not big enough to hold the sum of all the positive `int` values.

```java
// Hideously slow! Try to spot the object creation
private static long sum() {
  Long sum = 0L;

  for (long i = 0; i <= Integer.MAX_VALUE; i++)
    sum += i;
  
  return sum;
}
```

This program gets the right answer, but it is *much* slower than it should be, due to a one-character typographical error.

The variable `sum` is declared as a `Long` instead of a `long`, which means that the program constructs about $2^{31}$ unnecessary `Long` instances (roughly one for each time the `long i` is added to the `Long sum`). Changing the declaration of `sum` from `Long` to `long` reduces the runtime significantly.

**Prefer primitives to boxed primitives, and watch out for unintentional autoboxing**.
