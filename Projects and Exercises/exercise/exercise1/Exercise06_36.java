import java.util.Scanner;

public class Exercise06_36 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Enter the number of sides
    System.out.print("Enter the number of sides: ");
    int numberOfSides = input.nextInt();

    System.out.print("Enter the side: ");
    double side = input.nextDouble();
    
    System.out.println("The area of the polygon is " + 
      area(numberOfSides, side));
  }
  
  public static double area(int n, double side) {
    return n * side * side / Math.tan(Math.PI / n) / 4;
  }
}
