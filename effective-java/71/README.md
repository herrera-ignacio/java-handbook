# 71: Avoid unnecessary use of checked exceptions

## Overview

* Used properly, *checked exceptions* can improve APIs and programs. Unlike return codes and unchecked exceptions, they *force* programmers to deal with problems, enhancing reliability.

* Overuse of checked exceptions in APIs can make them far less pleasant to use. If a method throws checked exceptions, the code that invokes it must handle them in ne or more `catch` blocks, or declare that it hrows them and let them propagete outward. Either way, **it places a burden on the user of the API**.

* Methods throwing checked exceptions can't be used directly in streams.

* Burden may be justified if the exceptional condition cannot be prevented by proper use of the API *and* the programmer using the API can take some useful action once confronted with the exception. Unless both of these conditions are met, aun unchecked exception is appropriate.

* If recovery may be possible and you want to *force* callers to handle excetional conditions, first consider returning an optional. The disadvantage of this technique is that the method can't return any additional information detailing its inability to perform the desired cvomputation.

## Example

If the client programmer can't do something better than this, then an unchecked exception is called for:

```java
} catch (TheCheckedException e) {
  throw new AssertionError(); // Can't happen!
}
```

```java
} catch (TheCheckedException e) {
  e.printStackTrace(); // Oh well, we lose.
  System.exit(1);
}
```
