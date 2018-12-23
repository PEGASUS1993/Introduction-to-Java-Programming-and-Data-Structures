import java.util.Scanner;

public class Exercise02_06Extra {
  // Main method
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter an integer: ");
    int number = input.nextInt();
    
    int d1 = number % 10;
    number = number / 10;

    int d2 = number % 10;
    number = number / 10;

    int d3 = number % 10;
    number = number / 10;

    int d4 = number % 10;
    number = number / 10;
 
    System.out.println(d1 + "" + d2 + "" + d3 + "" + d4);
  }
}
