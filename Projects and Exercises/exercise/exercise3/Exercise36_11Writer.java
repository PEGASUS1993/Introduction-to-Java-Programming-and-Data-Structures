import java.io.*;

public class Exercise36_11Writer {
  public static void main(String[] args) {
    BufferedWriter out = null;

    try {
      out = new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream("Exercise36_11.gb"), "GB18030"));

      int code = 0x4E00; // Unicode 4E00
      for (int i = 0; i < 1307; i++) {
        for (int j = 0; j < 16; j++) {
          out.write((char)code + " ");
          code++;
        }
        out.write("\n");
      }
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    finally {
      try {
        out.flush();
        out.close();
      }
      catch (IOException ex1) {
        ex1.printStackTrace();
      }
    }
  }
}
