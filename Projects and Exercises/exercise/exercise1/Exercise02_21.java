// Exercise02_11.java: Create a method for computing future value
public class Exercise02_21 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter the investment amount
    System.out.print(
      "Enter the investment amount, for example 120000.95: ");
    double investmentAmount = input.nextDouble();

    // Enter yearly interest rate
    System.out.print("Enter annual interest rate, for example 8.25: ");
    double annualInterestRate = input.nextDouble();

    // Obtain monthly interest rate
    double monthlyInterestRate = annualInterestRate / 1200;

    // Enter number of years
    System.out.print(
      "Enter number of years as an integer, for example 5: ");
    int numOfYears = input.nextInt();

    double futureValue =
      investmentAmount * Math.pow(1 + monthlyInterestRate,
      numOfYears * 12);

    System.out.print("Future value is " +
      (int)(futureValue * 100) / 100.0);
  }
}
