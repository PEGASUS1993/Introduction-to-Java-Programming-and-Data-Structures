public class Exercise12_05 {
  public static void main(String[] args) {
    try {
      TriangleWithException t1 = new TriangleWithException(1.5, 2, 3);
      System.out.println("Perimeter for t1: " + t1.getPerimeter());
      System.out.println("Area for t1: " + t1.getArea());
      
      TriangleWithException t2 = new TriangleWithException(1, 2, 3);
      System.out.println("Perimeter for t2: " + t2.getPerimeter());
      System.out.println("Area for t2: " + t2.getArea());
    }
    catch (IllegalTriangleException ex) {
      System.out.println("Illegal triangle");
      System.out.println("Side1: " + ex.getSide1());
      System.out.println("Side2: " + ex.getSide2());
      System.out.println("Side3: " + ex.getSide3());
    }
  }
}

class IllegalTriangleException extends Exception {
  private double side1, side2, side3;
  
  public IllegalTriangleException(double side1, 
      double side2, double side3, String s) {
    super(s);
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
  }
  
  public double getSide1() {
    return side1;
  }
  
  public double getSide2() {
    return side2;
  }
  
  public double getSide3() {
    return side3;
  }
}

// Name the new Triangle class as TrianlgeWithException to avoid naming conflict
// with the Triangle class already defined in Programming Exercise 11.1
class TriangleWithException extends Object {
  double side1, side2, side3;

  /** Constructor */
  public TriangleWithException(double side1, double side2, double side3)
    throws IllegalTriangleException {
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
    if (side1 + side2 <= side3 || side1 + side3 <= side2 || side2 + side3 <= side1)
      throw new IllegalTriangleException(side1, side2, side3,
        "The sum of any two sides is greater than the other side");
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
    return "Triangle: side1 = " + side1 + " side2 = " + side2 +
      " side3 = " + side3;
  }
}
