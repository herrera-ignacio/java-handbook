public class Overloading {

    static void compute() {
        System.out.println("Compute no args");
    }

    static void compute(int data) {
        System.out.println("Compute int");
    }

    static void compute(short data) {
        System.out.println("Compute short");
    }

    static void compute(byte data) {
        System.out.println("Compute byte");
    }

    public static void main(String[] args) {
        compute((int) 1);
        compute();
        compute((short) 1);
        compute((byte) 1);
    }
}
