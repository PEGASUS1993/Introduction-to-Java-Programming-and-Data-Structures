import java.util.Scanner;

public class Exercise04_03Extra {
  public static void main(String[] args) {   
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the coefficient of friction: ");
    double u = input.nextDouble();
    double theta = Math.toDegrees(Math.atan(u));
    
    System.out.println("The minimal angle for the brick to slide is " + theta + " degrees");
  }
}