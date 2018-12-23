public class Exercise10_04 {
  public static void main(String[] args) {
    MyPoint p1 = new MyPoint();
    MyPoint p2 = new MyPoint(10, 30.5);
    System.out.println(p1.distance(p2));
    System.out.println(MyPoint.distance(p1, p2));
  }
}

class MyPoint {
  private double x;
  private double y;

  public MyPoint() {
  }

  public MyPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(MyPoint secondPoint) {
    return distance(this, secondPoint);
  }

  public static double distance(MyPoint p1, MyPoint p2) {
    return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y)
        * (p1.y - p2.y));
  }

  public static double distance(MyPoint p1, MyPoint p2) {
    return p2.distance(p1);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }
}
