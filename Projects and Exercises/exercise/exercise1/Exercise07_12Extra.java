public class Exercise07_12Extra {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Prompt the user to enter an integer for today
    System.out.print("Enter today’s day: ");
    int today = input.nextInt();
  
    System.out.print("Enter the number of days elapsed since today: ");
    int elapsedDays = input.nextInt();
    
    String[] dayName = {"Sunday", "Monday", "Tuesday", "Wednesday", 
       "Thursday", "Friday", "Saturday"};

    int futureDay = (today + elapsedDays) % 7;
    System.out.println("Today is " + dayName[today]  
      + " and the future day is " + dayName[futureDay]);
  }
}
