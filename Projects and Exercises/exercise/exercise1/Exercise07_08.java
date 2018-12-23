import java.util.Scanner;

public class Exercise07_08 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    double[] list2 = new double[10];
    System.out.print("Enter 10 double values: ");

    int i = 0;
    while (i < list2.length) {
      list2[i] = input.nextDouble();
      i++;
    }

    System.out.println("The average value is " + average(list2));
  }

  public static int average(int[] array) {
    int sum = 0;
    for (int i = 0; i < array.length; i++) {
      sum += array[i];
    }
    return sum / array.length;
  }

  public static double average(double[] array) {
    double sum = 0;
    for (int i = 0; i < array.length; i++) {
      sum += array[i];
    }
    return sum / array.length;
  }
}