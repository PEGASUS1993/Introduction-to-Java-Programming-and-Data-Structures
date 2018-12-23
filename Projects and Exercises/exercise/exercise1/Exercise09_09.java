public class Exercise09_09 {
  public static void main(String[] args) {
    RegularPolygon polygon1 = new RegularPolygon();
    RegularPolygon polygon2 = new RegularPolygon(6, 4);
    RegularPolygon polygon3 = new RegularPolygon(10, 4, 5.6, 7.8);
    
    System.out.println("Polygon 1 perimeter: " + 
      polygon1.getPerimeter());
    System.out.println("Polygon 1 area: " + polygon1.getArea());
    System.out.println("Polygon 2 perimeter: " + 
        polygon2.getPerimeter());
    System.out.println("Polygon 2 area: " + polygon2.getArea());
    System.out.println("Polygon 3 perimeter: " + 
        polygon3.getPerimeter());
    System.out.println("Polygon 3 area: " + polygon3.getArea());
  }
}

class RegularPolygon {
  private int n = 3;
  private double side = 1;
  private double x;
  private double y;

  public RegularPolygon() {
  }

  public RegularPolygon(int number, double newSide) {
    n = number;
    side = newSide;
  }

  public RegularPolygon(int number, double newSide, double newX, double newY) {
    n = number;
    side = newSide;
    x = newX;
    y = newY;
  }

  public int getN() {
    return n;
  }

  public void setN(int number) {
    n = number;
  }

  public double getSide() {
    return side;
  }

  public void setSide(double newSide) {
    side = newSide;
  }

  public double getX() {
    return x;
  }

  public void setX(double newX) {
    x = newX;
  }
  
  public double getY() {
    return y;
  }

  public void setY(double newY) {
    y = newY;
  }
  
  public double getPerimeter() {
    return n * side;
  }

  public double getArea() {
    return n * side * side / (Math.tan(Math.PI / n) * 4);
  } 
}
