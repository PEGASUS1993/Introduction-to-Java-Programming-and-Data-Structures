import java.text.*;
import java.util.Scanner;

public class Exercise36_07 {
  // Main method

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Enter yearly interest rate
    System.out.print("Enter yearly interest rate, for example 8.25: ");

    // Convert string to double
    double annualInterestRate = input.nextDouble();

    // Obtain monthly interest rate
    double monthlyInterestRate = annualInterestRate / 1200;

    // Enter number of years
    System.out.print("Enter number of years as an integer, \nfor example 5: ");
    int numOfYears = input.nextInt();

    // Enter loan amount
    System.out.print("Enter loan amount, for example 120000.95: ");
    double loanAmount = input.nextDouble();

    // Calculate payment
    double monthlyPayment = loanAmount * monthlyInterestRate
            / (1 - 1 / (Math.pow(1 + monthlyInterestRate, numOfYears * 12)));
    double totalPayment = monthlyPayment * numOfYears * 12;

    NumberFormat currencyForm =
            NumberFormat.getCurrencyInstance();

    // Display results
    System.out.println("The monthly payment is "
            + currencyForm.format(monthlyPayment));
    System.out.println("The total payment is "
            + currencyForm.format(totalPayment));
  }
}
