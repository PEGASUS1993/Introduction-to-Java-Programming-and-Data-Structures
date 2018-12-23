import java.util.Scanner;

public class Exercise13_04Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the first complex number: ");
    Complex1 c1 = parseComplexNumber(input.nextLine());
    System.out.print("Enter the second complex number: ");
    Complex1 c2 = parseComplexNumber(input.nextLine());
    System.out.println(c1 + " + " + c2 + " = " + c1.add(c2));
  }
  
  private static String removeBlank(String s) {
    String result = "";
    for (int i = 0; i < s.length(); i++) 
      if (s.charAt(i) != ' ') result += s.charAt(i);
    return result;
  }
  
  // For simplicity, we define this method in the test program
  public static Complex1 parseComplexNumber(String s) {
    if (!s.contains("i")) {
      return new Complex1(Double.parseDouble(s));
    }

    s = removeBlank(s);
    int k = Math.max(s.indexOf("+", 1), s.indexOf("-", 1));
    if (k >= 0) {
      String s1 = s.substring(0, k);
      String s2 = s.substring(k, s.indexOf('i'));
      if (s2.length() <= 1)
        s2 += '1';
      return new Complex1(Double.parseDouble(s1),
          Double.parseDouble(s2));
    } 
    else {
      String s2 = s.substring(0, s.indexOf('i'));
      if (s2.length() <= 1)
        s2 += '1';
      return new Complex1(0, Double.parseDouble(s2));
    }
  }
}

class Complex1 implements Cloneable {
  private double a;
  private double b;

  public Complex1() {
  }

  Complex1(double a, double b) {
    this.a = a;
    this.b = b;
  }

  public Complex1(double a) {
    this(a, 0);
  }

  public double getRealPart() {
    return a;
  }

  public double getImaginaryPart() {
    return b;
  }

  public Complex1 add(Complex1 secondComplex) {
    double newA = a + secondComplex.a;
    double newB = b + secondComplex.b;
    return new Complex1(newA, newB);
  }

  public Complex1 subtract(Complex1 secondComplex) {
    double newA = a - secondComplex.a;
    double newB = b - secondComplex.b;
    return new Complex1(newA, newB);
  }

  public Complex1 multiply(Complex1 secondComplex) {
    double newA = a * secondComplex.a - b * secondComplex.b;
    double newB = b * secondComplex.a + a * secondComplex.b;
    return new Complex1(newA, newB);
  }

  public Complex1 divide(Complex1 secondComplex) {
    double newA = (a * secondComplex.a + b * secondComplex.b)
        / (Math.pow(secondComplex.a, 2.0) + Math.pow(secondComplex.b,
            2.0));
    double newB = (b * secondComplex.a - a * secondComplex.b)
        / (Math.pow(secondComplex.a, 2.0) + Math.pow(secondComplex.b,
            2.0));
    return new Complex1(newA, newB);
  }

  public double abs() {
    return Math.sqrt(a * a + b * b);
  }

  @Override
  public String toString() {
    if (b == 0)
      return a + "";
    else {
      if (a == 0) {
        if (b == 1)
          return "i";
        else if (b == -1)
          return "-i";
        else
          return b + "i";
      }
      else {
        if (b == 1)
          return a + " + i";
        else if (b == -1)
          return a + " - i";
        else if (b < 0)
          return a + " - " + Math.abs(b) + "i";
        else
          return a + " + " + b + "i";
      }
    }
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
}
