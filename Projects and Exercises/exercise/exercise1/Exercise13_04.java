import java.util.*;

public class Exercise13_04 {
  static MyCalendar calendar = new MyCalendar();

  public static void main(String[] args) {
    int month = calendar.get(MyCalendar.MONTH) + 1;
    int year = calendar.get(MyCalendar.YEAR);

    if (args.length > 2)
      System.out.println("Usage java Exercise13_04 month year");
    else if (args.length == 2) {
      //use user-defined month and year
      year = Integer.parseInt(args[1].trim());
      month = Integer.parseInt(args[0].trim());

      calendar.set(Calendar.YEAR, year);
      calendar.set(Calendar.MONTH, month - 1);
    }
    else if (args.length == 1) {
      //use user-defined month for the current year
      month = Integer.parseInt(args[0]);

      calendar.set(Calendar.MONTH, month-1);
    }

    //set date to the first day in a month
    calendar.set(Calendar.DATE, 1);

    //print calendar for the month
    printMonth(year, month);
  }

  static void printMonth(int year, int month) {
    //get start day of the week for the first date in the month
    int startDay = getStartDay();

    //get number of days in the month
    int numOfDaysInMonth = calendar.daysInMonth();

    //print headings
    printMonthTitle(year, month);

    //print body
    printMonthBody(startDay, numOfDaysInMonth);
  }

  static int getStartDay() {
    return calendar.get(Calendar.DAY_OF_WEEK);
  }

  static void printMonthBody(int startDay, int numOfDaysInMonth) {
    //print padding space before the first day of the month
    int i = 0;

    for (i = 0; i < startDay-1; i++)
      System.out.print("    ");

    for (i = 1; i <= numOfDaysInMonth; i++) {
      if (i < 10)
        System.out.print("   "+i);
      else
        System.out.print("  "+i);

      if ((i + startDay - 1) % 7 == 0)
        System.out.println();
    }

    System.out.println("");
  }

  static void printMonthTitle(int year, int month) {
    System.out.println("         "+calendar.getMonthName()+", "+year);
    System.out.println("-----------------------------");
    System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
  }
}
