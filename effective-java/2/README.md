# 2: Consider a builder when faced with many constructor parameters.

Static factories and constructors share a limitation: they do not scale well to large numbers of optional parameters.

## Telescoping Constructor

Traditionally, programers have used the **telescoping constructor** pattern, in which you provide a constructor with only the required parameters, another with a single optional parameters, a third with two optional parameters, and so on...

When you want to create an instance, you use the constructor you use the constructor with the shortest parameter list containing all the parameters you want to set.

Typically this constructor invocation will require many parameters that you don't want to set, but you're forced to pass a value for them anyway.

> In short, the telescoping constructor pattern works, but it is hard to write client code when there are many parameters, and harder still to read it.

## JavaBeans pattern

A second alternative is the *JavaBeans* pattern, in which you call a parameterless constructor to create the object and call setter methods to set each required parameter and each optional parameter of interest.

```java
NutritionFacts cocaCola = new NutritionsFacts();
cocaCola.setServingSize(240);
cocaCola.setCalories(100);
// more setters calls ...
```

The problem is that, because construction is split across multiple calls, a **JavaBean may be in an incosistent state partway through its construction**. The class does not have the option of enforcing consistency merely by checking the validity of the constructor parameters.

> Attempting to use an object when its in an inconsistent state may cause failures that are fare removed from the code containing the bug and hence difficult to debug.

A related disadvantage is that this pattern **precldues the possibility of making a class immutable**, and requires added effort on part of the programmer to ensure *thread safety*.

> It's possible to reduce these disadvantages by manually "freezing" the object when its construction is complete and not allowing it to be used until forzen, but this variant is unwieldy and rarely used in practice. Moreover, it can cause errors at runtime because the compiler cannot ensure that the programmer calls the freeze method on an object before using it.

## Solution: The Builder pattern

This is a form of the Gamma's *Builder* pattern. Instead md making the desired object directly, the client calls a constrctor or static factory with all the required parameters and gets a *builder *object*.

Then the client calls setter-like methods on the builder object to set each optional parameter of interest. Finally, the client calls a parameterless `build` method to generate the object, which is typically immutable.

The builder is typically a *static* member class of the class it builds.

```java
public class NutritionFacts {
  private final int servingSize;
  private final int calories;
  private final int fat;
  private final int sodium;
  // more properties...

  public static class Builder {
    // Required parameters
    private final int servingSize;
    private final int calories;

    // Optional parameters - initialized to default values
    private int fat;
    private int sodium;

    public Builder(int servingSize, int calories) {
      this.servingSize = servingSize;
      this.calories = calories;
    }

    public Builder fat(int val) { fat = val; return this; }
    public Builder sodium(int val) { fat = val; return this; }

    public NutritionFacts build() {
      return new NutritionFacts(this);
    }
  }

  private NutritionFacts(Builder builder) {
    servingSize = builder.servingSize;
    calories = builder.calories;
    fat = builder.fat;
    sodium = builder.sodium;
  }
}

NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
  .fat(8).sodium(35).build();
```

The client code is easy to write and, more importantly, easy to read.

> The Builder pattern **simulates named optional patterns** as found in Python and Scala.

To detect invalid parameters as soon as possible, check parameter validity in the builder's constructor and methods. Check invariants involving multiple parameters in the constructor invoked by the `build` method. To ensure these invariants against attack, do the checks on object fields after copying parameters from the builder (Item 50). If a check fails, throw an `IllegalArgumentException` (Item 72) whose detail message indicates which parameters are invalid (Item 75)

The *Builder* pattern is also **well suited to class hierarchies**.

```java
public abstract class Pizza {
  public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
  final Set<Topping> toppings;

  abstract static class Builder<T extends Builder<T>> {
    EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
    
    public T addTopping(Topping topping) {
      toppings.add(Objects.requireNonNull(topping));
      return self();
    }

    abstract Pizza build();
    
    // Subclasses must override this method to return "this"
    protected abstract T self();
  }

  Pizza(Builder<?> builder) {
    toppings = builder.toppings.clone(); // See Item 50
  }
}

public class NyPizza extends Pizza {
  public enum Size { SMALL, MEDIUM, LARGE }
  private final Size size;

  public static class Builder extends Pizza.Builder<Builder> {
    private final Size size;

    public Builder(Size size) {
      this.size = Objects.requireNonNull(size);
    }

      @Override public NyPizza build() {
        return new NyPizza(this);
      }
      
      @Override protected Builder self() { return this; }
    }

    private NyPizza(Builder builder) {
      super(builder);
      size = builder.size;
    }
  }
}

public class Calzone extends Pizza {
  private final boolean sauceInside;

  public static class Builder extends Pizza.Builder<Builder> {
    private boolean sauceInside = false; // default

    public Builder sauceInside() {
      sauceInside = true;
      return this;
    }

    // Override methods...
  }

  private Calzone(Builder builder) {
    super(builder);
    sauceInside = builder.sauceInside;
  }
}
```

This technique, wherein a subclass method is declared to return a subtype of the return type declared in the super class, is known as a **covariant return typing**. It allows clients to use these builders without the need for casting.

```java
NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();

Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();
```

### Advantages

* Is a good choice when designing classes whose constructors or static factories would have more than a handful of parameters, especially if many of those are optional or of identical type.

* Client code is much easier to read and write with builders than with telescoping constructors.

* Builders are much safer than JavaBeans.

### Disadvantages

* In order to create an object, you must first create its builder. While the cost of creating this builder is unlikely to be noticeable in practice, it could be a problem in performance-critical situations.

* Builder pattern is more verbose than the telescoping constructor pattern, so it should be used only if there are enough parameters to make it worthwhile.
