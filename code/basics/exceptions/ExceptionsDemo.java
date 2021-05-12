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
        finally {
            System.out.println("[ExceptionsDemo] {main} finally block");
        }
    
        System.out.println("[ExceptionsDemo] {main} end");
    }
}