package polymorphism;

public class UserTest {
    public void printUserType(User u) {
        u.printUserType();
    }

    public void approveReview(Staff s) {
        if (s instanceof Editor) {
            ((Editor) s).approveReview();
        } else {
            System.out.println("approveReview: Invalid object passed");
        }
    }

    public static void main(String[] args) {
        User user = new User();
        User staff = new Staff();
        User editor = new Editor();

        UserTest ut = new UserTest();
        ut.printUserType(user);
        staff.saveWebLink();
        editor.saveWebLink();

        ut.approveReview((Staff) staff);
        ut.approveReview((Staff) editor);
    }
}
