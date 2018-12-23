import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise12_27 {
  public static void main(String[] args) {
    for (int i = 0; i < args.length; i++)
      if (!args[i].endsWith(".class"))
         replaceWord(new File(args[i]));
  }

  public static void replaceWord(File file) {
    String lineSeparator = System.getProperty("line.separator");
    String line = "";
    try {
      try (Scanner input = new Scanner(file)) {
        while (input.hasNext()) {
          line += getNewLine(input.nextLine()) + lineSeparator;
        }
      }

      try (PrintWriter output = new PrintWriter(file)) {
        output.print(line);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  // Return a new line padded with 0 for Exercise
  public static String getNewLine(String line) {   
    int k = line.indexOf("Exercise");
    
    while (k >= 0) {
      int p = k + "Exercise".length();
      if (line.length() >= p + 3 && Character.isDigit(line.charAt(p))
          && line.charAt(p + 1) == '_' && Character.isDigit(line.charAt(p + 2))
          && (line.length() == p + 3 || !Character.isDigit(line.charAt(p + 3)))) {
        line = line.substring(0, p) + "0" + line.charAt(p)
            + line.charAt(p + 1) + "0" + line.substring(p + 2);
      } else if (line.length() >= p + 4 && Character.isDigit(line.charAt(p))
          && Character.isDigit(line.charAt(p + 1)) && line.charAt(p + 2) == '_'
          && Character.isDigit(line.charAt(p + 3))
          && (line.length() == p + 4 || !Character.isDigit(line.charAt(p + 4)))) {
        line = line.substring(0, p + 3) + "0" + line.substring(p + 3);
      } else if (line.length() >= p + 4 && Character.isDigit(line.charAt(p))
          && line.charAt(p + 1) == '_' && Character.isDigit(line.charAt(p + 2))
          && Character.isDigit(line.charAt(p + 3))) {
        line = line.substring(0, p) + "0" + line.substring(p);
      }
      
      k = line.indexOf("Exercise", p);
    }

    return line;
  }
}
