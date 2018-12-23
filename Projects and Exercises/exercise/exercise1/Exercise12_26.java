import java.util.Scanner;
import java.io.*;

public class Exercise12_26 {
  public static void main(String[] args) {
    System.out.print("Enter a directory name: ");
    Scanner input = new Scanner(System.in);
    String directoryName = input.nextLine();
    
    if (new File(directoryName).mkdirs()) {
      System.out.println("Directory " + directoryName + " created");
    }
    else {
      System.out.println("Directory " + directoryName + " already exists");
    }
  }
}
