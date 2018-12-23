import java.io.*;
import java.util.*;

public class Exercise12_22 {
  public static void main(String[] args) throws Exception {
    // Check command line parameter usage
    if (args.length != 3) {
      System.out.println("Usage: java Exercise12_22 dir oldStr newStr");
      System.exit(1);
    }

    // Check if source file exists
    File currentDir = new File(args[0]);
    System.out.println(currentDir.getName());

    if (!currentDir.exists()) {
      System.out.println("Current directory " + args[0] + " does not exist");
      System.exit(2);
    }

    if (currentDir.isFile()) {
      replaceInAFile(currentDir, args[1], args[2]); // currentDir is a file
    } else {
      File[] files = currentDir.listFiles();
      for (int i = 0; i < files.length; i++)
        if (files[i].isFile())
          replaceInAFile(files[i], args[1], args[2]);
    }
  }

  private static void replaceInAFile(File sourceFile, String oldStr,
      String newStr) {
    StringBuilder sb = new StringBuilder();

    try {
      try ( // Read text from the file and save it in a string builder
        Scanner input = new Scanner(sourceFile);
      ) {
        while (input.hasNext()) {
          String s1 = input.nextLine();
          String s2 = s1.replaceAll(oldStr, newStr);
          sb.append("\r\n" + s2);
        }
      }
  
      try ( // Write back to the file
        PrintWriter output = new PrintWriter(sourceFile);
      ) {
        output.printf("%s\r\n", sb.toString());
      } 
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
