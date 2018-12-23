public class Exercise03_11 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    // Prompt the user to enter input
    System.out.print(
      "Enter a month in the year (e.g., 1 for Jan): ");
    int month = input.nextInt();

    System.out.print("Enter a year: ");
    int year = input.nextInt();

    int numberOfDaysInMonth = 0;

    switch (month) {
    case 1:
      System.out.print("January " + year);
      numberOfDaysInMonth = 31;
      break;
    case 2:
      System.out.print("February " + year);
      if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
        numberOfDaysInMonth = 29;
      }
      else {
        numberOfDaysInMonth = 28;
      }
      break;
    case 3:
      System.out.print("March " + year);
      numberOfDaysInMonth = 31;
      break;
    case 4:
      System.out.print("April " + year);
      numberOfDaysInMonth = 30;
      break;
    case 5:
      System.out.print("May " + year);
      numberOfDaysInMonth = 31;
      break;
    case 6:
      System.out.print("June " + year);
      numberOfDaysInMonth = 30;
      break;
    case 7:
      System.out.print("July " + year);
      numberOfDaysInMonth = 31;
      break;
    case 8:
      System.out.print("August " + year);
      numberOfDaysInMonth = 31;
      break;
    case 9:
      System.out.print("September " + year);
      numberOfDaysInMonth = 30;
      break;
    case 10:
      System.out.print("October " + year);
      numberOfDaysInMonth = 31;
      break;
    case 11:
      System.out.print("November " + year);
      numberOfDaysInMonth = 30;
      break;
    case 12:
      System.out.print("December " + year);
      numberOfDaysInMonth = 31;
      break;
    }

    System.out.print(" has " + numberOfDaysInMonth + " days");
  }
}
