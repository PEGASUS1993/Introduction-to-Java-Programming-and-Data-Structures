public class Exercise06_01Extra {
  public static void main(String[] args) {
    long seconds = System.currentTimeMillis() / 1000;
    long minutes = seconds / 60;
    long hours = minutes / 60;
    long days = hours / 24;

    days += 1; // Starting day in Jan 1, 1970 is 1, not 0

    // Get year
    int year = 1970;
    while (days >= (isLeapYear(year) ? 366 : 365)) {
      days = days - (isLeapYear(year) ? 366 : 365);
      year++;
    }

    // get month
    int month = 1;
    while (days >= getNumberOfDaysInMonth(year, month)) {
      days = days - getNumberOfDaysInMonth(year, month);
      month++;
    }

    System.out.println("Current date is " + getMonthName(month) + " " 
      + days + ", " + year);
  }
  
  //Determine if it is a leap year
  public static boolean isLeapYear(int year) {
    return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
  }

  // Get the English name for the month
  public static String getMonthName(int month) {
    switch (month) {
      case 1:
        return "January";
      case 2:
        return "February";
      case 3:
        return "March";
      case 4:
        return "April";
      case 5:
        return "May";
      case 6:
        return "June";
      case 7:
        return "July";
      case 8:
        return "August";
      case 9:
        return "September";
      case 10:
        return "October";
      case 11:
        return "November";
      case 12:
        return "December";
      default:
        return "Error";
    }
  }

  // Get the number of days in a month
  public static int getNumberOfDaysInMonth(int year, int month) {
    if (month == 1 || month == 3 || month == 5 || month == 7 ||
        month == 8 || month == 10 || month == 12)
      return 31;

    if (month == 4 || month == 6 || month == 9 || month == 11)
      return 30;

    if (month == 2) return isLeapYear(year) ? 29 : 28;

    return 0; // If month is incorrect
  }
}
