import java.io.*;
import java.util.*;

public class Exercise12_13 {
  public static void main(String[] args) throws Exception {
    // Check usage
    if (args.length != 1) {
      System.out.println("Usage: java Exercise12_13 file");
      System.exit(1);
    }

    // Check if source file exists
    File sourceFile = new File(args[0]);
    if (!sourceFile.exists()) {
       System.out.println("Source file " + args[0] + " does not exist");
       System.exit(2);
    }

    // Create a Scanner for the file
    Scanner input = new Scanner(sourceFile);

    int wordCount = 0, lineCount = 0;
    while (input.hasNext()) {
      String oneLine = input.nextLine();
      lineCount++;
      wordCount += countWords(oneLine);
    }

    input.close();

    System.out.println("File " + sourceFile + " has ");
    System.out.println(sourceFile.length() + " characters");
    System.out.println(wordCount + " words");
    System.out.println(lineCount + " lines");
  }

  private static int countWords(String s) {
    String[] words = s.split("[ \\t\\n\\r]");
    int count = 0;
    
    for (String word: words)
      if (word.length() > 0)
        count++;
    
    return count;
  }
  
//  /** Alternative way */
//  private static int countWords(String s) {
//    Scanner input = new Scanner(s);
//    int count = 0;
//
//    while (input.hasNext()) {
//      input.next(); 
//      count++;
//    }
//
//    return count;
//  }
}
