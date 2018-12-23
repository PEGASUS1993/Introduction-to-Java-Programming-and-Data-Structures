public class Exercise13_09 {
  public static void main(String[] args) {
    Circle13_09 obj1 = new Circle13_09();
    Circle13_09 obj2 = new Circle13_09();

    System.out.println(obj1.equals(obj2));
    System.out.println(obj1.compareTo(obj2));
  }
}

// Circle.java: The circle class that extends GeometricObject
class Circle13_09 extends GeometricObject implements Comparable<Circle13_09> {
  private double radius;

  /** Return radius */
  public double getRadius() {
    return radius;
  }

  /** Set a new radius */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  /** Implement the getArea method defined in GeometricObject */
  public double getArea() {
    return radius * radius * Math.PI;
  }

  /** Implement the getPerimeter method defined in GeometricObject*/
  public double getPerimeter() {
    return 2 * radius * Math.PI;
  }

  @Override
  public String toString() {
    return "[Circle] radius = " + radius;
  }

  @Override
  public int compareTo(Circle13_09 obj) {
    if (this.getArea() > obj.getArea())
      return 1;
    else if (this.getArea() < obj.getArea())
      return -1;
    else
      return 0;
  }

  public boolean equals(Object obj) {
    return this.radius == ((Circle13_09)obj).radius;
  }
}
