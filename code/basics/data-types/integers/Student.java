public class Student {
    int id = 1000; // [-2^31, 2^31 - 1]
    byte age = 18; // [-2^7, 2^7 - 1]
    short rank = 165; // [-2^15, 2^15 - 1]
    long phone = 223_456_7890L; // [-2^63, 2^63-1]
    
    int minInteger = Integer.MIN_VALUE;
    int maxInteger = Integer.MAX_VALUE;

    byte minByte = Byte.MIN_VALUE;
    byte maxByte = Byte.MAX_VALUE;

    void compute() {
        System.out.println("id: " + id);
        System.out.println("age: " + age);
        System.out.println("rank: " + rank);
        System.out.println("phone: " + phone);

        System.out.println("min integer: " + minInteger);
        System.out.println("max integer: " + maxInteger);

        System.out.println("min byte:" + minByte);
        System.out.println("max byte:" +maxByte);
    }

    public static void main(String[] args) {
        Student s = new Student();
        s.compute();
    }
}
