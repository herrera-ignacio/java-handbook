# 5: Prefer dependency injection to hardwiring resources

Many classes depend on one or more underlying resources.

It's not uncommon to see such classes implemented as static utility classes.

```java
// Innapropriate use of static utility: inflexible & untestable!

public class SpellChecker {
  private static final Lexicon dictionary = ...;

  private SpellCheck() {} // Noninstantiable

  public static boolean isValid(String word) { ... }

  public static List<String> suggestions(String typo) { ... }
}
```

Similary, it's not uncommon to see them implemented as singletons.


```java
// Innapropriate use of singleton: inflexible & untestable!

public class SpellChecker {
  private static final Lexicon dictionary = ...;

  private SpellCheck() {} // Noninstantiable

  public static SpellChecker INSTANCE = new SpellChecker(...);

  public static boolean isValid(String word) { ... }

  public static List<String> suggestions(String typo) { ... }
}
```

Neither of these approaches is satisfactory, because **they assume that there is only one dictionary worth using**.

> In practice, each language has its own dictionary, and special dictionaries are used for special vocabularies. Also, it may be desirable to use a special dictionary for testing.

**Static utility classes and singletons are inappropriate for classes whose behavior is parameterized byh an underlying resorce**. You could try to have `SpellChecker` support multiple dictionaries by making the `dictionary` field nonfinal and adding a method to change it in an existing spell checker, but this would be awkward, error-prone, and unworkable in a concurrent setting.

**We want to ability to support multiple instances of the class, each of which uses the resource desired by the client**.

A simple pattern that satisfies this requirement is to **pass the resource into the constructor when creating a new instance**. This is one form of *dependency injection*: the dictionary is a *dependency* of the spell checker and is *injected* into the spell checker when it is created.

```java
// Dependency injection provides flexibility and testability
public class SpellChecker {
  private final Lexicon dictionary;

  public SpellChecker(Lexicon dictionary) {
    this.dictioanry = Objects.requireNonNull(dictionary);
  }

  public boolean isValid(String word) { ... }

  public List<String> suggestions(String typo) { ... }
}
```

A useful variant of the pattern is to pass a resource *factory* to the constructor. Such factories embody the *Factory Method* pattern (Gamma95).

```java
// bounded wildcard type to allow the client to pass in a factory that creates any subtype of a specified type
Mosaic create(Supplier<? extends Tile> tileFactory) { ... }
```

Dependency injection can clutter up large projects, which typically contain thousands of dependencies. This clutter can be all but eliminated by using a *dependency injection framework*, such as Dagger, Guice, or Spring.

In summary, **do not use a singleton or static utility class to implement a class that depends on one or more underlying resources whose behavior affects that of the class, and do not have the class create these resources directly**. Instead, **pass the resources, or factories to create them, into the constructor** (or static factory or builder).

> The practice of dependency injection will greatly enhance the flexibility, reusability, and testability of a class.
