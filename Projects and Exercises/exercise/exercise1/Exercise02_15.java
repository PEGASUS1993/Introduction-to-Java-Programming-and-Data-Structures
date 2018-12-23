import java.util.Scanner;

public class Exercise02_15 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Enter the first point with two double values
    System.out.print("Enter x1 and y1: ");
    double x1 = input.nextDouble();
    double y1 = input.nextDouble();

    // Enter the second point with two double values
    System.out.print("Enter x2 and y2: ");
    double x2 = input.nextDouble();
    double y2 = input.nextDouble();

    // Compute the distance
    double distance = Math.pow((x2 - x1) * (x2 - x1) +
        (y2 - y1) * (y2 - y1), 0.5);
    
    System.out.println("The distance of the two points is " +
        distance);
  }
}
