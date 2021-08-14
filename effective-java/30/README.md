# 30: Favor generic methods

* Static utility methods that operate on parameterized types are usually generic. All of the "algorithm" methods in `Collections` (such as `binarySearch` and `sort`) are generic.

* On occasion, you will need to create an object that is immutable but applicable to many different types. Because generics are implemented by *erasure*, you can use a single object for all required type parameterizations, but you need to write a static factory method to repeatedly dole out the object for each requested type parameterization. This pattern, called *generic singleton factory*, is used for function objects ([Item 42](../42)) such as `Collections.reverseOrder`, and occasionally for collections such as `Collections.emptySet`.
