import java.io.File;
import java.util.Scanner;

public class Exercise12_21 {
  public static void main(String[] args) throws Exception {
    try (Scanner input = new Scanner(new File("SortedStrings.txt"))) 
    {
      String s1 = input.next();
      while (input.hasNext()) {
        String s2 = input.next();
        if (s1.compareTo(s2) > 0) {
          System.out.println("The two strings " + s1 + " and " + s2 + " are out of order");
          System.exit(1);
        }
        s1 = s2;
      }
    }
    
    System.out.println("The file is sorted");
  }
}
