import java.io.File;

public class Exercise12_06Extra {
  public static void main(String[] args) {
    // Check number of strings passed
    if (args.length != 1) {
      System.out.println(
        "Usage: java Exercise12_06Extra filename");
      System.exit(0);
    }
    
    File file = new File(args[0]);
    if (file.exists()) {
      if (changeName(file)) {
        System.out.println("Success. New file is " + file.getAbsolutePath().toString());
      }
      else {
        System.out.println("Failed");
      }
    }
    else {
      System.out.println("The file does not exist");
    }
  }
  
  public static boolean changeName(File file) {
    java.util.Date date = new java.util.Date();
    File newFile = new File(file.getAbsoluteFile().toString() + date.toString().replaceAll(":", "_"));
    return file.renameTo(newFile);
  }
}