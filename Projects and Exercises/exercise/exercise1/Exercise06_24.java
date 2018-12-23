public class Exercise06_24 {
  public static void main(String[] args) {
    // Obtain the total milliseconds since the midnight, Jan 1, 1970
    long totalMilliseconds = System.currentTimeMillis();

    // Obtain the total seconds since the midnight, Jan 1, 1970
    long totalSeconds = (totalMilliseconds / 1000);

    // Compute the current second in the minute in the hour
    int currentSecond = (int)(totalSeconds % 60);

    // Obtain the total minutes
    long totalMinutes = totalSeconds / 60;

    // Compute the current minute in the hour
    int currentMinute = (int)(totalMinutes % 60);

    // Obtain the total hours
    long totalHours = totalMinutes / 60;

    // Compute the current hour
    int currentHour = (int)(totalHours % 24);

    // Compute the total days
    int totalDays = (int)(totalHours / 24);
    if (currentHour > 0) totalDays++;

    // Obtain Year
    int currentYear = 2000;
    do {
      currentYear++;
    } while (getTotalDaysInYears(currentYear) < totalDays);

    // Obtain Month
    int totalNumOfDaysInTheYear = totalDays -
      getTotalDaysInYears(currentYear - 1);
    int currentMonth = 0;
    do {
      currentMonth++;
    } while (getTotalDaysInMonths(currentYear, currentMonth)
      < totalNumOfDaysInTheYear);

    // Obtain Day
    int currentDay = totalNumOfDaysInTheYear -
      getTotalDaysInMonths(currentYear, currentMonth - 1);

    // Display results
    String output = "Current date and time is " +
      currentMonth + "/" + currentDay + "/" + currentYear + " " +
      currentHour + ":" + currentMinute + ":" + currentSecond + " GMT";

    System.out.println(output);
  }

  /** Get the total number of days from Jan 1, 1970 to the specified year*/
  static int getTotalDaysInYears(int year) {
    int total = 0;

    // Get the total days from 1970 to the specified year
    for (int i = 1970; i <= year; i++)
    if (isLeapYear(i))
      total = total + 366;
    else
      total = total + 365;

    return total;
  }

  /** Get the total number of days from Jan 1 to the month in the year*/
  static int getTotalDaysInMonths(int year, int month) {
    int total = 0;

    // Add days from Jan to the month
    for (int i = 1; i <= month; i++)
      total = total + getNumOfDaysInMonth(year, i);

    return total;
  }

  /** Get the number of days in a month */
  static int getNumOfDaysInMonth(int year, int month) {
    if (month == 1 || month==3 || month == 5 || month == 7 ||
      month == 8 || month == 10 || month == 12)
      return 31;

    if (month == 4 || month == 6 || month == 9 || month == 11)
      return 30;

    if (month == 2)
      if (isLeapYear(year))
        return 29;
      else
        return 28;

    return 0; // If month is incorrect.
  }

  /** Determine if it is a leap year */
  static boolean isLeapYear(int year) {
    if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
      return true;

    return false;
  }
}
