import java.io.*;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Exercise30_15 {
  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.out.println(
        "Usage: java Exercise30_15 fullfilename");
      System.exit(1);
    }

    String filename = args[0];

    Files.lines(new File(filename).toPath()).parallel().map(line -> 
      Stream.of(line.split("[\\s+\\p{P}]")))
      .reduce((e1, e2) -> Stream.concat(e1, e2)).get()
      .map(e -> e.trim()).filter(e -> e.length() > 0).sorted().forEach(word -> 
       System.out.println(word));
  }
}
