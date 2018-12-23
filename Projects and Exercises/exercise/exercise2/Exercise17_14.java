import java.util.Scanner;
import java.io.*;

public class Exercise17_14 { 
  public static void main(String[] args) throws Exception {
    Scanner input = new Scanner(System.in);  
    System.out.print("Enter a file to encrypt: ");
    File inputFile = new File(input.nextLine());
    System.out.print("Enter the output file: ");
    File outputFile = new File(input.nextLine());
    
    try (
      BufferedInputStream in = new BufferedInputStream(
        new FileInputStream(inputFile));
      BufferedOutputStream output = new BufferedOutputStream(
        new FileOutputStream(outputFile));
    ) {
      int value;
      while ((value = in.read()) != -1) {
        output.write(value + 5);
      }
    }
  }
}
