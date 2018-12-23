import java.io.*;
import java.util.*;

public class Exercise12_11 {
  public static void main(String[] args) throws Exception {
    // Check command line parameter usage
    if (args.length != 2) {
      System.out.println(
        "Usage: java Exercise12_11 stringToBeRemoved sourceFile");
      System.exit(1);
    }

    // Check if the file exists
    File sourceFile = new File(args[1]);
    if (!sourceFile.exists()) {
       System.out.println("Source file " + args[1] + " not exist");
       System.exit(2);
    }

    // Read text from the file and save it in a string builder
    Scanner input = new Scanner(sourceFile);
    StringBuilder sb = new StringBuilder();

    String lineSeparator = System.getProperty("line.separator");
    boolean firstLine = true;
    while (input.hasNext()) {
      String s1 = input.nextLine();
      String s2 = s1.replaceAll(args[0], "");
      if (firstLine) {
        sb.append(s2);
        firstLine = false;
      }
      else
        sb.append(lineSeparator + s2);
    }

    input.close();

    // Write back to the file
    PrintWriter output = new PrintWriter(sourceFile);
    output.println(sb.toString());
    output.close();
  }
}