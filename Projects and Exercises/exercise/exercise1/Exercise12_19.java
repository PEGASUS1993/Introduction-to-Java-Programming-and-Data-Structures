import java.util.Scanner;

public class Exercise12_19 {
  public static void main(String[] args) {
    String URLString = "http://cs.armstrong.edu/liang/Lincoln.txt";
       
    try {
      java.net.URL url = new java.net.URL(URLString); 
      int count = 0;
      try (Scanner input = new Scanner(url.openStream())) {
        while (input.hasNext()) {
          String word = input.next();
          if (word.trim().length() > 0)
            count += 1;
        } 
      }
      System.out.println("The number of words is " + count);
    }
    catch (java.net.MalformedURLException ex) {
      System.out.println("Invalid URL");
    }
    catch (java.io.IOException ex) {
      System.out.println("IO Errors: no such file");
    }
  }
}   

/** A better alternative solution
import java.net.URL;
import java.util.*;

public class Exercise12_19 {
  public static void main(String[] args) throws Exception {
    URL url = new URL("http://cs.armstrong.edu/liang/data/Lincoln.txt");
    Scanner input = new Scanner(url.openStream());
    int total = 0;
    
    while (input.hasNext()) {
      String line = input.nextLine();
      total += getNumberOfWords(line);    
    }
    
    System.out.println("The total number of words is " + total);
  }
  
  public static int getNumberOfWords(String s) {
    // Split s by punctuations
    String[] tokens = s.split("[\\s\\p{P}]"); 
    
    int count = 0;
   
    for (String token: tokens) {
      if (token.trim().length() > 0) {
        count++;
      }
    }
    return count;
  }
}
*/