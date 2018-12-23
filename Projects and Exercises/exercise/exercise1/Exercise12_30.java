import java.util.*;
import java.io.*;

public class Exercise12_30 {
  public static void main(String[] args) throws Exception {
    Scanner consoleInput = new Scanner(System.in);
    System.out.print("Enter file name: ");
    String filename = consoleInput.nextLine();
    
    int[] counts = new int[26];

    try (Scanner input = new Scanner(new File(filename))) {
      while (input.hasNext()) {
        String s = input.nextLine();
        
        for (int i = 0; i < s.length(); i++)
          if (Character.isLetter(s.charAt(i)))
            counts[Character.toUpperCase(s.charAt(i)) - 'A']++;
      }
    }

    displayCounts(counts);
  }
  
  public static void displayCounts(int[] counts) {
    for (int i = 0; i < counts.length; i++)
      System.out.println("The occurrence of " + (char)(i + 'A') + "'s is "
          + counts[i]);
  }
}
