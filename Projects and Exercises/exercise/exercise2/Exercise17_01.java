import java.io.*;
import java.util.*;

public class Exercise17_01 {
  public static void main(String[] args) throws IOException {
    try (
      PrintWriter output =
        new PrintWriter(new FileOutputStream("Exercise17_01.txt", true));
    ) {
      for (int i = 0; i < 100; i++)
        output.print((int)(Math.random() * 100000) + " ");
    }
  }
}