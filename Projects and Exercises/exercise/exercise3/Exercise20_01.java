import java.util.*;
import java.io.*;

public class Exercise20_01 {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println(
        "Usage: java Exercise20_01 fullfilename");
      System.exit(1);
    }

    String filename = args[0];

    // Create a list to hold the words
    List<String> list = new ArrayList<>();

    try {
      Scanner in = new Scanner(new File(filename));

      while (in.hasNext()) {
        String word = in.next();

        if (word.matches("[a-z|A-Z].*"))
          list.add(word);
      }
    }
    catch (Exception ex) {
      System.err.println(ex);
    }

    // Get an iterator for the list
    Collections.sort(list);

    // or since JDK 1.8
    // list.sort(null);

    // Display mappings
    System.out.println("Display words in ascending order ");
    for (String word: list) {
      System.out.println(word);
    }
  }
}
