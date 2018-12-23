import java.io.*;

public class Exercise17_02 {
  public static void main(String[] args) throws IOException {
    try (
      DataOutputStream output =
        new DataOutputStream(new FileOutputStream("Exercise17_02.dat", true));
    ) {
      for (int i = 0; i < 100; i++)
        output.writeInt((int)(Math.random() * 100000));
    }

    System.out.println("Done");
  }
}
