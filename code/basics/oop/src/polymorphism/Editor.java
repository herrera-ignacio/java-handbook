package polymorphism;

public class Editor extends Staff {
    public void printUserType() {
        System.out.println("Editor");
    }

    public void postAReview() {
        System.out.println("Editor: postAReview");
    }
    public void approveReview() { System.out.println("Staff: approveReview"); }
}
