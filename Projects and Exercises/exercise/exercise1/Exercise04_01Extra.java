import java.util.Scanner;

public class Exercise04_01Extra {
  public static void main(String[] args) {   
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the mass of the cart: ");
    double m = input.nextDouble();
    System.out.print("Enter the force to push the cart: ");
    double F = input.nextDouble();
     
    final double g = 9.8;
    double theta = Math.toDegrees(Math.asin(F / (m * g)));
    
    System.out.println("The angle of the ramp is " + theta + " degrees");
  }
}