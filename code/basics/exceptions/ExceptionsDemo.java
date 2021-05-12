import java.io.FileNotFoundException;

public class ExceptionsDemo {
    public static void main(String[] args) {
        System.out.println("[ExceptionsDemo] {main} start");

        try {
            Service.go(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {}
        catch (Throwable t) {}
    
        System.out.println("[ExceptionsDemo] {main} end");
    }
}