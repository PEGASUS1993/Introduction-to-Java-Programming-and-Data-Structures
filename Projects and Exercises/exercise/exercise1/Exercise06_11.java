public class Exercise06_11 {

  public static void main(String[] args) {
    System.out.println("salesAmount\tCommission");

    for (int salesAmount = 10000; salesAmount <= 100000; salesAmount = salesAmount + 5000) {
      System.out.println(salesAmount + "\t\t" + computeCommission(salesAmount));
    }
  }

  public static double computeCommission(double salesAmount) {
    double commission;

    if (salesAmount >= 10000.01)
      commission =
      5000 * 0.08 + 5000 * 0.1 + (salesAmount - 10000) * 0.12;
    else if (salesAmount >= 5000.01)
      commission = 5000 * 0.08 + (salesAmount - 5000) * 0.10;
    else
      commission = salesAmount * 0.08;

    return commission;
  }
}
