import java.util.Scanner;
import java.io.*;

public class Exercise12_05Extra { 
  public static void main(String[] args) throws IOException {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a file name for baby name ranking: ");
    
    File file = new File(input.nextLine());
    if (!file.exists()) {
      System.out.println("File " + file + " does not exist");
      System.exit(1);
    }
    
    // Open the file
    input = new Scanner(file);
    
    PrintWriter output = new PrintWriter(file.toString() + ".out");
    
    while (input.hasNext()) {
      input.nextInt(); // Skip an integer for ranking
      output.println(input.nextLine());
    }

    input.close();
    output.close();
  }
}