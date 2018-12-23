import java.util.*;
import java.io.*;

public class Exercise12_15 {
  public static void main(String[] args) throws Exception {
    // Check if source file exists
    File file = new File("Exercise12_15.txt");
    if (!file.exists()) {
      try ( // Create the file
        PrintWriter output = new PrintWriter(file);
      ) {
        for (int i = 1; i <= 100; i++) {
          output.print((int)(Math.random() * 100) + " ");
        }
      }
    }

    try (
      Scanner input = new Scanner(file);
    ) {
      int[] numbers = new int[100];
  
      for (int i = 0; i < 100; i++)
        numbers[i] = input.nextInt();
  
      Arrays.sort(numbers);
  
      for (int i = 0; i < 100; i++)
        System.out.print(numbers[i] + " ");
    }
  }
}
