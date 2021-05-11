public class StringDemo {

    /**
     * Java uses UTF-16 for characters.
     * String is an immutable sequence of unicode characters.
     * String pool saves memory.
     */

    /**
     * String has lots of util methods such as:
     * - split
     * - substring
     * - equals / equalsIgnoreCase
     * - compareTo
     * - contains
     * - indexOf / lastIndexOf
     * - startsWith / endsWith
     */

    static void compute() {
        String s = new String(); // empty string

        char[] cArray = {'h', 'e', 'l', 'l', 'o'};

        s = new String(cArray);

        s = new String("hello!"); // can change reference

        s = "hello world!";


        System.out.println(s);
    }

    static void concatenation() {
        StringBuilder sb = new StringBuilder("hello" + " world!");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        compute();
        concatenation();
    }
    
}
