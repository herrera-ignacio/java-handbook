import java.math.BigDecimal;

public class Gpa {

    float gpa_float = 3.888888888889f;
    double gpa_double = 3.8888888888889d;
    BigDecimal gpa_bd = new BigDecimal("3.9"); // Useful for exact precision, e.g. money

    void compute() {
        System.out.println("GPA float:" + gpa_float);
        System.out.println("GPA double: " + gpa_double);
        System.out.println("GPA BigDecimal: " + gpa_bd);
    }

    public static void main(String[] args) {
        Gpa gpa = new Gpa();
        gpa.compute();
    }
}
