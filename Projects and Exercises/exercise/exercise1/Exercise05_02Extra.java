public class Exercise05_02Extra {
  public static void main(String[] args) {
    final int NUMBER_OF_TRIALS = 1000000;
    int numberOfHits = 0;

    for (int i = 0; i < NUMBER_OF_TRIALS; i++)
    {
      double x = Math.random() * 2.0 - 1;
      double y = Math.random() * 2.0 - 1;
      if (x < 0)
        numberOfHits += 1;
      else if (!(x > 1 || x < 0 || y > 1 || y < 0)) {
        double slope = (1.0 - 0) / (0 - 1.0);
        double x1 = x + -y * slope;
        if (x1 <= 1)
          numberOfHits += 1;      
      }
    }

    System.out.println("The probability in Region 1 and 3 is " +
      (1.0 * numberOfHits / NUMBER_OF_TRIALS));
  }
  
  /*
  static boolean isInRegion3(double x, double y) {
    if (x > 1 || x < 0 || y > 1 || y < 0)
      return false;
    else {
      double slope = (1.0 - 0) / (0 - 1.0);
      double x1 = x + -y * slope;
      if (x1 <= 1)
        return true;
      else
        return false;
    }
  }
  */
}
