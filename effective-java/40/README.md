# 40: Consistently use the `Override` annotation

For the typical programmer, the most important anotation type is `@Override`. This annotation can be used only on method declarations, and it indicates **that the annotated method declaration overrides a declaration in a supertype**.

The **compiler can protect you** from a great many errors if you use the `Override` annotation.

**You should use `@Override` on every method declaration that you believe to override a sueprclass declaration**. There is one minor exception to this rule. If you are writing a class that is not labeled abstract and you believe that it overrides an abstract method in its superclass, you needn't bother puttig the `Override` annotation on that method. In a class that is not declared abstract, the compiler will emit an error message if you fail to override an abstract superclass method. However, you might wish to **draw attention** to all of the methods in your class that override superclass methods, in which case you should feel free to annotate these methods too.
