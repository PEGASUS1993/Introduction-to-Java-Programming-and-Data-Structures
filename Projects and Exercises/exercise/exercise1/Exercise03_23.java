import java.util.Scanner;

public class Exercise03_23 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Enter a point with two double values
    System.out.print("Enter a point with two coordinates: ");
    double x = input.nextDouble();
    double y = input.nextDouble();

    // Compute the horizontal distance to the center of the rectangle
    double hDistance = Math.pow(x * x, 0.5); // The absolute value of x, Math.abs(x)
    
    // Compute the vertical distance to the center of the rectangle
    double vDistance = Math.pow(y * y, 0.5); // The absolute value of y

    if (hDistance <= 5 && vDistance <= 2.5)
      System.out.println("Point (" + x + ", " + y + 
        ") is in the rectangle");
    else
      System.out.println("Point (" + x + ", " + y + 
        ") is not in the rectangle");
  }
}
