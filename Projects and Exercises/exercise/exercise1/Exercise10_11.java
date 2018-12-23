public class Exercise10_11 {
  public static void main(String[] args) {
    Circle2D c1 = new Circle2D(2, 2, 5.5);
    System.out.println("Area is " + c1.getArea());
    System.out.println("Perimeter is " + c1.getPerimeter());
    System.out.println("c1 contains point (3, 3)? " 
      + c1.contains(3, 3));
    System.out.println("c1 contains circle Circle2D(4, 5, 10.5)? " 
      + c1.contains(new Circle2D(4, 5, 10.5)));
    System.out.println("c1 overlaps circle Circle2D(3, 5, 2.3)? " 
      + c1.overlaps(new Circle2D(3, 5, 2.3)));
  }
}

class Circle2D {
  private double x;
  private double y;
  private double radius;
  
  public Circle2D() {
    x = 0;
    y = 0;
    radius = 1;
  }
  
  public Circle2D(double x, double y, double radius) {
    this.x = x;
    this.y = y;
    this.radius = radius;
  }
  
  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }
  
  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }
  
  public double getPerimeter() {
    return 2 * radius * Math.PI;
  }
  
  public double getArea() {
    return radius * radius * Math.PI;
  }
  
  public boolean contains(double x, double y) {
    // MyPoint is defined in Exercise09_04
    double d = distance(x, y, this.x, this.y) ;
    return d <= radius;
  }
  
  public boolean contains(Circle2D circle) {   
    double d = distance(this.x, this.y, circle.x, circle.y);
    return d + circle.radius <= this.radius;
  }
  
  public boolean overlaps(Circle2D circle) {    
    // Two circles overlap if the distance between the two centers 
    // are less than or equal to this.radius + circle.radius
    // MyPoint is defined in Exercise09_04
    return distance(this.x, this.y, circle.x, circle.y) 
      <= radius + circle.radius;
  }
  
  private static double distance(double x1, double y1, 
      double x2, double y2) {
    return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }
}
