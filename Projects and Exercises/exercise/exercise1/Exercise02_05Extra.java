import java.util.Scanner;

public class Exercise02_05Extra {
  // Main method
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter final account value: ");
    double finalAccountValue = input.nextDouble();
    System.out.print("Enter annual interest rate in percent: ");
    double annualInterestRate = input.nextDouble();
    double monthlyInterestRate = annualInterestRate / 1200;
    
    System.out.print("Enter number of years: ");
    int numberOfYears = input.nextInt();
 
    double initialDepositAmount = finalAccountValue / 
      Math.pow(1 + monthlyInterestRate, numberOfYears * 12);
    
    System.out.println("Initial deposit value is " + initialDepositAmount);
  }
}
