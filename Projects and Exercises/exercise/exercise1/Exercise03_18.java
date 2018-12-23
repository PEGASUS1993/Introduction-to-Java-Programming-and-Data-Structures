import java.util.Scanner;

public class Exercise03_18 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter package weight: ");
    double w = input.nextDouble();
    
    if (w <= 0) {
      System.out.println("Invalid input");
    }
    else if (w <= 1) {
      System.out.println("The shipping cost is $3.5");
    }
    else if (w <= 3) {
      System.out.println("The shipping cost is $5.5");
    }     
    else if (w <= 10) {
      System.out.println("The shipping cost is $8.5");
    }     
    else if (w <= 20) {
      System.out.println("The shipping cost is $10.5");
    }     
    else {
      System.out.println("The package cannot be shipped");
    }
  }
}
