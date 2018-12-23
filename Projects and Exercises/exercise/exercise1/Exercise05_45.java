public class Exercise05_45 {
  public static void main(String[] args) {
    final int COUNT = 10; // Total numbers
    double sum = 0; // Store the sum of the numbers
    double squareSum = 0; // Store the sum of the squares

    java.util.Scanner input = new java.util.Scanner(System.in);

    // Create numbers, find its sum, and its square sum
    for (int i = 0; i < COUNT; i++) {
      // Get a new number
      System.out.print("Enter a number: ");
      double number = input.nextDouble();

      // Add the number to sum
      sum += number;

      // Add the square of the number to squareSum
      squareSum += Math.pow(number, 2); // Same as number*number;
    }

    // Find mean
    double mean = sum / COUNT;

    // Find standard deviation
    double deviation =
      Math.pow((squareSum - sum * sum / COUNT) / (COUNT - 1), 0.5);

    // Display result
    System.out.println("The mean is " + mean);
    System.out.println("The standard deviation is " + deviation);
  }
}
