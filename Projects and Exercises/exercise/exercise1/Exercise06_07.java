public class Exercise06_07 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter loan amount
    System.out.print(
      "Enter investment amount, for example 100: ");
    double investmentAmount =  input.nextDouble();

    // Enter yearly interest rate
    System.out.print(
      "Enter yearly interest rate, for example 5.25: ");
    double annualInterestRate = input.nextDouble();

    System.out.printf("%-5s%20s\n", "Years", "Future Value");

    for (int i = 1; i <= 30; i++) {
      double futureValue =
        futureInvestmentValue(investmentAmount, annualInterestRate / 1200, i);
      System.out.printf("%-5d%20.2f\n", i, futureValue);
    }
  }

  public static double futureInvestmentValue(double investmentAmount,
      double monthlyInterestRate, int years) {
    return investmentAmount * Math.pow(1 + monthlyInterestRate, years * 12);
  }
}
