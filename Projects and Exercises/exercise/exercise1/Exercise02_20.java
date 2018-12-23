import java.util.Scanner;

public class Exercise02_20 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Obtain input
    System.out.print("Enter balance and annual interest rate: ");
    double balance = input.nextDouble();
    double annualInterestRate = input.nextDouble();
    
    double monthlyInterestRate = annualInterestRate / 1200;

    double interest = balance * monthlyInterestRate;

    // Display output
    System.out.println("The interest is " + (int)(100* interest) / 100.0);
  }
}
