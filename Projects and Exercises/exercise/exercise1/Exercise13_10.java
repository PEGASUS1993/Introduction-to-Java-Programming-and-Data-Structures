public class Exercise13_10 {
  public static void main(String[] args) {
    Rectangle13_10 obj1 = new Rectangle13_10();
    Rectangle13_10 obj2 = new Rectangle13_10();
    System.out.println(obj1.equals(obj2));
    System.out.println(obj1.compareTo(obj2));
  }
}

// Rectangle.java: The Rectangle class that extends GeometricObject
class Rectangle13_10 extends GeometricObject implements Comparable<Rectangle13_10> {
  private double width;
  private double height;

  /** Default constructor */
  public Rectangle13_10() {
    this(1.0, 1.0);
  }

  /** Construct a rectangle with width and height */
  public Rectangle13_10(double width, double height) {
    this.width = width;
    this.height = height;
  }

  /** Return width */
  public double getWidth() {
    return width;
  }

  /** Set a new width */
  public void setWidth(double width) {
    this.width = width;
  }

  /** Return height */
  public double getHeight() {
    return height;
  }

  /** Set a new height */
  public void setHeight(double height) {
    this.height = height;
  }

  /** Implement the getArea method in GeometricObject */
  public double getArea() {
    return width*height;
  }

  /** Implement the getPerimeter method in GeometricObject */
  public double getPerimeter() {
    return 2*(width + height);
  }

  @Override
  public String toString() {
    return "[Rectangle] width = " + width +
      " and height = " + height;
  }

  @Override
  public int compareTo(Rectangle13_10 obj) {
    if (this.getArea() > obj.getArea())
      return 1;
    else if (this.getArea() < obj.getArea())
      return -1;
    else
      return 0;
  }

  public boolean equals(Object obj) {
    return this.getArea() == ((Rectangle13_10)obj).getArea();
  }
}
