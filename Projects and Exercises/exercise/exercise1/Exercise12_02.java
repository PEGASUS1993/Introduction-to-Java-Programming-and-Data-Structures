import java.util.Scanner;

public class Exercise12_02 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    boolean done = false;
    int number1 = 0;
    int number2 = 0; 

    // Enter two integers
    System.out.print("Enter two integers: ");
        
    while (!done) {
      try {
        number1 = input.nextInt();
        number2 = input.nextInt();
        done = true;
      }
      catch (Exception ex) {
        ex.printStackTrace();
        System.out.print("Incorrect input and re-enter two integers: ");
        input.nextLine(); // Discard input
      }
    }
   
    System.out.println("Sum is " + (number1 + number2));
  }
}
