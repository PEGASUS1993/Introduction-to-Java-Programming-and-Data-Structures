public class Exercise03_05 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Prompt the user to enter an integer for today
    System.out.print("Enter today’s day: ");
    int today = input.nextInt();
  
    System.out.print("Enter the number of days elapsed since today: ");
    int elapsedDays = input.nextInt();
    
    String nameForToday;
    if (today == 0)
      nameForToday = "Sunday";
    else if (today == 1)
      nameForToday = "Monday";
    else if (today == 2)
      nameForToday = "Tuesday";
    else if (today == 3)
      nameForToday = "Wednesday";
    else if (today == 4)
      nameForToday = "Thursday";
    else if (today == 5)
      nameForToday = "Friday";
    else // if (today == 6)
      nameForToday = "Saturday";

    int futureDay = (today + elapsedDays) % 7;
    String nameForFutureDay;
    if (futureDay == 0)
      nameForFutureDay = "Sunday";
    else if (futureDay == 1)
      nameForFutureDay = "Monday";
    else if (futureDay == 2)
      nameForFutureDay = "Tuesday";
    else if (futureDay == 3)
      nameForFutureDay = "Wednesday";
    else if (futureDay == 4)
      nameForFutureDay = "Thursday";
    else if (futureDay == 5)
      nameForFutureDay = "Friday";
    else // if (futureDay == 6)
      nameForFutureDay = "Saturday";
    
    System.out.println("Today is " + nameForToday 
        + " and the future day is " + nameForFutureDay);
  }
}
