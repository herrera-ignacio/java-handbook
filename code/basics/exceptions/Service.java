import java.io.FileNotFoundException;

public class Service {
    public static void go(boolean shouldThrow) throws FileNotFoundException {
        if (shouldThrow) {
            System.out.println("[Service] {Go} EXCEPTION");
            throw new FileNotFoundException(); 
        }
        System.out.println("[Service] {Go} Success");
    }    
}
