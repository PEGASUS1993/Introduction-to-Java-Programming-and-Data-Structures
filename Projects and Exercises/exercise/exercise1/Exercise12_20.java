import java.io.*;
import java.util.Scanner;

public class Exercise12_20 {
  public static void main(String[] args) throws Exception {
    // Check command line parameter usage
    if (args.length != 1) {
      System.out.println("Usage: java Exercise12_20 srcRootDirectory");
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
          removePackageStatementFromFilesInDirectory(files[i]);
        }
    }
  }

  private static void removePackageStatementFromFilesInDirectory(File directory)
      throws Exception {
    File[] files = directory.listFiles();

    for (int i = 0; i < files.length; i++)
      if (files[i].isFile() && files[i].getName().endsWith(".java")) {
        removePackageStatment(files[i]);
      }
  }

  private static void removePackageStatment(File file)
      throws Exception {
    StringBuilder sb = new StringBuilder();

    try (Scanner input = new Scanner(file)) {
      // Read the first line from the file
      String firstLine = input.nextLine();
      if (!firstLine.toString().startsWith("package"))
        sb.append(firstLine + "\r\n");
      
      while (input.hasNext()) {
        sb.append(input.nextLine() + "\r\n");
      }
    }
    
    try ( // Write back to the file
      PrintWriter output = new PrintWriter(file);
    ) {
      output.printf("%s", sb.toString());
    }
  }
}
