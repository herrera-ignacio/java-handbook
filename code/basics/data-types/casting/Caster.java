public class Caster {

    int x = 10;
    long y = x; // Implicit casting, smaller to larger.

    int z = (int) y; // Explicit casting, larger to smaller (narrowing).

    byte b = 65;
    char c = (char) b; // Explicit casting, widening & narrowing
    short s = (short) c; // Implicit casting.

    byte narrowedByte = (byte) 123456; // 64, Out-of-range assignment produces information loss

    int truncatedInt = (int) 0.9; // 0, truncation.
    char truncatedChar = (char) 65.5; // 65 'A' truncation.

    void compute() {
        System.out.println("Implicit int->long: " + y);
        System.out.println("Implicit char->short: " + s);
        System.out.println("Explicit long->int: " + z);
        System.out.println("Explicit byte->char: " + c);
        System.out.println("Out-of-range assignment byte: " + narrowedByte);
        System.out.println("Truncation float->int: " + truncatedInt);
        System.out.println("Truncation float->char: " + truncatedChar);
    }

    public static void main(String[] args) {
       Caster c = new Caster();
       c.compute();
    } 
}
