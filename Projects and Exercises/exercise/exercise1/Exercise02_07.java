import java.util.Scanner;

public class Exercise02_07 {
  public static void main(String[] args) {
    // Prompt the user to enter the number of minutes
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of minutes: ");
    long minutes = input.nextLong();
    
    long numberOfDays = minutes / (24 * 60);
    long numberOfYears = numberOfDays / 365;
    long remaingDays = numberOfDays % 365;

    // Display results
    System.out.println(minutes + " minutes is approximately " +
      numberOfYears + " years and " + remaingDays + " days remaining");
  }
}
