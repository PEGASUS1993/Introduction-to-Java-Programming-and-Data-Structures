import java.util.Scanner;

public class Exercise03_02Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the coordinates for two points: ");
    double x1 = input.nextDouble();
    double y1 = input.nextDouble();
    double x2 = input.nextDouble();
    double y2 = input.nextDouble();

    double m = (y2 - y1) / (x2 - x1);
    double b = y1 - m * x1;
    
    System.out.print("The line equation for two points (" + x1 + ", "
      + y1 + ") and (" + x2 + ", " + y2 + ") is " + "y = ");
  
    if (m == -1)
      System.out.print("-x");
    else if (m == 1)
      System.out.print("x");
    else 
      System.out.print(m + "x");
    
    if (b > 0)
      System.out.println(" + " + b);
    else if (b < 0)
      System.out.println(" - " + (-1 * b));
    else // b is 0
      System.out.println();
  }
}