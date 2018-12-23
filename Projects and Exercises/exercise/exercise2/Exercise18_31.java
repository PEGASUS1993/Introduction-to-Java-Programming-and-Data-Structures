import java.io.*;
import java.util.Scanner;

public class Exercise18_31 {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println(
        "Usage: java Exercise18_31 directory oldWord newWord");
      System.exit(1);
    }

    replaceInFile(new File(args[0]), args[1], args[2]);
  }

  public static long replaceInFile(File file, String oldWord,
    String newWord) {
    long numberOfFiles = 0; // Store the total size of all files

    if (file.isDirectory()) {
      File[] files = file.listFiles(); // All files and subdirectories
      for (int i = 0; i < files.length; i++) {
        replaceInFile(files[i], oldWord, newWord); // Recursive call
      }
    }
    else { // Base case
      replaceWord(file, oldWord, newWord);
      numberOfFiles++;
    }

    return numberOfFiles;
  }

  public static void replaceWord(File file, String oldWord, String newWord) {
    String lineSeparator = System.getProperty("line.separator");
    String line = "";
    try {
      try (Scanner input = new Scanner(file)) {
        while (input.hasNext()) {
          line += input.nextLine() + lineSeparator;
        }
      }

      if (line.indexOf(oldWord) > 0) {
        String newContent = line.replaceAll(oldWord, newWord);
        try (PrintWriter output = new PrintWriter(file)) {
          output.print(newContent);
        }
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
