import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.net.URL;

public class Exercise12_01Extra { 
  public static void main(String[] args) {
    String filename;
    try (Scanner input = new Scanner(System.in)) {
      System.out.print("Enter a file name for baby name ranking: ");
      filename = input.nextLine();
    }
    
    URL url = null;
    try {
      url = new URL("http://cs.armstrong.edu/liang/data/" + filename);
    }
    catch (java.net.MalformedURLException ex) {
      ex.printStackTrace();
    }
    
    // Open the file
    try (Scanner input = new Scanner(url.openStream())) {   
      ArrayList<String> boyNames = new ArrayList<>();
      ArrayList<String> girlNames = new ArrayList<>();
      
      while (input.hasNext()) {
        input.nextInt(); // Skip an integer for ranking
        boyNames.add(input.next());
        input.nextInt(); // Skip an integer for number of boys
        girlNames.add(input.next());
        input.nextInt(); // Skip an integer for number of girls
      }
      
      boyNames.retainAll(girlNames); // If you don't know how to use the retainAll method, write your own code to obtain the common elements in two array lists.
      System.out.println(boyNames.size() + " names are used for both boys and girls ");
      System.out.println("They are ");
      for (String name: boyNames) {
        System.out.print(name + " ");
      }
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}