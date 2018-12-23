public class Exercise05_33 {
  public static void main(String[] args) {
    for (int number = 6; number <= 10000; number++) {
      int sum = 0;
      int divisor = number - 1;
      while (divisor >= 1) {
        if (number % divisor == 0) {
          sum += divisor;
        }

        divisor--;
      }

      if (number == sum) {
        System.out.println(number);
      }
    }
  }
}
