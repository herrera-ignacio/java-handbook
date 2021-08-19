# 65: Prefer interfaces to reflection

> The *core reflection facility*, `java.lang.reflect`, offers programmatic access to arbitrary classes. Given a `Class` object, you can obtain `Constructor`, `Method`, and `Field` instances. These objects provide programmatic access to the class's member names, field types, method signatures, and so on. Moreover, they let you manipulate their underlying counterparts *reflectively*: you can construct instances, invoke methods, and access fields of the underlying class by invoking methods on such instances. Reflection allows one class to use another, even if the latter class did not exist when the former was compiled.

* Reflection comes at a price:

  * **You lose all the benefits of compile-time type checking**, including exception checking. If a program attempts to invoke a nonexistent or inaccessible method reflectively, it will fail at runtime unless you've taken special precautions.

  * **The code required to perform reflective access is clumsy and verbose**. It is tedious to write and difficult to read.

  * **Performance suffers**. Reflective method invocation is much slower than normal method invocation.

> There are a few sophisticated applications that require reflection (e.g., code analysis tools and dependency injection frameworks).

* You can obtain many of the benefits of reflection while incurring few of its costs by using it only in a very limited form. For many programs that must use a class that is unavailable at compile time, there exists at cdompile time an appropriate interface or superclass by which to refer to the class. If this is the case, you can create instances reflectively and access them normally via their interface or superclass.
