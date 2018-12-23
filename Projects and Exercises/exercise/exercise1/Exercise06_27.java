public class Exercise06_27 {
  public static void main(String[] args) {
    for (int i = 2, count = 1; count <= 100; i++) {
      // Display each number in five positions
      if (isPrime(i) && !isPalindrome(i) && isPrime(reversal(i))) {
        System.out.print(count % 10 == 0 ? i + "\n" : i + " ");

        count++; // Increase count
      }
    }
  }

  public static boolean isPrime(int num) {
    for (int i = 2; i <= num / 2; i++) {
      if (num % i == 0) {
        return false;
      }
    }

    return true;
  }

  /** Return the reversal of number */
  static int reversal(int number) {
    int result = 0;

    while (number != 0) {
      int lastDigit = number % 10;
      result = result * 10 + lastDigit;
      number = number / 10;
    }

    return result;
  }

  /** Return true if number is palindromic */
  static boolean isPalindrome(int number) {
    return number == reversal(number);
  }
}
