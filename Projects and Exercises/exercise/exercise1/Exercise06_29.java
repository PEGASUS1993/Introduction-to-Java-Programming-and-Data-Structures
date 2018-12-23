public class Exercise06_29 {
  public static void main(String[] args) {
    int p1 = 2;
    for (int p = 2; p <= 1000; p++) {
      if (isPrime(p)) {
        if (p - p1 == 2) {
          System.out.println("(" + p1 + ", " + p + ")");
        }

        p1 = p;
      }
    }
  }

  public static boolean isPrime(int num) {
    if ((num == 1) || (num == 2)) {
      return true;
    }

    for (int i = 2; i <= num / 2; i++) {
      if (num % i == 0) {
        return false;
      }
    }

    return true;
  }
}
