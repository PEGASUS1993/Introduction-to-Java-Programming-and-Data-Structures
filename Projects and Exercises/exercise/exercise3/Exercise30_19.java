import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.util.stream.DoubleStream;

public class Exercise30_19 {
  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.out.println(
        "Usage: java Exercise30_19 fullfilename");
      System.exit(1);
    }

    String filename = args[0];

    double[] values = Files.lines(new File(filename).toPath()).map(line -> 
      Stream.of(line.split("[\\s+]"))).reduce((e1, e2) -> Stream.concat(e1, e2)).get()
      .mapToDouble(e -> Double.parseDouble(e)).toArray();

    System.out.println("Sum is " + DoubleStream.of(values).sum());
    System.out.println("Average is " + DoubleStream.of(values).average().getAsDouble());
    System.out.println("Max is " + DoubleStream.of(values).max().getAsDouble());
    System.out.println("Min is " + DoubleStream.of(values).min().getAsDouble());
  }
}
