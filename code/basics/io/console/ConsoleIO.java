import java.util.Scanner;

public class ConsoleIO {

    public static void main(String[] args) {
        String data;

        System.out.print("Enter 'start' to continue: ");
        
        Scanner scanner = new Scanner(System.in);

        while(!(data = scanner.nextLine()).equals("start")) {
            System.out.println("\nTry again: ");
        }

        System.out.println("Enter code: ");

        int code = scanner.nextInt();

        System.out.println("Success, code: " + code);

        scanner.close();

        Scanner scanner2 = new Scanner("How are you today?");
        
        while(scanner2.hasNext()) {
            System.out.println(scanner2.next());
        }

        scanner2.close();
    }
    
}
