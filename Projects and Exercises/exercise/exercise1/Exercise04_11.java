import java.util.Scanner;

public class Exercise04_11 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a decimal value (0 to 15): ");
    int decimal = input.nextInt();
    
    if (decimal > 15 || decimal < 0)
      System.out.println(decimal + " is an invalid input");
    else if (decimal < 10)
      System.out.println("The hex value is " + decimal);
    else 
      System.out.println("The hex value is " + (char)('A' + decimal - 10));
  }
}

