# Parameterized Tests

> https://java-x.blogspot.com/2007/01/unit-testing-with-junit-40.html

## JUnit 5

> * https://www.baeldung.com/parameterized-tests-junit-5
> * https://www.baeldung.com/junit-params

## JUnit 4

`Parameterized` runner allows you to run the same test with different data.

For example, in the following piece of code, the tests will run four times, with the parameter "`number`" changed each time to the value in the array.

```java
@RunWith(value = Parameterized.class)
public class StackTest {
 Stack<Integer> stack;
 private int number;

 public StackTest(int number) {
   this.number = number;
 }

 @Parameters
 public static Collection data() {
   Object[][] data = new Object[][] { { 1 }, { 2 }, { 3 }, { 4 } };
   return Arrays.asList(data);
 }
 ...
}
```

## Example: Single Test Class For Multiple Implementations

> https://stackoverflow.com/a/16237341/9248356

With JUnit 4.0+ you can use [parameterized tests][1]:

 - Add `@RunWith(value = Parameterized.class)` annotation to your test fixture
 - Create a `public static` method returning `Collection`, annotate it with `@Parameters`, and put `SinglyLinkedList.class`, `DoublyLinkedList.class`, `CircularList.class`, etc. into that collection
 - Add a constructor to your test fixture that takes `Class`: `public MyListTest(Class cl)`, and store the `Class` in an instance variable `listClass`
 - In the `setUp` method or `@Before`, use `List testList = (List)listClass.newInstance();`

With the above setup in place, the parameterized runner will make a new instance of your test fixture `MyListTest` for each subclass that you provide in the `@Parameters` method, letting you exercise the same test logic for every subclass that you need to test.

  [1]: http://java-x.blogspot.com/2007/01/unit-testing-with-junit-40.html
