# 53: Use varargs judiciously

Varargs methods **accept zero or more arguments of a specified type**. The varargs facility works by first creating an array whose size is the number of arguments passed at the call site, then putting the argument values into the array, and finally passing the array to the method.

```java
// Simple use of varargs
static int num(int... args) {
  int sum = 0;
  for (int arg : args)
    sum += arg;
  return sum;
}
```

Sometimes it's appropriate to write a method that requires *one* or more arguments of some type, rather than *zero* or more.

```java
// The WRONG way to use varargs to pass one or more arguments
static int min(int... args) {
  if (args.length == 0)
    throw new IllegalArgumentException("Too few arguments");
  int min = args[0];
  for (int i = 1; i < args.length; i++)
    if (args[i] < min)
      min = args[i];
  return min;
}
```

This solution has seeral problems. The most serious is that if the client invokes this method with no arguments, it fails at runtime rather than compile time. Another problem is that you can't use a for-each loop unless you initialize `min` to `Integer.MAX_VALUE`, which is also ugly.

```java
// The right way to use varargs to pass one or more arguments
static int min(int firstArg, int... remainingArgs) {
  int min = firstArg;
  for (int arg : remainingArgs)
    if (arg < min)
      min = arg;
    return min;
}
```
