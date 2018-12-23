import java.util.Scanner;

public class Exercise08_02Extra {
  final static int N = 3;
  
  public static void main(String[] args) {
    double[][] A = new double[N][N];
    System.out.print("Enter a11, a12, a13, a21, a22, a23, a31, a32, a33: ");

    Scanner input = new Scanner(System.in);
    A[0][0] = input.nextDouble();
    A[0][1] = input.nextDouble();
    A[0][2] = input.nextDouble();
    A[1][0] = input.nextDouble();
    A[1][1] = input.nextDouble();
    A[1][2] = input.nextDouble(); 
    A[2][0] = input.nextDouble();
    A[2][1] = input.nextDouble();
    A[2][2] = input.nextDouble();

    System.out.print("Enter b1, b2, b3: ");
    double[] B = new double[3];
    B[0] = input.nextDouble();
    B[1] = input.nextDouble();
    B[2] = input.nextDouble();

    double[] result = getSolution(A, B);
    
    if (result == null)
      System.out.println("No solution");
    else {
      System.out.println("The solution is " + result[0] + " " + result[1] + " " + result[2]);
    }
  }

  public static double deltaA(double[][] A) {
    double result = A[0][0] * A[1][1] * A[2][2] +
      A[2][0] * A[0][1] * A[1][2] + A[0][2] * A[1][0] * A[2][1] -
      A[0][2] * A[1][1] * A[2][0] - A[0][0] * A[1][2] * A[2][1] -
      A[2][2] * A[1][0] * A[0][1];
    return result;
  }
  
  public static double[] getSolution(double[][] A, double[] B) {
    double delta = deltaA(A);
    if (delta == 0)
      return null;
    else {
      double x = ((A[1][1] * A[2][2] - A[1][2] * A[2][1]) * B[0] +
        (A[0][2] * A[2][1] - A[0][1] * A[2][2]) * B[1] +
        (A[0][1] * A[1][2] - A[0][2] * A[1][1]) * B[2]) / delta;
      double y = ((A[1][2] * A[2][0] - A[1][0] * A[2][2]) * B[0] +
        (A[0][0] * A[2][2] - A[0][2] * A[2][0]) * B[1] +
        (A[0][2] * A[1][0] - A[0][0] * A[1][2]) * B[2]) / delta;
      double z = ((A[1][0] * A[2][1] - A[1][1] * A[2][0]) * B[0] +
        (A[0][1] * A[2][0] - A[0][0] * A[2][1]) * B[1] +
        (A[0][0] * A[1][1] - A[0][1] * A[1][0]) * B[2]) / delta;
      return new double[]{x, y, z};
    }
  }
}
