import java.util.*;
import java.io.*;

public class Exercise21_10 {
  public static void main(String[] args) {
    // Prompt the user to enter a Java source file
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a file name: ");
    String filename = input.nextLine();

    // Array of all Java keywords + true + null
    String[] keywordString = { "abstract", "finally", "public", "boolean",
        "float", "return", "break", "for", "short", "byte", "goto", "static",
        "case", "if", "super", "catch", "implements", "switch", "char",
        "import", "synchronized", "class", "instanceof", "this", "const",
        "int", "throw", "continue", "interface", "throws", "default", "long",
        "transient", "do", "native", "try", "double", "new", "void", "else",
        "package", "volatile", "extends", "private", "while", "final",
        "protected", "true", "null" };

    Set<String> keywordSet = new HashSet<String>(Arrays.asList(keywordString));
    int count = 0;

    try {
      input = new Scanner(new File(filename));

      String text = "";
      while (input.hasNext()) {
        String line = input.nextLine();
        line = stripLineComments(line);
        line = stripLineStringLiterals(line);
        text += line + " ";
      }

      text = stripParagraghComments(text);

      TreeMap<String, Integer> map = new TreeMap<String, Integer>();

      String[] tokens = text.split("[ \\[,()\\]]");
      for (String token : tokens) {
        if (keywordSet.contains(token))
          if (map.get(token) == null) {
            map.put(token, 1);
          } else {
            int value = map.get(token);
            value++;
            map.put(token, value);
          }
      }

      // Get all entries into a set
      Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

      // Get key and value from each entry
      for (Map.Entry<String, Integer> entry: entrySet)
        System.out.println(entry.getValue() + "\t" + entry.getKey());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /* Strip line comments */
  private static String stripLineComments(String line) {
    int index = line.indexOf("//");

    if (index < 0)
      return line;
    else
      return line.substring(0, index);
  }

  /* Strip string literals */
  private static String stripLineStringLiterals(String line) {
    int start = line.indexOf("\"");
    int end = line.indexOf("\"", start + 1);

    while (start > 0 && end > 0) {
      line = line.substring(0, start) + line.substring(end + 1);
      start = line.indexOf("\"");
      end = line.indexOf("\"");
    }

    return line;
  }

  /* Strip paragraph comments */
  private static String stripParagraghComments(String text) {
    int start = text.indexOf("/*");
    int end = text.indexOf("*/");

    while (start > 0 && end > 0) {
      text = text.substring(0, start) + text.substring(end + 2);
      start = text.indexOf("/*");
      end = text.indexOf("*/");
    }

    return text;
  }
}
