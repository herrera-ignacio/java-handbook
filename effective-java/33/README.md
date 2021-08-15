## 33: Consider typesafe heterogeneous containers

## Overview

Consider a database row that can have arbitrarily many columns. It would be nice to be able to access all of them in a typesafe manner. The idea is to parameterize the *key* instead of the *container*. Then present the parameterized key to the container to insert or retrieve a value. **The generic type system is used to guarantee that the type of the value agrees with its key**.

## Example

Consider a `Favorites` class that allows its clients to store and retrieve a favorite instance of arbitrarily many types.

```java
// Typesafe heteogeneous container pattern - API
public interface Favorites {
  public <T> void putFavorite(Class<T> type, T instance);
  public <T> void getFavorite(Class<T> type);
}

// Typesafe heterogeneous container pattern - implementation
public class Favorites {
  private Map<Class<?>, Object> favorites = new HashMap<>();

  public <T> void putFavorite(Class<T> type, T instance) {
    favorites.put(Objects.requireNonNull(type), instance);
  }

  public <T> T getFavorite(Class<T> type) {
    return type.cast(favorites.get(type));
  }
}

// Client
public static void main(String[] args) [
  Favorites f = new Favorites();
  f.putFavorite(String.class, "Java");
  f.putFavorite(Integer.class, 0xcafebabe);
  f.putFavorite(Class.class, Favorites.class);

  String favoriteString = f.getFavorte(String.class);
  int favoriteInteger = f.getFavorte(Integer.class);
  Class<?> favoriteClass = f.getFavorte(Class.class);

  System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
]
```
