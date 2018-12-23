import java.util.*;
import java.io.*;

public class Exercise12_03Extra { 
  private static String[] names = new String[1000 * 10 * 2];

  private static void readNames() {
    try {
      int j = 0;
      for (int i = 0; i < 10; i++) {
        String filename;
        if (i == 9)
          filename = "Babynamesranking2010.txt";
        else
          filename = "Babynamesranking200" + (i + 1) + ".txt";
        Scanner input = new Scanner(new File(filename));
        
        while (input.hasNext()) {
          input.nextInt(); // Skip the ranking
          names[j++] = input.next();
          input.nextInt(); // Skip the number of boy's name
          names[j++] = input.next();
          input.nextInt(); // Skip the number of girl's name
        }
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  /** Main method */
  public static void main(String[] args) {
    readNames();
    
    Arrays.sort(names);
    
    writeNames();
  }
  
  private static void writeNames() {
    try {
      PrintWriter output = new PrintWriter("AllBabyNamesSortedAllowDuplicates.txt");
      for (int i = 1; i <= 1000 * 10 * 2; i++) {
        if (i % 10 == 0)
          output.println(names[i - 1]); 
        else
          output.print(names[i - 1] + " "); 
      }
      
      output.close();
      
      System.out.println("Done");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}