import java.util.Scanner;

public class Exercise04_04Extra {
  public static void main(String[] args) {   
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the coefficient of friction: ");
    double u = input.nextDouble();
    System.out.print("Enter the angle in degrees: ");
    double theta = input.nextDouble();
    final double g = 9.8;
    double a = g * (Math.sin(Math.toRadians(theta)) - 
      u * Math.cos(Math.toRadians(theta)));
    
    if (a <= 0)
      System.out.println("The brick does not move or move at a constant speed");
    else 
      System.out.println("The brick accelerates at " + a + 
        " meters per square seconds");
  }
}