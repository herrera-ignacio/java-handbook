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

    void compute() {
        arithmeticOps();
    }

    public static void main(String[] args) {
        Operators ops = new Operators();
        ops.compute();
    }
    
}
