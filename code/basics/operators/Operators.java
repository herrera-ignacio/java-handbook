public class Operators {
    
    /**
     * # Unary
     *  - operator operand, e.g., -x
     *  - operand operator, e.g., x++
     * # Binary
     *  operand operator operand, e.g., x + 3
     * # Ternary (?:)
     *  operand operator operand operator operand, e.g., (x > 3) ? x : 0
     */

    /**
     * 1. Assignment
     * 2. Arithmetic
     * 3. Comparison
     * 4. Logical
     * 5. Bitwise
     * 6. Bit shift
     * 7. instanceof
     */

    static void arithmeticOps() {
        int x = 1;

        System.out.println("x++ (post): " + x++);
        System.out.println("+x (pre): " + ++x + ", x:" + x);

        int y = 3;

        System.out.println("x + y: " + (x+y)); // +=
        System.out.println("x - y: " + (x-y)); // -=
        System.out.println("x * y: " + (x*y)); // *=
        System.out.println("x / y: " + (x/y)); // /=
        System.out.println("x % y: " + (x/y)); // %=
    }

    static void comparisonOps() {
        int age = 26;

        System.out.println("age: " + age);
        System.out.println("age > 26: " + (age > 26));
        System.out.println("age >= 26: " + (age >= 26));
        System.out.println("age < 26: " + (age < 26));
        System.out.println("age <= 26: " + (age <= 26));
        System.out.println("age == 26: " + (age == 26));
        System.out.println("age != 26: " + (age != 26));
    }

    static void logicalOps() {
        boolean x = true;
        boolean y = false;

        System.out.println("x: " + x);
        System.out.println("y: " + y);
        System.out.println("x && y: " + (x && y));
        System.out.println("x || y: " + (x || y));
        System.out.println("!x: " + !x);
    }

    static void bitwiseOps() {
        int x = 0b0010_0110;
        int y = 0b1101_0110;

        System.out.println("x & y (AND): " + Integer.toBinaryString(x & y));
        System.out.println("x | y (OR): " + Integer.toBinaryString(x | y));
        System.out.println("x ^ y (XOR): " + Integer.toBinaryString(x ^ y));
        System.out.println("~x (NOT) :" + Integer.toBinaryString(~x));
    }

    static void bitshiftOps() {
        System.out.println("x: " + Integer.toBinaryString(0b1010_0110));
        System.out.println("x << 1 (left-shift): " + Integer.toBinaryString(0b1010_0110 << 1));
        System.out.println("x >>> 1 (unsigned right-shift): " + Integer.toBinaryString(0b1010_0110 >>> 1));
        System.out.println("x >> 1 (signed right-shift): " + Integer.toBinaryString(0b1010_0110 >> 1));
    }

    void compute() {
        arithmeticOps();
        comparisonOps();
        logicalOps();
        bitwiseOps();
        bitshiftOps();
    }

    public static void main(String[] args) {
        Operators ops = new Operators();
        ops.compute();
    }
    
}
