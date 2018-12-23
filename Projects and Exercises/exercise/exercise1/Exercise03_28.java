import java.util.Scanner;

public class Exercise03_28 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter r1’s center x-, y-coordinates, width, and height: ");
    double x1 = input.nextDouble();
    double y1 = input.nextDouble();
    double w1 = input.nextDouble();
    double h1 = input.nextDouble();

    System.out.print("Enter r2’s center x-, y-coordinates, width, and height: ");
    double x2 = input.nextDouble();
    double y2 = input.nextDouble();
    double w2 = input.nextDouble();
    double h2 = input.nextDouble();
    
    double xDistance = x1 >= x2 ? x1 - x2 : x2 - x1;
    double yDistance = y1 >= y2 ? y1 - y2 : y2 - y1;
    
    if (xDistance <= (w1 - w2) / 2 && yDistance <= (h1 - h2) / 2)
      System.out.println("r2 is inside r1");
    else if (xDistance <= (w1 + w2) / 2 && yDistance <= (h1 + h2) / 2)
      System.out.println("r2 overlaps r1");
    else
      System.out.println("r2 does not overlap r1");
  }
}
