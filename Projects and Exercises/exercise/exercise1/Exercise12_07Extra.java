import java.util.Scanner;
import java.io.*;

public class Exercise12_07Extra {
  public static void main(String[] args) throws IOException {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a file name: ");
    String filename = input.nextLine();
    
    String result = replaceLeadingNumber(filename);
    PrintWriter output = new PrintWriter(filename);
    output.print(result);
    output.close();
    System.out.println("Done!");
  }
  
  private static String replaceLeadingNumber(String filename) throws java.io.IOException {
    String result = "";
    java.util.Scanner scanner = new java.util.Scanner(new java.io.File(filename));
    while (scanner.hasNext()) {
      result += replaceLeadingNumberInOneLine(scanner.nextLine()) 
        + (!scanner.hasNext() ? "" : System.getProperty("line.separator"));
    }
    scanner.close();
    return result;
  }
    
  private static String replaceLeadingNumberInOneLine(String s) {
    if (s.trim().matches("\\d+.*")) {
      String[] blanks = s.split("\\d", 2);
      String[] tokens = s.trim().split("\\s", 2);
      if (tokens.length < 2) 
        return blanks[0] + getBlanks(tokens[0]);
      else
        return blanks[0] + getBlanks(tokens[0]) + " " + tokens[1];        
    }  
    return s;
  }
  
  private static String getBlanks(String s) {
    String result = "";
    for (int i = 0; i < s.length(); i++)
      result += " ";
    return result;
  }
}
