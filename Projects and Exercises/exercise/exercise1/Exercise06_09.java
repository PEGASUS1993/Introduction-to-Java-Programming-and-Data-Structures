public class Exercise06_09 {
  public static void main(String[] args) {
    System.out.printf("%4s%10s%20s%10s\n", "Feet", "Meters", "Meters", "Feet");
    System.out.println("---------------------------------------------");

    double foot = 1; double meter = 20;
    for (int i = 1; i <= 10; foot++, meter += 5, i++) {
      System.out.printf("%4.1f%10.3f%20.1f%10.3f\n", foot, footToMeter(foot), meter, meterToFoot(meter));
    }
  }

  /** Converts from feet to meters */
  public static double footToMeter(double foot) {
    return 0.305 * foot;
  }

  /** Converts from meters to feet */
  public static double meterToFoot(double meter) {
    return (1 / 0.305) * meter;
  }
}
