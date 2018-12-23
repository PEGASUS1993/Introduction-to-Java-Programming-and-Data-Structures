import java.io.*;

public class Exercise17_03 {
  /** Main method */
  public static void main(String[] args) {
    // Read data
    int count = 0;
    try ( // Declare and create data input and output streams
      DataInputStream dis =  
        new DataInputStream(new FileInputStream("Exercise17_02.dat"));
    ) {    
      int total = 0;
      while (dis.available() > 0) {
        int temp = dis.readInt();
        total += temp;
        count++;
        System.out.print(temp + " ");
      }

      System.out.println("\nCount is " + count);
      System.out.println("\nTotal is " + total);
    }
    catch (FileNotFoundException ex) {
      System.out.println("File not found");
    }
    catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
