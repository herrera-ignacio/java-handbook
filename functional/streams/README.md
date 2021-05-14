# Streams

> `java.util.stream` provides Streams & Collections API.

* Streams make parallelizing simpler and cleaner.
  * filtering
  * mapping
  * slicing
  * matching & finding
  * reduction
  * collect
* Favor declarative programming over imperative.

```java
// Imperative
Map<Category, List<Book>> booksByCategory = new HashMap<>();

for (Book book : books) {
    if (book.rating() >= 4.5) {
        Category c= book.getCategory();

        List<Book> booksList = booksByCategory.get(c);

        if (booksList == null) {
            booksList = new ArrayList<>();

            booksByCategory.put(c, booksList);
        }

        booksList.add(book);
    }
}

// Streams - Declarative
Map<Category, List<Book>> booksByCategory = books.stream()
    .filter(book => book.rating() >= 4.5)
    .collect(groupingBy(Book::getCategory));
```

```java
documents.stream()
    .filter(d => d.contains("findMe"))
    .map(Indexer::stripHtmlTags)
    .map(Indexer::removeStopwords)
    .forEach(System.out::println);
```
