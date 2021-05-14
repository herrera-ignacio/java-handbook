# Method References

```java
// (ClassName::staticMethod)
Function<String, String> stopwordRemover = Indexer::removeStopwords;
text = stopwordRemover.apply(text);

// (objectRef::instanceMethod)
elements.forEach(System.out::println);

// (ClassName::instanceMethod)
BiFunction<String, String, Boolean> biFunction = String::contains;
biFunction.apply(doc, "findMe");
```
