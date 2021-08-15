# 36: Use `EnumSet` instead of bit fields

If the elements of an enumerated type are used primarily in sets, it is traditional to use thei nt enum pattern, assigning a different power of 2 to each constant. This representation lets you use the bitwise `OR` operation to combine everal constants into a set, known as a *bit field*. It also lets you perform set operations such as union and intersecction efficiently using bitwise arithmetic. But **bit fields have all the disadvantages of `int` enum constants and more**.

The `java.util.EnumSet` class efficiently represent sets of values drawn from a single enum type. This class implements the `Set` interface, providing all of the richness, type safety, and interopperability you get with any other `Set` implementation.
