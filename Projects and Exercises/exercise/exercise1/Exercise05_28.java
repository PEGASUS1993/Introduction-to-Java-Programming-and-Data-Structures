public class Exercise05_28 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Prompt the user to enter input
    System.out.print("Enter a year: ");
    int year = input.nextInt();

    System.out.print("Enter the first day of the year: ");
    int firstDay = input.nextInt();

    int numberOfDaysInMonth = 0;

    // Display calendar for each month
    for (int month = 1; month <= 12; month++) {
      // Display Calendar title
      switch (month) {
        case 1: System.out.print("January 1, " + year + " is ");
                numberOfDaysInMonth = 31;
                break;
        case 2: System.out.print("February 1, " + year + " is ");
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
                  numberOfDaysInMonth = 29;
                else
                  numberOfDaysInMonth = 28;
        break;
        case 3: System.out.print("March 1, " + year + " is ");
        numberOfDaysInMonth = 31;
        break;
        case 4: System.out.print("April 1, " + year + " is ");
        numberOfDaysInMonth = 30;
        break;
        case 5: System.out.print("May 1, " + year + " is ");
        numberOfDaysInMonth = 31;
        break;
        case 6: System.out.print("June 1, " + year + " is ");
        numberOfDaysInMonth = 30;
        break;
        case 7: System.out.print("July 1, " + year + " is ");
        numberOfDaysInMonth = 31;
        break;
        case 8: System.out.print("August 1, " + year + " is ");
        numberOfDaysInMonth = 31;
        break;
        case 9: System.out.print("September 1, " + year + " is ");
        numberOfDaysInMonth = 30;
        break;
        case 10: System.out.print("October 1, " + year + " is ");
        numberOfDaysInMonth = 31;
        break;
        case 11: System.out.print("November 1, " + year + " is ");
        numberOfDaysInMonth = 30;
        break;
        case 12: System.out.print("December 1, " + year + " is ");
        numberOfDaysInMonth = 31;
        break;
      }

      switch (firstDay) {
        case 0: System.out.println("Sunday"); break;
        case 1: System.out.println("Monday"); break;
        case 2: System.out.println("Tuesday"); break;
        case 3: System.out.println("Wednesday"); break;
        case 4: System.out.println("Thursday"); break;
        case 5: System.out.println("Friday"); break;
        case 6: System.out.println("Saturday"); break;
      }

      // Get the start day for the next month
      firstDay = (firstDay + numberOfDaysInMonth) % 7;
    }
  }
}
