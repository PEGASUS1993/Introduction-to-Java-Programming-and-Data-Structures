public class Exercise09_01International {
  public static void main(String[] args) {
    Triangle7_1 triangle = new Triangle7_1(1, 1.5, 1);
    System.out.println(triangle);
    System.out.println("The area is " + triangle.findArea());
    System.out.println("The perimeter is "
      + triangle.findPerimeter());
    System.out.println(triangle);
  }
}

class Triangle7_1 {
  double side1, side2, side3;

  /** Constructor */
  public Triangle7_1(double side1, double side2, double side3) {
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
  }

  /** Implement the abstract method findArea in GeometricObject */
  public double findArea() {
    double s = (side1 + side2 + side3) / 2;
    return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
  }

  /** Implement the abstract method findCircumference in
   * GeometricObject
   **/
  public double findPerimeter() {
    return side1 + side2 + side3;
  }

  @Override /** Override the toString method */
  public String toString() {
    // Implement it to return the three sides
    return "Triangle: side1 = " + side1 + " side2 = " + side2 +
      " side3 = " + side3;
  }
}
