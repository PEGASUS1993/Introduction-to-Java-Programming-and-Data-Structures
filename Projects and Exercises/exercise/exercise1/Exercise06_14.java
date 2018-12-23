public class Exercise06_14 {
  public static void main(String[] args) {
    System.out.printf("%-20s%-20s\n", "i", "m(i)");
    for (int i = 1; i <= 1000; i += 100)
      System.out.printf("%-20d%-20.4f\n", i, m(i));
  }

  public static double m(int i) {
    double pi = 0;
    double sign = 1;
    
    for (int k = 1; k <= i; k++) {
      pi += sign / (2 * k - 1.0);
      sign = -1 * sign;
    }

    return 4 * pi;
  }
}
