// Finding sales amount to meet the commission
public class Exercise07_10Extra {
  // The commission sought
  final static double COMMISSION_SOUGHT = 25000;

  // Main method
  public static void main(String[] args) {
    double commission = 0;
    int low = 0;
    int high = (int)(COMMISSION_SOUGHT / 0.08);

    while (low < high - 1) {
      int mid = (low + high) / 2;

      // Compute commission
      if (mid >= 10001)
        commission = 5000 * 0.08 + 5000 * 0.1 + (mid - 10000) * 0.12;
      else if (mid >= 5001)
        commission = 5000 * 0.08 + (mid - 5000) * 0.10;
      else
        commission = mid * 0.08;

      if (commission == COMMISSION_SOUGHT) {
        break;
      }
      else if (commission < COMMISSION_SOUGHT) {
        low = mid;
      }
      else {
        high = mid;
      }
    }

    // Display the sales amount
    System.out.println("The sales amount " + high +
      " is needed to make a commission of $" + COMMISSION_SOUGHT);
  }
}
