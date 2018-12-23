import java.util.Scanner;
import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Exercise30_06 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    double[] list2 = new double[10];
    System.out.print("Enter 10 double values: ");

    int i = 0;
    while (i < list2.length) {
      list2[i] = input.nextDouble();
      i++;
    }

    System.out.println(average(list2));
  }

  public static int average(int[] array) {
    return (int)IntStream.of(array).average().getAsDouble();
  }

  public static double average(double[] array) {
    return DoubleStream.of(array).average().getAsDouble();
  }
}
