import java.io.*;

public class Exercise18_01Extra {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println(
        "Usage: java Exercise18_01 directoryName");
      System.exit(0);
    }

    File file = findLargestFile(new File(args[0]));
    System.out.println("The largest file is \n" + file.getAbsolutePath()
      + "\nSize is " + file.length() + " bytes\n" + 
        "Last modified on " + new java.util.Date(file.lastModified()));
  }

  public static File findLargestFile(File file) {
    File result = null; // Store the total size of all files

    if (file.isDirectory()) {
      File[] files = file.listFiles(); // All files and subdirectories
      for (int i = 0; i < files.length; i++) {
        long size = result == null ? 0 : result.length();
        File file1 = findLargestFile(files[i]);
        
        if (file1 != null && size < file1.length())
          result = file1; // Recursive call
      }
    }
    else { // Base case
      long size = result == null ? 0 : result.length(); 
      if (size < file.length())
        result = file; // Recursive call
    }

    return result;
  }
}
