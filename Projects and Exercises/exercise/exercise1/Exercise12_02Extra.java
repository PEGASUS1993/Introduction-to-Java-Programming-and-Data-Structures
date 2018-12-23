import java.util.*;
import java.io.*;

public class Exercise12_02Extra { 
  private static ArrayList<String> names = new ArrayList<String>();

  private static void readNames() {
    try {
      for (int i = 0; i < 10; i++) {
        String filename;
        if (i == 9)
          filename = "Babynamesranking2010.txt";
        else
          filename = "Babynamesranking200" + (i + 1) + ".txt";
        Scanner input = new Scanner(new File(filename));
        
        while (input.hasNext()) {
          input.nextInt(); // Skip the ranking
          String name = input.next();
          if (!names.contains(name))
            names.add(name);
          input.nextInt(); // Skip the number of boy's name
          name = input.next();
          if (!names.contains(name))
            names.add(name);
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
    
    Collections.sort(names); // If you don't know how to use this method, write your own method to sort the elements in an array list
    
    writeNames();
  }
  
  private static void writeNames() {
    try {
      PrintWriter output = new PrintWriter("AllBabyNamesSortedDuplicatesNotAllowed.txt");
      for (int i = 1; i <= names.size(); i++) {
        if (i % 10 == 0)
          output.println(names.get(i - 1)); 
        else
          output.print(names.get(i - 1) + " "); 
      }
      
      output.close();
      
      System.out.println("Done");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}