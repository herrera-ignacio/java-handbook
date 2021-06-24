# 9: Prefer try-with-resources to try-finally

* Overview
* Case study: `try-finally`
* Case study: `try-with-resources`

## Overview

The Java libraries include many resources that must be closed manually by invoking a `close` method. Examples include `InputStream`, `OutputStream`, and `java.sql.Connection`.

> Closing resources is often overlooked by clients, with predictably dire performance consequences. While many of these resources use *finalizers* as a safety net, they don't work very well ([Item 8](../8)).

To be usable with `try-with-resources`, a resource must implement the `AutoClseable` interface, whic consists of a single `void`-returning `close` method.

## Case study: `try-finally`

It's no longer the best way to close resources!

```java
static String firstLineOfFile(String path) throws IOException {
  BufferedReader br = new BufferedReader(new FileReader(path));
  try {
    return br.readLine();
  } finally {
    br.close();
  }
}
```

This gets worse when you add a second resource:

```java
static void copy(String src, String dst) throws IOException {
  InputStream in = new FileInputStream(src);
  try {
    OutputStream out = new FileOutputStream(dst);
    try {
      byte[] buf = new byte[BUFFER_SIZE];
      int n;
      while ((n = in.read(buf)) >= 0)
        out.write(buf, 0, n);
    } finally {
      out.close();
    }
  } finally {
    in.close();
  }
}
```

The code on both the `try` block and the `finally` block is capable of throwing exceptions. For example, in the `firstLineOfFile` method, the call to `readLine` could throw an exception due to a failure in the underlying physical device, and the call to `close` could then fail for the same reason. Undert these circumstances, the second exception completely obliterates the first one. There is no record of the first exception in the exception stack trace, which can greatly complicate debugging in real systems (usually, it's the first exception that you want to see in order to diagnose the problem).

All of these problems were solved when Java 7 introduced the `try-with-resources` statement.

## Case study: `try-with-resources`

Here's the first example using this construct:

```java
static String firstLineOfFile(String path) throws IOException {
  try (BufferedReader br = new BufferedReader(
    new FileReader(path))) {
      return br.readLine();
    }
}
```

Here's the second example:

```java
static void copy(String src, String dst) throws IOException {
  try (InputStream in = new FileInputStream(src);
      OutputStream out = new FileOutputStream(dst)) {
    byte[] buf = new byte[BUFFER_SIZE];
    int n;
    while ((n = in.read(buf)) >= 0)
      out.write(buf, 0, n);
  }
}
```

Not only these versions are **shorter** and **more readable** than the originals, but they **provide far better diagnostics**.

If exceptions are thrown by both the `readLine` call and the (invisible) `close`, the latter exception is *suppresed* in favor of the former. In fact, multiple exceptions may be suppresed in order to preserve the exception that you actually want to see. They are *not discarded* though, they are printed in the stack trace with a notation saying that they were suppressed.

> You can also access them programmatically with the `getSuppressed` method, which was added to `Throwable` in Java 7.

You can put `catch` clauses on `try-with-resources` statements, just as you can on regular `try-finally` statements. This allows you to handle exceptions without sullying your code with another layer of nesting.

Here's another example of `firstLineOfFile` method that does not throw exceptions, but takes a default value to return if it can't open the file or read from it:

```java
static String firstLineOfFile(String path, String defaultVal) {
  try (BufferedReader br = new BufferedReader(
      new FileReader(path))) {
    return br.readLine();
  } catch (IOException e) {
    return defaultVal;
  }
}
```
