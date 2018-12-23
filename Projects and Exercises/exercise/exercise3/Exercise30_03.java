import java.util.Scanner;
import java.util.stream.DoubleStream;

public class Exercise30_03 {
  // Main method
  public static void main(String[] args) {
    double[] scores = new double[100];
    int count = 0;
    Scanner input = new Scanner(System.in);
    do {
      System.out.print("Enter a new score: ");
      scores[count] = input.nextDouble();
    } while (scores[count++] >= 0);

    double average = DoubleStream.of(scores)
            .limit(count).average().getAsDouble();
    long numOfAbove = DoubleStream.of(scores)
            .limit(count).filter(e -> e >= average).count();
    long numOfBelow = DoubleStream.of(scores)
            .limit(count).filter(e -> e < average).count();

    System.out.println("Average is " + average);
    System.out.println("Number of scores above or equal to the average "
        + numOfAbove);
    System.out.println("Number of scores below the average " + numOfBelow);
  }
}
