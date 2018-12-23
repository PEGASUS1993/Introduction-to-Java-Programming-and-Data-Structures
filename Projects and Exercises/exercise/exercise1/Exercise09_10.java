import java.util.Scanner;

public class Exercise09_10 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a, b, c: ");
    double a = input.nextDouble();
    double b = input.nextDouble();
    double c = input.nextDouble();

    QuadraticEquation equation = new QuadraticEquation(a, b, c);
    double discriminant = equation.getDiscriminant();

    if (discriminant < 0) {
      System.out.println("The equation has no roots");
    }
    else if (discriminant == 0)
    {
      System.out.println("The root is " + equation.getRoot1());
    }
    else // (discriminant >= 0)
    {
      System.out.println("The roots are " + equation.getRoot1() 
        + " and " + equation.getRoot2());
    }  
  }
}

class QuadraticEquation {
  private double a;
  private double b;
  private double c;

  public QuadraticEquation(double newA, double newB, double newC) {
    a = newA;
    b = newB;
    c = newC; 
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

  double getDiscriminant() {
    return b * b - 4 * a * c;
  }

  double getRoot1() {
    if (getDiscriminant() < 0)
      return 0;
    else {
      return (-b + getDiscriminant()) / (2 * a);
    }
  }

  double getRoot2() {
    if (getDiscriminant() < 0)
      return 0;
    else {
      return (-b - getDiscriminant()) / (2 * a);
    }
  }
}
