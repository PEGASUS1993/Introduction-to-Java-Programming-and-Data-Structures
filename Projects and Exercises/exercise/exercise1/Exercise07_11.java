// Exercise07_11.java: Compute deviation
public class Exercise07_11 {
  public static void main(String[] args) {
    double[] numbers = new double[10];
    
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter ten numbers: ");

    for (int i = 0; i < numbers.length; i++) 
      numbers[i] = input.nextDouble();

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

    return Math.sqrt(squareSum / (x.length - 1));
  }

  /** Method for computing deviation of int values*/
  public static double deviation(int[] x) {
    double mean = mean(x);
    double squareSum = 0;

    for (int i = 0; i < x.length; i++) {
      squareSum += Math.pow(x[i] - mean, 2);
    }

    return Math.sqrt(squareSum / (x.length - 1));
  }

  /** Method for computing mean of an array of double values*/
  public static double mean(double[] x) {
    double sum = 0;

    for (int i = 0; i < x.length; i++)
      sum += x[i];

    return sum / x.length;
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
