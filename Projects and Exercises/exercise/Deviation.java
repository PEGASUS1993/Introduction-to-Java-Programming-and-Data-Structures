// Deviation.java: Compute deviation
public class Deviation {
  /** Main method */
  public static void main(String[] args) {
    // Declare and create an array for 10 numbers
    double[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    // Print numbers
    printArray(numbers);

    // Display mean and deviation
    System.out.println("The mean is " + mean(numbers));
    System.out.println("The standard deviation is " +
      deviation(numbers));
  }

  /** Method for computing deviation of double values*/
  public static double deviation(double[] x) {
    double mean = mean(x);
    double squareSum = 0;

    for (int i = 0; i < x.length; i++) {
      squareSum += Math.pow(x[i] - mean, 2);
    }

    return Math.sqrt((squareSum) / (x.length - 1));
  }

  /** Method for computing deviation of int values*/
  public static double deviation(int[] x) {
    double mean = mean(x);
    double squareSum = 0;

    for (int i = 0; i < x.length; i++) {
      squareSum += Math.pow(x[i] - mean, 2);
    }

    return Math.sqrt((squareSum) / (x.length - 1));
  }

  /** Method for computing mean of an array of double values*/
  public static double mean(double[] x) {
    double sum = 0;

    for (int i = 0; i < x.length; i++)
      sum += x[i];

    return sum * 1.0 / x.length;
  }

  /** Method for computing mean of an array of int values*/
  public static double mean(int[] x) {
    int sum = 0;

    for (int i = 0; i < x.length; i++)
      sum += x[i];

    return sum / x.length;
  }

  /** Method for printing array */
  public static void printArray(double[] x) {
    for (int i = 0; i < x.length; i++)
      System.out.print(x[i] + " ");
    System.out.println();
  }
}
