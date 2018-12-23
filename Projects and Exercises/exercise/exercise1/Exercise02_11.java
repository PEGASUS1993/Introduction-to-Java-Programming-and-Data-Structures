import java.util.Scanner;

public class Exercise02_11 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of years: ");
    int numberOfYears = input.nextInt();
    
    double population = 312032486 + numberOfYears * 365 * 24 * 60 * 60 / 7.0 - 
      numberOfYears * 365 * 24 * 60 * 60 / 13.0 + numberOfYears * 365 * 24 * 60 * 60 / 45.0;
    
    // Display results
    System.out.println("The population in " + numberOfYears + " years is " + (int)population);
  }
}
