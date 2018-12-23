public class Exercise05_42 {
  public static void main(String[] args) {
    double commission = 0;
    double salesAmount = 1;

    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter the desired commission
    System.out.print(
      "Enter the desired commission: ");

    // Convert string to double
    double commissionSought = input.nextDouble();

    for (salesAmount = 1; commission < commissionSought;
      salesAmount++) {
      // Compute commission
      if (salesAmount >= 10001)
        commission = 5000 * 0.08 + 5000 * 0.1 + (salesAmount - 10000) * 0.12;
      else if (salesAmount >= 5001)
        commission = 5000 * 0.08 + (salesAmount - 5000) * 0.10;
      else
        commission = salesAmount * 0.08;
    }

    // Display the sales amount
    System.out.println("The sales amount " + salesAmount +
      " is needed to make a commission of $" + commissionSought);
  }
}
