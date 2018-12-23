import java.io.*;

public class Exercise17_04 {
  public static void main(String[] args) throws IOException {
    try (
      BufferedReader input = new BufferedReader(new FileReader(args[0]));
      DataOutputStream output = new DataOutputStream(new FileOutputStream(args[1]));
    ) {
      String line;
      while ((line = input.readLine()) != null)
        output.writeUTF(line);
    }

    try (
      InputStream input1 = new FileInputStream(args[0]);
      InputStream input2 = new FileInputStream(args[1]);
    ) {
      System.out.println(args[0] + "'s size is " + input1.available() + " bytes");
      System.out.println(args[1] + "'s size is " + input2.available() + " bytes");
    }
  }
}
