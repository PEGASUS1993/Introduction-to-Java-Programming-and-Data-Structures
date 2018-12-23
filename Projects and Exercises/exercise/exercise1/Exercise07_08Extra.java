import java.util.Scanner;

public class Exercise07_08Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the order of the polynomial n: ");
    int n = input.nextInt();
    
    System.out.print("Enter coefficients: ");
    double[] a = new double[n + 1];
    for (int i = a.length - 1; i >= 0; i--) {
      a[i] = input.nextDouble();
    }
    
    System.out.print("Enter x: ");
    double x = input.nextDouble();
    
    System.out.println("The polynomial expression is " + 
      evaluatePolynomial(a, x));
  }
  
  public static double evaluatePolynomial(double[] a, double x) {
    double result = 0;
    for (int i = a.length - 1; i >= 0; i--) {
      result = result * x + a[i];
    }
    return result;
  }
}

