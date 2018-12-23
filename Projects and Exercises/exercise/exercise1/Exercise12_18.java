import java.io.*;
import java.util.Scanner;

public class Exercise12_18 {
  public static void main(String[] args) throws Exception {
    // Check command line parameter usage
    if (args.length != 1) {
      System.out.println("Usage: java Exercise12_18 srcRootDirectory");
      System.exit(1);
    }

    // Check if source file exists
    File currentDir = new File(args[0]);

    if (!currentDir.exists()) {
      System.out.println("Current directory " + args[0] + " does not exist");
      System.exit(2);
    }

    if (!currentDir.isDirectory()) {
      System.out.println(args[0] + " is not a directory");
      System.exit(3);
    } 
    else {
      File[] files = currentDir.listFiles();
      for (int i = 0; i < files.length; i++)
        if (files[i].isDirectory() && files[i].getName().startsWith("chapter")) {
          String packageStatment = "package " + files[i].getName() + ";\r\n";
          insertIntoFilesInDirectory(files[i], packageStatment);
        }
    }
  }

  private static void insertIntoFilesInDirectory(File directory, String line)
      throws Exception {
    File[] files = directory.listFiles();

    for (int i = 0; i < files.length; i++)
      if (files[i].isFile() && files[i].getName().endsWith(".java")) {
        insertPackageStatment(files[i], line);
      }
  }

  private static void insertPackageStatment(File file, String line)
      throws Exception {
    StringBuilder sb = new StringBuilder(line);

    try (
      Scanner input = new Scanner(file);
    ) {
      while (input.hasNext()) 
        sb.append("\r\n" + input.nextLine());
    }

    try (
      // Write back to the file
      PrintWriter output = new PrintWriter(file);
    ) {
      output.printf("%s\r\n", sb.toString());
    }
  }
}
