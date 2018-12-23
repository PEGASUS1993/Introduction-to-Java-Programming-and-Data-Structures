import java.util.Scanner;

public class Exercise03_22 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Enter a point with two double values
    System.out.print("Enter a point with two coordinates: ");
    double x = input.nextDouble();
    double y = input.nextDouble();

    // Compute the distance
    double distance = Math.pow(x * x +  y * y, 0.5);
    
    if (distance <= 10)
      System.out.println("Point (" + x + ", " + y + 
        ") is in the circle");
    else
      System.out.println("Point (" + x + ", " + y + 
        ") is not in the circle");
  }
}
