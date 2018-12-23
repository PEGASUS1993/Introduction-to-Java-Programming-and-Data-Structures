import java.io.*;

public class Exercise17_10 {
  public static void main(String[] args) throws Exception {
    // Check usage
    if (args.length != 2) {
      System.out.println("Usage: java Exercise17_10 SourceFile numberOfPieces");
      System.exit(1);
    }

    try (
      BufferedInputStream input = new BufferedInputStream(new FileInputStream(
          new File(args[0])));
    ) {
      int numberOfPieces = Integer.parseInt(args[1]);
  
      System.out.println("File size: " + input.available() + " bytes");
      long fileSize = input.available();
      int splitFileSize = (int)
        Math.ceil(1.0 * fileSize / numberOfPieces);
    
      for (int i = 1; i <= numberOfPieces; i++) {
        try (
          BufferedOutputStream output = new BufferedOutputStream(
            new FileOutputStream(new File(args[0] + "." + i)));
        ) {
          int value;
          int count = 0;
          // What is wrong if these two conditions are placed in a different order?
          while (count++ < splitFileSize && (value = input.read()) != -1) {
            output.write(value);
          }
        }
      }
    }
  }
}
