public class Exercise13_05Extra {
  public static void main(String[] args) {
    Point1 p1 = new Point1(3, 4);
    Point1 p2 = new Point1(3.4, 1.4);
    System.out.println(p1.equals(p2));
    System.out.println(p1.equals(p1));
    System.out.println(p1.compareTo(p2));
    System.out.println(p2.compareTo(p1));
    
    Point1 p3 = (Point1)(p1.clone());
    System.out.println(p3.equals(p1));
    System.out.println(p3);
  }
}

class Point1 implements Comparable<Point1>, Cloneable {
  private double x;
  private double y;
  
  public Point1(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public double getX() {
    return x;
  }
  
  public double getY() {
    return y;
  }
  
  @Override
  public boolean equals(Object o) {
    return x == ((Point1)o).x && y == ((Point1)o).y;
  }
  
  @Override
  public int compareTo(Point1 p) {
    if (x > p.x) {
      return 1;
    }
    else if (x < p.x) {
      return -1;
    }
    else if (y > p.y) {
      return 1;
    }
    else if (y == p.y) {
      return 0;
    }
    else {
      return -1;
    }
  }
  
  @Override
  public Object clone() {
    try {
      return super.clone();
    }
    catch (CloneNotSupportedException ex) {
      return null;
    }
  }
  
  @Override
  public String toString() {
    return "[" + x + ", " + y + "]";
  }
}
