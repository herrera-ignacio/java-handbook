# 58: Prefer for-each loops to traditional `for` loops

## Overview

* The for-each loop gets rid of the clutter and the opportunity for error by hiding the iterator or index variable, with no performance penalty. **Use for-each loops in preference to `for` loops wherever you can**.

* The resulting idiom applies equally to collections and arrays, easing the process of switching the implementation type of a container from one to the other.

* Three common situations where you *can't* use for-each:
  * **Destructive filtering**: If you need to traverse a collection removing selected elements, then you need to use an explicit iterator so that you can call its `remove` method. You can often avoid explicit traversal by using `Collection`'s `removeIf` method.

  * **Transforming**: If you need to traverse a list or array and replace some or all of the values of its elements, then you need the list iterator or array index in order to replace the value of an element.

  * **Parallel iteration**: If you need to traverse multiple collections in parallel, then you need explicit control over the iterator or index variable so that all iterators or index variables can be advanced in lockstem (as demonstrated unintentionally in the upcoming buggy examples).

## Examples

```java
// The preferred idiom for iterating over collections and arrays
for (Element e : elements) {
  ... // Do something with e
}
```

```java
// Bug that can be avoided using for-each!
List<Card> deck = new ArrayList<>();
for (Iterator<Suit> i = suits.iterator(); i.hasNext(); )
  for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); )
    deck.add(new Card(i.next(), j.next()))
    // should not call i.next() from inner loop!

// Fixed, but ugly - can improve with for-each!
for (Iterator<Suit> i = suitable.iterator(); i.hasNext(); ) {
  Suit suit = i.next();
  for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); )
    deck.add(new Card(suit, j.next()));
}

// Preferred idiom for nested iteration on collections and arrays
for (Suit suit : suits)
  for (Rank rank : ranks)
    defck.add(new Card(suit, rank));
```

