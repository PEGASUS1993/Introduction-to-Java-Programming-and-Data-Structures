public class Exercise05_23 {
  final static int N = 50000;

  public static void main (String[] args) {
    // Backward computing
    double sum = 0.0;
    for (int i = N; i >= 1; i--)
      sum += 1.0 / (double)(i);
    System.out.println("The result of the backward computation " + sum);
    
    // Forward computing
    double sum1 = 0.0;
    for (int i = 1; i <= N; i++)
      sum1 += 1.0 / (double)(i);
    System.out.println("The result of the forward computation " + sum1);

    double difference = sum - sum1;
    System.out.println("difference is " + difference);
  }

  /*
  public static double backward() {
    double sum = 0.0;
    for (int i = N; i >= 1; i--)
      sum += 1.0 / (double)(i);
    return sum;
  }

  static double forward() {
    double sum = 0.0;
    for (int i = 1; i <= N; i++)
      sum += 1.0 / (double)(i);
    return sum;
  } */
}
