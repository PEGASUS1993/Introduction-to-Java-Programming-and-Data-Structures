import java.io.*;
import java.util.Scanner;

public class Exercise18_30 {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println(
        "Usage: java Exercise18_30 directoryName word");
      System.exit(0);
    }

    findInFile(new File(args[0]), args[1]);
  }

  public static long findInFile(File file, String word) {
    long numberOfFiles = 0; // Store the total size of all files

    if (file.isDirectory()) {
      File[] files = file.listFiles(); // All files and subdirectories
      for (int i = 0; i < files.length; i++) {
        findInFile(files[i], word); // Recursive call
      }
    }
    else { // Base case
      findWord(file, word);
      numberOfFiles++;
    }

    return numberOfFiles;
  }

  public static void findWord(File file, String word) {
    try (Scanner input = new Scanner(file)) {
      while (input.hasNext()) {
        String line = input.nextLine();
        if (line.indexOf(word) > -1) {
          System.out.println(file + ": " + line);
        }
      }
      input.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
