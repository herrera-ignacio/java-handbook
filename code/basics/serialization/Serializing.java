import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializing implements Serializable {
    private String name;
    private transient int id = 4;

    public String getName() { return name; }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getId() { return id; }

   public static void doSerialization() {
        System.out.println("\nInside doSerialization ...");

        Serializing obj = new Serializing();

		obj.setName("Demo");

		System.out.println("name (before serialization): " + obj.getName());
		System.out.println("id (before serialization): " + obj.getId());
		
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("obj.ser")))) {
			out.writeObject(obj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
   }

   public static void doDeserialization() {
    System.out.println("\nInside doDeserialization ...");
		
    try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("obj.ser")))) {
        Serializing obj = (Serializing) in.readObject();

        System.out.println("name (after deserialization): " + obj.getName());
        System.out.println("id (after deserialization): " + obj.getId());
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
   }

   public static void main(String[] args) {
       doSerialization();
       doDeserialization();
   }
}
