import java.io.*;

public class Exercise22_12 {
  public static void main(String[] args) throws Exception {
    DataInputStream input =
      new DataInputStream(new BufferedInputStream(
      new FileInputStream("Exercise22_08.dat")));

    input.skip(input.available() - 8 * 100);
    while (input.available() > 0) {
      System.out.println(input.readLong() + " ");
    }

    input.close();
  }
}
