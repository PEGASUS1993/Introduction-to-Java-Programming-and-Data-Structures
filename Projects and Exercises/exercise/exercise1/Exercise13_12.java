public class Exercise13_12 {
  public static void main(String[] args) {
    new Exercise13_12();
  }

  public Exercise13_12() {
    GeometricObject[] a = {new Circle(5), new Circle(6),
      new Rectangle13_12(2, 3), new Rectangle13_12(2, 3)};

    System.out.println("The total area is " + sumArea(a));
  }

  public static double sumArea(GeometricObject[] a) {
    double sum = 0;

    for (int i = 0; i < a.length; i++)
      sum += a[i].getArea();

    return sum;
  }
}

  // Rectangle.java: The Rectangle class that extends GeometricObject
class Rectangle13_12 extends GeometricObject {
  private double width;
  private double height;

    /** Construct a rectangle with width and height */
  public Rectangle13_12(double width, double height) {
    this.width = width;
    this.height = height;
  }
  
  /**Return width*/
  public double getWidth() {
    return width;
  }

  /**Set a new width*/
  public void setWidth(double width) {
    this.width = width;
  }

  /**Return height*/
  public double getHeight() {
    return height;
  }

  /**Set a new height*/
  public void setHeight(double height) {
    this.height = height;
  }

  /**Implement the getArea method in GeometricObject*/
  public double getArea() {
    return width*height;
  }

  /**Implement the getPerimeter method in GeometricObject*/
  public double getPerimeter() {
    return 2*(width + height);
  }

  /**Override the equals method defined in the Object class*/
  public boolean equals(Rectangle rectangle) {
    return (width == rectangle.getWidth()) &&
      (height == rectangle.getHeight());
  }

  @Override
  public String toString() {
    return "[Rectangle] width = " + width +
      " and height = " + height;
  }
}
