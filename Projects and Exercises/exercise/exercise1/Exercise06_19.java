public class Exercise06_19 {
  /** Main method */
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter three sids in double: ");
    double edge1 = input.nextDouble();
    double edge2 = input.nextDouble();
    double edge3 = input.nextDouble();

    if (MyTriangle.isValid(edge1, edge2, edge3)) {
      System.out.println("The are of the triangle is " +
        MyTriangle.area(edge1, edge2, edge3));
    }
    else
      System.out.println("Input is invalid");
  }
}

class MyTriangle {
  public static boolean isValid(double side1, double side2, double side3) {
    return (side1 + side2 > side3) &&
      (side1 + side3 > side2) && (side2 + side3 > side1);
  }

  public static double area(double side1, double side2, double side3) {
    double s = (side1 + side2 + side3) / 2;
    return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
  }
}
