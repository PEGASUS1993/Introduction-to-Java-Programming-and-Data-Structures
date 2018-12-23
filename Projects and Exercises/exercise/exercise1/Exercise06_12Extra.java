import java.util.Scanner;

public class Exercise06_12Extra {
  public static void main(String[] args) {   
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Enter yearly interest rate
    System.out.print("Enter yearly interest rate, for example 8.25: ");
    double annualInterestRate = input.nextDouble();

    // Enter number of years
    System.out.print(
      "Enter number of years as an integer, for example 5: ");
    int numberOfYears = input.nextInt();
    
    // Enter loan amount
    System.out.print("Enter loan amount, for example 120000.95: ");
    double loanAmount = input.nextDouble();

    // Display results
    System.out.printf("The monthly payment is $%.2f\n", 
      getMonthlyPayment(annualInterestRate, numberOfYears, loanAmount));
    System.out.printf("The total payment is $%.2f", 
      getTotalPayment(annualInterestRate, numberOfYears, loanAmount));
  }
  
  public static double getMonthlyPayment(double annualInterestRate, 
      double numberOfYears, double loanAmount) {
    // Obtain monthly interest rate
    double monthlyInterestRate = annualInterestRate / 1200;

    // Calculate payment
    double monthlyPayment = loanAmount * monthlyInterestRate / (1
      - 1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12));

    return monthlyPayment;
  }
  
  public static double getTotalPayment(double annualInterestRate, 
      double numberOfYears, double loanAmount) {
    return getMonthlyPayment(annualInterestRate, numberOfYears, 
      loanAmount) * numberOfYears * 12;        
  }
}
