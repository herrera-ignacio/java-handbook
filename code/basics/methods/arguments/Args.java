public class Args {

    int id = 1000;

    void updateId(int newId) {
        newId = 1001;
    }

    void passByValue() {
        int id = 1000;
        updateId(id);
        System.out.println(id); // 1000
    }

    void passByReference(Args a) {
        a.id = 1001;
        System.out.println(a.id); // 1001
    }

    void compute() {
        passByValue();
        passByReference(this);
    }
    
    public static void main(String[] args) {
        Args a = new Args();
        a.compute();
    }
}
