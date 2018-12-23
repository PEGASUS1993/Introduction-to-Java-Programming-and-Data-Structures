import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Exercise21_12 { 
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
    
    Set<String> boyNames = new HashSet<String>();
    Set<String> girlNames = new HashSet<String>();
    
    while (input.hasNext()) {
      input.nextInt(); // Skip an integer for ranking
      boyNames.add(input.next());
      input.nextInt(); // Skip an integer for number of boys
      girlNames.add(input.next());
      input.nextInt(); // Skip an integer for number of girls
    }
    
    boyNames.retainAll(girlNames);
    System.out.println(boyNames.size() + " names are used for both boys and girls ");
    System.out.println("They are ");
    for (String name: boyNames) {
      System.out.print(name + " ");
    }
  }
}
