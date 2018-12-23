import java.util.*;
import java.io.*;

public class Exercise12_14 {
  public static void main(String[] args) throws Exception {
    Scanner consoleInput = new Scanner(System.in);
    System.out.print("Enter file name: ");
    String filename = consoleInput.nextLine();
    
    try (
      Scanner input = new Scanner(new File(filename));
    ) {
      double sum = 0;
  
      while (input.hasNext()) {
        sum += input.nextDouble();
      }
  
      System.out.println("Total is " + sum);
    }
  }
}
