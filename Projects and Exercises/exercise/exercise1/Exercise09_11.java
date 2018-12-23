import java.util.Scanner;

public class Exercise09_11 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a, b, c, d, e, f: ");
    double a = input.nextDouble();
    double b = input.nextDouble();
    double c = input.nextDouble();
    double d = input.nextDouble();
    double e = input.nextDouble();
    double f = input.nextDouble();

    LinearEquation equation = new LinearEquation(a, b, c, d, e, f);

    if (equation.isSolvable()) {
      System.out.println("x is " +
        equation.getX() + " and y is " + equation.getY());
    }
    else {
      System.out.println("The equation has no solution");
    }
  }
}

class LinearEquation {
  private double a;
  private double b;
  private double c;
  private double d;
  private double e;
  private double f;

  public LinearEquation(double newA, double newB, double newC,
      double newD, double newE, double newF) {
    a = newA;
    b = newB;
    c = newC; 
    d = newD;
    e = newE;
    f = newF; 
  }
  
  double getA() {
    return a;
  }

  double getB() {
    return b;
  }

  double getC() {
    return c;
  }

  double getD() {
    return d;
  }

  double getE() {
    return e;
  }

  double getF() {
    return f;
  }

  boolean isSolvable() {
    return a * d - b * c != 0;
  }

  double getX() {
    double x = (e * d - b * f) / (a * d - b * c);
    return x; 
  }

  double getY() {
    double y = (a * f - e * c) / (a * d - b * c);
    return y;
  }
}
