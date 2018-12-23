import java.util.Scanner;

public class Exercise13_01 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter three sides: ");
    double side1 = input.nextDouble();
    double side2 = input.nextDouble();
    double side3 = input.nextDouble();
    
    Triangle triangle = new Triangle(side1, side2, side3);
    
    System.out.print("Enter the color: ");
    String color = input.next();
    triangle.setColor(color);
    
    System.out.print("Enter a boolean value for filled: ");
    boolean filled = input.nextBoolean();
    triangle.setFilled(filled);

    System.out.println("The area is " + triangle.getArea());
    System.out.println("The perimeter is "
      + triangle.getPerimeter());
    System.out.println(triangle);
  }
}

class TriangleNew extends GeometricObject {
  private double side1 = 1.0, side2 = 1.0, side3 = 1.0;

  /** Constructor */
  public TriangleNew() {
  }

  /** Constructor */
  public TriangleNew(double side1, double side2, double side3) {
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
  }

  /** Implement the abstract method findArea in GeometricObject */
  public double getArea() {
    double s = (side1 + side2 + side3) / 2;
    return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
  }

  /** Implement the abstract method findCircumference in
   * GeometricObject
   **/
  public double getPerimeter() {
    return side1 + side2 + side3;
  }

  @Override
  public String toString() {
    // Implement it to return the three sides
    return "TriangleNew: side1 = " + side1 + " side2 = " + side2 +
      " side3 = " + side3;
  }
}
