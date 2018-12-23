public class Exercise27_14 {

}

class Point {
  private double x;
  private double y;
  
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public double getX() {
    return x;
  }
  
  public double getY() {
    return y;
  }
  
  public boolean equals(Object o) {
    return x == ((Point)o).x && y == ((Point)o).y;
  }
  
  public int hashCode() {
    return (int)(Double.doubleToLongBits(x) ^ Double.doubleToLongBits(y));
  }
}
