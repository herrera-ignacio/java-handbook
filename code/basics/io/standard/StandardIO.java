import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StandardIO {

    static String filePath = "C:\\Users\\ignac\\Documents\\GitHub\\java-handbook\\code\\basics\\io\\standard\\test.txt";

    static void read() {
        StringBuilder text = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = in.readLine()) != null) {
                text.append(line).append("\n");
            
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(text);
    }

    static void write(String text) {
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"))) {
            out.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        read();
        write("Hello from Java!");
        read();
    }
}
