public class Exercise13_11 {
  public static void main(String[] args) {
    Octagon a1 = new Octagon(5);
    System.out.println("Area is " + a1.getArea());
    System.out.println("Perimeter is " + a1.getPerimeter());

    Octagon a2 = (Octagon)(a1.clone());
    System.out.println("Compare the octagons " + a1.compareTo(a2));
  }
}

class Octagon extends GeometricObject
    implements Comparable<Octagon>, Cloneable {
  private double side;
  
  public double getSide() {
    return side;
  }

  public void setSide(double side) {
    this.side = side;
  }

  /** Construct a Octagon with the default side */
  public Octagon () {
    // Implement it
    this.side = 1;
  }
  
  /** Construct a Octagon with the specified side */
  public Octagon (double side) {
    // Implement it
    this.side = side;
  }

  @Override /** Implement the abstract method getArea in
     GeometricObject */
  public double getArea() {
    // Implement it
    return (2 + 4 / Math.sqrt(2)) * side * side;
  }

  @Override /** Implement the abstract method getPerimeter in
     GeometricObject */
  public double getPerimeter() {
    // Implement it
    return 8 * side;
  }

  @Override
  public int compareTo(Octagon obj) {
    if (this.side > obj.side)
      return 1;
    else if (this.side == obj.side)
      return 0;
    else
      return -1;
  }

  @Override /** Implement the clone method in
     the Object class */
  public Object clone() {
//    Octagon o = new Octagon();
//    o.side = this.side;
//    return o;
//    
    // Implement it
    try {
      return super.clone(); // Automatically perform a shallow copy
    }
    catch (CloneNotSupportedException ex) {
      return null;
    }
  }
}
