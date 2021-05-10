import java.util.Arrays;

public class VarArgs {

    static void compute(int i, int j, int... varargs) {
        System.out.println("i: " + i);
        System.out.println("j: " + j);
        System.out.println("varargs: " + Arrays.toString(varargs));
    }


    public static void main(String[] args) {
        compute(
            1,
            7,
            1,2,3,4
        );

        compute(1, 7);
    }
    
}
