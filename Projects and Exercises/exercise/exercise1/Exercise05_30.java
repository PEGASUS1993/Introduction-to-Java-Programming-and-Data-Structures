import java.util.Scanner;

public class Exercise05_30 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the amount to be saved for each month: ");
    double monthlyDeposit = input.nextDouble();

    System.out.print("Enter the annual interest rate: ");
    double annualInterestRate = input.nextDouble();
    double monthlyInterestRate = annualInterestRate / 1200;

    System.out.print("Enter the number of months: ");
    int numberOfMonths = input.nextInt();

    double currentValue = monthlyDeposit * (1 + monthlyInterestRate);
    for (int i = 1; i < numberOfMonths; i++) {
      currentValue = (currentValue + monthlyDeposit) * (1 + monthlyInterestRate);
    }

    System.out.print("After the " + numberOfMonths +
      "th month, the account value is ");
    System.out.printf("%.2f\n", currentValue);
  }
}
