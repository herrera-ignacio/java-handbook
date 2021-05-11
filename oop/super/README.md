# `super` keyword

Access super class method.

* Non-overriden methods can be accessed directly or with `super`.
* Overriden methods must be accessed with `super`.
* Can't use in `static` method.
* Access _hidden_ field.

```java
class Staff extends User {
    void postAReview() {
        super.postAReview();
        // behavior extension code
    }
}
```
