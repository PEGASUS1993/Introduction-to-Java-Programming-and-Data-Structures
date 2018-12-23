import java.util.Scanner;

public class Exercise05_41 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter the first number
    System.out.print("Enter an integer (0 ends the input): ");
    int value = input.nextInt();
    if (value == 0) {
      System.out.println("No numbers are entered except 0");
      System.exit(1);
    } 
    
    int max = value;
    int count = 1;
    
    while (value != 0) {
      // Prompt the user to enter the remaining numbers
      System.out.print("Enter an integer (0 ends the input): ");
      value = input.nextInt();

      if (value > max) {
        max = value;
        count = 1;
      } 
      else if (value == max) {
        count++;
      }
    }

    System.out.println("max is " + max);
    System.out.println("The count for the max number is " + count);
  }
}
