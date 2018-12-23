import java.util.Scanner;

public class Exercise04_02Extra {
  public static void main(String[] args) {   
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the mass of the cart: ");
    double m = input.nextDouble();
    System.out.print("Enter the ramp angle: ");
    double theta = input.nextDouble();
     
    final double g = 9.8;
    double F = m * g * Math.sin(Math.toRadians(theta));
    
    System.out.println("The force to push the cart is " + F + " Newtons");
  }
}