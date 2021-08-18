# 56: Write doc comments for all exposed API elements

> The *Javadoc* utility generates API documentation automatically from source code with specially formatted *documentation comments*, more commonly known as *doc comments*. They constitute a de facto API; conventions are described in the *How to Write Doc Comments* web page (Javadoc-guide).

* To document your API properly, you must preced *every* exported class, interface, constructor, method, and field declaration with a doc comment.

* The doc comment for a method should describe succinctly the contract between the method and its client.
  * *What* the method does rather than *how* it does its job.
  * Enumerate all of the method's *preconditions* and its *postcvonditions*.
  * Document any *side effects*.
  * By convention, the text following an `@param` tag or `@return` tag should be a noun phrase describing the value represented by the parameter or return value and is the phrase or caluse is not terminated by a period.

* Doc comments should be readable both in source code and in the generated documentation.

* No two memebers or constructors in a class or interface should have the same summary description. Pay particular attention to overloadings.

* When documenting a *generic type or method*, be sure to document all type parameters.

* When documenting an *enum type*, be sure to document the constants as well as the type and any public methods.

* When documenting an *annotation type*, be sure to document any members as well as the type itself. Document members with noun phrases, as if they were fields.

* Thread-safety and serializability are often neglected in documentation. Whether or not a class or static method is thread-safe, you should document its thread-safety level ([Item 82](../82)), as well as if a class is serializable, you should document its serialized form ([Item 87](../87)).
