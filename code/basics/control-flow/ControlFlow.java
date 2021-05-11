public class ControlFlow {
    
    static boolean ifStatement(boolean x, boolean y) {
        if (x || y) {
            return true;
        } else {
            return false;
        }
    }

    static void switchStatement(int x) {
        switch (x) {
            case 1:
                System.out.println("x == 1");
                break;
            case 2:
                System.out.println("x == 2");
                break;
            default:
                System.out.println("Other");
                break;
        }
    }

    static void ternaryOp(int x) {
        System.out.println("x > 0 ? 'positive' : 'negative': " + (x > 0 ? "positive" : "negative"));
    }

    static void forStatement(int x) {
        for (int i = 0; i < x; i++) {
            System.out.println("Iteration n~" + i);
        }
    }

    static void forEachStatement(int[] x) {
        for (int i : x) {
            System.out.println("Item: " + i);
        }
    }

    static void whileStatement(int x) {
        int i = 0;

        while (i < x) {
            

            if (i == 1) {
                System.out.println("continue statement!");
                i++;
                continue;
            }

            if (i == 3) {
                System.out.println("break statement!");
                break;
            }

            System.out.println("While: " + i + " < " + x);
            i++;
        }
    }

    static void doWhileStatement(int x) {
        int i = 0;

        do {
            System.out.println("Do while: " + i + " > " + x);
        } while (i > x);
    }

    void compute() {
        System.out.println("If x || y: " + ifStatement(true, false));

        switchStatement(1);
        switchStatement(3);

        ternaryOp(1);

        forStatement(3);

        forEachStatement(new int[]{0, 1, 2});

        whileStatement(5);

        doWhileStatement(3);
    }

    public static void main(String[] args) {
        ControlFlow cf = new ControlFlow();
        cf.compute();
    }
}
