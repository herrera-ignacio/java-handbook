import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class ByteIO {
    static String filePath = "C:\\Users\\ignac\\Documents\\GitHub\\java-handbook\\code\\basics\\io\\bytestreams\\Jesus.jpg";
    static String outFileName = "C:\\Users\\ignac\\Documents\\GitHub\\java-handbook\\code\\basics\\io\\bytestreams\\slow-copy.jpg";
    static String outFileName2 = "C:\\Users\\ignac\\Documents\\GitHub\\java-handbook\\code\\basics\\io\\bytestreams\\fast-copy.jpg";

    public static void fileCopyNoBuffer() {
        System.out.println("Copy without Buffer...");

        long startTime, elapsedTime;

        File fileIn = new File(filePath);
        System.out.println("File size is " + fileIn.length() + " bytes");

        try (FileInputStream in = new FileInputStream(filePath);
                FileOutputStream out = new FileOutputStream(outFileName)) {
            startTime = System.nanoTime();

            int byteRead;

            while((byteRead = in.read()) != -1) {
                out.write(byteRead);
            }

            elapsedTime = System.nanoTime() - startTime;

            System.out.println("DONE! Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Most common way to read byte streams from a file
    public static void fileCopyWithBufferAndArray() {
        System.out.println("\nCopy with Buffer");

        long startTime, elapsedTime;

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileName2))) {
            startTime = System.nanoTime();

            byte[] byteBuf = new byte[4000];

            int numBytesRead;

            while ((numBytesRead = in.read(byteBuf)) != -1) {
                out.write(byteBuf, 0, numBytesRead);
            }

            elapsedTime = System.nanoTime() - startTime;

            System.out.println("DONE! Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        fileCopyNoBuffer();
        fileCopyWithBufferAndArray();
    }
}
