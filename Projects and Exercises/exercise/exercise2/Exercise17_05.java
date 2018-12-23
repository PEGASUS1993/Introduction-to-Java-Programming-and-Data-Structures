import java.io.*;

public class Exercise17_05 {
  public static void main(String[] args) throws IOException {
    try (
      ObjectOutputStream output = new ObjectOutputStream(
        new FileOutputStream("Exercise17_05.dat"));
    ) {
      output.writeObject(new int[]{1, 2, 3, 4, 5});
      output.writeObject(new java.util.Date());
      output.writeDouble(5.5);
    }

    try (
      ObjectInputStream input = new ObjectInputStream(
        new FileInputStream("Exercise17_05.dat"));
    ) {
      int[] array = (int[])input.readObject();
      java.util.Date date = (java.util.Date)input.readObject();
      double d = input.readDouble();

      System.out.println(java.util.Arrays.toString(array));
      System.out.println(date);
      System.out.println(d);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
