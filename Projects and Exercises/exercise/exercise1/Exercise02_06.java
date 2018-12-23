// Exercise02_06.java: Summarize all digits in an integer < 1000
public class Exercise02_06 {
  // Main method
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    // Read a number
    System.out.print("Enter an integer between 0 and 1000: ");
    int number = input.nextInt();

    // Find all digits in number
    int lastDigit = number % 10;
    int remainingNumber = number / 10;
    int secondLastDigit = remainingNumber % 10;
    remainingNumber = remainingNumber / 10;
    int thirdLastDigit = remainingNumber % 10;

    // Obtain the sum of all digits
    int sum = lastDigit + secondLastDigit + thirdLastDigit;

    // Display results
    System.out.println("The sum of all digits in " + number
      + " is " + sum);
  }
}
