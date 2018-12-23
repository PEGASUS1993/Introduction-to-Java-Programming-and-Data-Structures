import java.util.Scanner;

public class Exercise02_01Extra {
  // Main method
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the width and height of a rectangle: ");
    double w = input.nextDouble();
    double h = input.nextDouble();
    double perimeter = 2 * (w + h);
    double area = w * h;
    double diagonal = Math.pow(w * w + h * h, 0.5);

    System.out.println("The perimeter is " + perimeter);
    System.out.println("The area is " + area);
    System.out.println("The length of the diagonal is " + diagonal);
  }
}
