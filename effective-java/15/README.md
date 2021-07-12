# 15: Minimize the accessibility of classes and members

* Make Each Class or Member as Inaccessible as Possible
  * Testing concerns
* Instance Fields of Public Classes Should Rarely Be Public ([Item 16](../16))
  * `public static final` array field
* Important Concepts
  * Encapsulation
  * Access Levels

## Make Each Class or Member as Inaccessible as Possible

After designing your class's public API, your reflex should be to make all other members private. Only if another class in the same package really needs to access a member should you remove the `private` modifier, making the member `package-private`.

For top-level (non-nested) classes and interfaces, there are only two possible access levels: *package-private* and *public*. **If a top-level class or interface can be made *package-private*, it should be**.

> If you declare a top-level class or interface with the `public` modifier, it will be public; otherwise, it will be *package-private*.

By making it *package-private*, you make it part of the implementation rather than the exported API, and you can modify it, replace it, or eliminate it in a subsequent release without fear or harming existing clients.

> If a *package-private* top-level class or interface is used by only one class, consider making it a *private static nested* class of the sole class hat uses it ([Item 24](../24)). This reduces its accessibility from all the classes in its package to the one class that uses it.

Both `private` and `package-private` members are part of a **class's implementation** and do not normally impact its exported API. These fields can, however, **"leak" into the exported API** if the class implements `Serializable` ([Item 86](../86) and [87](../87)).

Also, **a `protected` member of an exported class represents a public commitment to an implementation detail ([Item 19](../19)**. The need for *protected* members should be relatively rare.

### Testing concerns

To facilitate testing your code, you may be tempted to make a class, interface, or member more accessible than otherwise necessary. This is fine up to a point. **It is acceptable to make a `private` member of a public class `package-private` in order to test it**, but it is not acceptable to raise the accessiblity any higher.

## Instance Fields of Public Classes Should Rarely Be Public ([Item 16](../16))

If an instance field is nonfinald or is a reference to a mutable object, then by making it `public`, you give up the ability to enforce invariants involving the field.

Also, you give up the ability to take any action when the field is modified, so **classes with public mutable fields are not generally thread-safe**.

Even if a field is *final* and refers to an immutable object, by making it public you give up the flexibility to switch to a new internal data representation in which the field does not exist.

> There's one exception. You can exponse constants via public static final fields, assuming the constants **form an integral part of the abstraction provided by the class**.

### `public static final` array field

Note that a nonzero-length array is always mutable, so **it is wrong for a class to have** a `public static finald` array field, or an accessor that returns such a field, because clients will be able to modify the contents of the array.

```java
// Potential security hole!
public static final Thing[] VALUES = { ... };
```

There are two ways to fix the problem. You can make the public array `private` and add a public immutable list:

```java
private static final Thing[] PRIVATE_VALUES = { ... };
public static final List<Thing> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
```

Alternatively, you can make the array private and add a public method that returns a copy of a private array:

```java
private static final Thing[] PRIVATE_VALUES = { ... };
public static final Thing[] values() {
  return PRIVATE_VALUES.clone();
}
```

## Important Concepts

### Encapsulation

A well-designed component hides all its implementation details, cleanly seaprating its API from its implementation. Components then communicate only through their APIs are oblivious to each other's inner workings.

**Information Hiding** is important for many reasons, most of which stem from the fact that it **decouples** the components that comprise a system, allowing them to be developed, tested, optimized, used, understood, and modified in isolation.

> While *Encapsulation* bdoes not, in and of itself, cause good performance, it **enables effective performance tunning**: once a system is complete and profiling has determined which components are causing performance problems ([Item 67](../67)), those components can be optimized without affecting the correctness of others.

### Access Levels

For members (fields, methods, nested classes, and nested interfaces), there are four possible access levels:

* `private`: The member is accessible only from the top-level class where it is declared.

* `package-private`: Member is accessible from any class in the package where it is declared. This is the access level you get if no access modifier is specified (except for interface members, which are public by default). Technically known as `package` access.

* `protected`: The member is accessible from subclasses of the class where it is declared, and from any class in the package where it is declared.

* `public`: The member is accessible from anywhere.

> as of Java 9, there are two additional, implicit access levels introduced as part of the *module system*. A *module* is agrouping of packages, like a package is a grouping of classes. A module may explicitly export some of its packages via *export declarations* in its *module delcaration* (which is by convention `module-info.java`). Public and protected members of unexported packages in a module are inaccessible outside the module; within the module, accessibility iws unaffected by export declarations. *Public* and *protected* members of public classes in unexported packages give rise to the two implicit access levels, which are intramodular analogues of the normal public and protected levels.
