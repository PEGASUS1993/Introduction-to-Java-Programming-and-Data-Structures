public class Exercise13_18 {
  public static void main(String[] args) {
    final int N = 100;
    Rational sum = new Rational();
    for (int i = 1; i <= N; i++)
      sum = sum.add(new Rational(i - 1, i));

    System.out.println("The sum of the first series is " + sum + " = " +
      sum.doubleValue());
  }
}
