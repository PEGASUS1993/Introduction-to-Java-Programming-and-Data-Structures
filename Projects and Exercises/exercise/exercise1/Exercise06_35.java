import java.util.Scanner;

public class Exercise06_35 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Enter the side of the pentagon
    System.out.print("Enter the side: ");
    double side = input.nextDouble();
    
    System.out.println("The area of the pentagon is " + 
        getArea(side));
  }
  
  public static double getArea(double side) {
    // Compute the area
    double area = 5 * side * side / Math.tan(Math.PI / 5) / 4;
    
    return area;
  }
}
