# Enums

* Can have instance variables
* Can have methods
* Can have constructors with private access (no public or protected modifier)
* Can have nested enums.

## Motivation

Avoid int constants:
    * No type safety
    * No namespace protection
    * Fragile

## Example

```java
public enum BookGenre {
    BIOGRAPHY(12),
    HORROR(15);

    private int minAgeToRead;

    public int getMinAgeToRead() {
        return minAgeToRead;
    }

    private BookGenre(int minAgeToRead) {
        this.minAgeToRead = minAgeToRead;
    }

    public static void main(String[] args) {
        for (BookGenre bookGenre : BookGenre.values()) {
            System.out.println("\nName": bookGenre); // toString
        }
    }
}
```