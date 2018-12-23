public class Exercise05_21 {
  // Main method
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter loan amount
    System.out.print("Enter loan amount, for example 120000.95: ");
    double loanAmount = input.nextDouble();

    // Enter number of years
    System.out.print("Enter number of years as an integer, \nfor example 5: ");

    // Convert string to int
    int numOfYears = input.nextInt();

    // Display the header
    System.out.printf("%-20s%-20s%-20s\n", "Interest Rate", "Monthly Payment", "Total Payment");

    for (double annualInterestRate = 5.0; annualInterestRate <= 8.0;
      annualInterestRate += 1.0 / 8) {
      // Obtain monthly interest rate
      double monthlyInterestRate = annualInterestRate / 1200;

      // Compute mortgage
      double monthlyPayment = loanAmount * monthlyInterestRate /
        (1 - (Math.pow(1 / (1 + monthlyInterestRate), numOfYears * 12)));
      double totalPayment = monthlyPayment * numOfYears * 12;

      // Display results
      System.out.printf("%5.3f%c%20.2f %20.2f\n", annualInterestRate, 
          '%', monthlyPayment, totalPayment);
    }
  }
}
