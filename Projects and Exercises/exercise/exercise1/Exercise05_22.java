public class Exercise05_22 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter loan amount
    System.out.print(
      "Enter loan amount, for example 120000.95: ");
    double loanAmount = input.nextDouble();

    // Enter number of years
    System.out.print(
      "Enter number of years as an integer, for example 5: ");
    int numOfYears = input.nextInt();

    // Enter yearly interest rate
    System.out.print("Enter yearly interest rate, for example 8.25: ");
    double annualInterestRate = input.nextDouble();

    // Obtain monthly interest rate
    double monthlyInterestRate = annualInterestRate/1200;

    // Compute mortgage
    double monthlyPayment = loanAmount*monthlyInterestRate /
      (1 - (Math.pow(1 / (1 + monthlyInterestRate), numOfYears * 12)));

    double balance = loanAmount;
    double interest;
    double principal;

    System.out.println("Monthly Payment: " + (int)(monthlyPayment * 100) / 100.0 );
    System.out.println("Total Payment: " + (int)(monthlyPayment * 12 * numOfYears * 100) / 100.0 + "\n" );

    // Display the header
    System.out.printf("%-15s%-15s%-15s%-15s\n", "Payment#", "Interest", "Principal", "Balance");
    for (int i = 1; i <= numOfYears * 12; i++) {
      interest = monthlyInterestRate * balance;
      principal = monthlyPayment - interest;
      balance = balance - principal;
      System.out.printf("%-15d%-15.2f%-15.2f%-15.2f\n", i, interest, principal, balance);
    }
  }
}
