package p2;

import p1.A1;

public class C2 extends A1 {
    public static void main(String[] args) {
        // System.out.println("privateMember: " + privateMember); // Private
        // System.out.println("defaultMember: " + defaultMember); // Cannot be accessed from outside package
        System.out.println("protectedMember: " + protectedMember);
        System.out.println("publicMember: " + publicMember);
    }
}
