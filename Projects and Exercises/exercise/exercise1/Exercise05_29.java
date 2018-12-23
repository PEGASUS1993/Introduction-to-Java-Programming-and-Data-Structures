import java.util.Scanner;

public class Exercise05_29 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    // Prompt the user to enter input
    System.out.print("Enter a year: ");
    int year = input.nextInt();

    System.out.print("Enter the first day of the year: ");
    int firstDay = input.nextInt();

    int startDay = firstDay;
    int numberOfDaysInMonth = 0;

    // Display calendar for each month
    for (int month = 1; month <= 12; month++) {
      // Display Calendar title
      System.out.print("          ");
      switch (month) {
      case 1:
        System.out.println("January " + year);
        numberOfDaysInMonth = 31;
        break;
      case 2:
        System.out.println("Feburary " + year);
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
          numberOfDaysInMonth = 29;
        else
          numberOfDaysInMonth = 28;
        break;
      case 3:
        System.out.println("March " + year);
        numberOfDaysInMonth = 31;
        break;
      case 4:
        System.out.println("April " + year);
        numberOfDaysInMonth = 30;
        break;
      case 5:
        System.out.println("May " + year);
        numberOfDaysInMonth = 31;
        break;
      case 6:
        System.out.println("June " + year);
        numberOfDaysInMonth = 30;
        break;
      case 7:
        System.out.println("July " + year);
        numberOfDaysInMonth = 31;
        break;
      case 8:
        System.out.println("August " + year);
        numberOfDaysInMonth = 31;
        break;
      case 9:
        System.out.println("September " + year);
        numberOfDaysInMonth = 30;
        break;
      case 10:
        System.out.println("October " + year);
        numberOfDaysInMonth = 31;
        break;
      case 11:
        System.out.println("November " + year);
        numberOfDaysInMonth = 30;
        break;
      case 12:
        System.out.println("December " + year);
        numberOfDaysInMonth = 31;
        break;
      }

      System.out.println("-----------------------------");
      System.out.println(" Sun Mon Tue Wed Thu Fri Sat");

      // Pad space before the first day of the month
      int i = 0;
      for (i = 0; i < startDay; i++)
        System.out.print("    ");

      for (i = 1; i <= numberOfDaysInMonth; i++) {
        if (i < 10)
          System.out.print("   " + i);
        else
          System.out.print("  " + i);

        if ((i + startDay) % 7 == 0)
          System.out.println();
      }

      System.out.println();
      System.out.println();

      // Get the start day for the next month
      startDay = (startDay + numberOfDaysInMonth) % 7;
    }
  }

}
