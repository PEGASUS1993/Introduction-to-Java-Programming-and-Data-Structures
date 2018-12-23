import java.util.Scanner;

public class Exercise13_17 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the first complex number: ");
    double a = input.nextDouble();
    double b = input.nextDouble();
    Complex c1 = new Complex(a, b);

    System.out.print("Enter the second complex number: ");
    double c = input.nextDouble();
    double d = input.nextDouble();
    Complex c2 = new Complex(c, d);

    System.out.println("(" + c1 + ")" + " + " + "(" + c2 + ")" + " = " + c1.add(c2));
    System.out.println("(" + c1 + ")" + " - " + "(" + c2 + ")" + " = " + c1.subtract(c2));
    System.out.println("(" + c1 + ")" + " * " + "(" + c2 + ")" + " = " + c1.multiply(c2));
    System.out.println("(" + c1 + ")" + " / " + "(" + c2 + ")" + " = " + c1.divide(c2));
    System.out.println("|" + c1 + "| = " + c1.abs());
    
    Complex c3 = (Complex)c1.clone();
    System.out.println(c1 == c3);
    System.out.println(c3.getRealPart());
    System.out.println(c3.getImaginaryPart());
    Complex c4 = new Complex(4, -0.5);
    Complex[] list = {c1, c2, c3, c4};
    java.util.Arrays.sort(list);
    System.out.println(java.util.Arrays.toString(list));
  }
}

class Complex implements Cloneable, Comparable<Complex> {
  private double a;
  private double b;

  public Complex() {
  }

  Complex(double a, double b) {
    this.a = a;
    this.b = b;
  }

  public Complex(double a) {
    this(a, 0);
  }

  public double getRealPart() {
    return a;
  }

  public double getImaginaryPart() {
    return b;
  }

  public Complex add(Complex secondComplex) {
    double newA = a + secondComplex.a;
    double newB = b + secondComplex.b;
    return new Complex(newA, newB);
  }

  public Complex subtract(Complex secondComplex) {
    double newA = a - secondComplex.a;
    double newB = b - secondComplex.b;
    return new Complex(newA, newB);
  }

  public Complex multiply(Complex secondComplex) {
    double newA = a * secondComplex.a - b * secondComplex.b;
    double newB = b * secondComplex.a + a * secondComplex.b;
    return new Complex(newA, newB);
  }

  public Complex divide(Complex secondComplex) {
    double newA = (a * secondComplex.a + b * secondComplex.b)
        / (Math.pow(secondComplex.a, 2.0) + Math.pow(secondComplex.b,
            2.0));
    double newB = (b * secondComplex.a - a * secondComplex.b)
        / (Math.pow(secondComplex.a, 2.0) + Math.pow(secondComplex.b,
            2.0));
    return new Complex(newA, newB);
  }

  public double abs() {
    return Math.sqrt(a * a + b * b);
  }

  @Override
  public String toString() {
    if (b != 0)
      return a + " + " + b + "i";
    return a + "";
  }
  
  @Override /** Implement the clone method in
  the Object class */
  public Object clone() {
    // Implement it
    try {
      return super.clone(); // Automatically perform a shallow copy
    }
    catch (CloneNotSupportedException ex) {
      return null;
    }
  }
  
  @Override 
  public int compareTo(Complex c) {
	double v1 = this.abs();
	double v2 = c.abs();
	return v1 < v2 ? -1 : ((v1 == v2) ? 0 : 1);
  }
}
