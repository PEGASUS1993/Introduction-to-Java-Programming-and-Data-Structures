import java.util.Scanner;

public class Exercise04_04 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the side: ");
    double side = input.nextDouble();

    // Compute the area
    double area = 6 * side * side / Math.tan(Math.PI / 6) / 4;

    System.out.println("The area of the hexagon is " + area);
  }
}