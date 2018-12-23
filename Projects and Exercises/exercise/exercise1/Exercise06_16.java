public class Exercise06_16 {
  public static void main(String[] args) {
    for (int year = 2000; year <= 2020; year++) {
      System.out.println(year + " has " + numberOfDaysInAYear(year));
    }
  }

  public static int numberOfDaysInAYear(int year) {
    if (isLeapYear(year)) {
      return 366;
    }
    else {
      return 365;
    }
  }

  /** Determine if it is a leap year */
  static boolean isLeapYear(int year) {
    return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
  }
}
