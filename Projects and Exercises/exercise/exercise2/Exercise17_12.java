import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Exercise17_12 {
  public static void main(String[] args) throws Exception {
    // Check usage
    if (args.length < 2) {
      System.out.println(
        "Usage: java Exercise17_12 SourceFile1 ... SoureFilen TargetFile");
      System.exit(1);
    }

    try (
      // The last file TargetFile is for output
      BufferedOutputStream output = new BufferedOutputStream(
        new FileOutputStream(new File(args[args.length - 1])));
    ) {
      for (int i = 0; i < args.length - 1; i++) {
        try (
          BufferedInputStream input = new BufferedInputStream(
            new FileInputStream(new File(args[i])));
        ) {
          int value;
          while ((value = input.read()) != -1) {
            output.write(value);
          }
        }
      }
    }
  }
}