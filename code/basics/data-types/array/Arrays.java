public class Arrays {
    void compute() {
        int[] scores = new int[4];

        System.out.println("Default value [0]: " + scores[0]);

        scores[1] = 90;

        System.out.println("Accessing initialized value [1]: " + scores[1]);

        int[] someNumbers = {1, 2, 3, 4};

        System.out.println("Accessing initialized array [1],[2],[3],[4]: " + someNumbers[0] + someNumbers[1] + someNumbers[2] + someNumbers[3]);
    }

    void compute2D() {
        int[][] my2DArray = new int[4][2];

        System.out.println("my2DArray Default value [0][1]: " + my2DArray[0][1]);

        int[][] myOther2DArray = {{1,2}, {3,4}};

        System.out.println("myOther2DArray Initialized value [1][0]: " + myOther2DArray[1][0]);
    }

    public static void main(String[] args) {
        Arrays a = new Arrays();
        a.compute();
        a.compute2D();
    }
    
}
