package p1;

public class B1 {
    public static void main(String[] args) {
        System.out.println("A1.defaultMember: " + A1.defaultMember); // Valid because inside same package
        System.out.println("A1.protectedMember: " + A1.protectedMember); // Valid because inside same package
    }
}
