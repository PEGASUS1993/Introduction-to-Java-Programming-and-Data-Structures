import java.util.Scanner;

public class Exercise08_03Extra {
  public static void main(String[] args) {
    final int N = 3;
    Scanner input = new Scanner(System.in);

    double[][] A = new double[N][N];
    System.out.print("Enter a11, a12, a13, a21, a22, a23, a31, a32, a33: ");
    A[0][0] = input.nextDouble();
    A[0][1] = input.nextDouble();
    A[0][2] = input.nextDouble();
    A[1][0] = input.nextDouble();
    A[1][1] = input.nextDouble();
    A[1][2] = input.nextDouble();
    A[2][0] = input.nextDouble();
    A[2][1] = input.nextDouble();
    A[2][2] = input.nextDouble();
    
    double[][] inverseA = inverse(A);
    if (inverseA == null)
      System.out.println("No inverse matrix");
    else
      displayMatrix(inverseA);
  }
  
  public static void displayMatrix(double[][] A) {
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) 
        System.out.print(A[i][j] + " ");
      System.out.println();  
    }
  }
  
  public static double deltaA(double[][] A) {
    double result = A[0][0] * A[1][1] * A[2][2] +
      A[2][0] * A[0][1] * A[1][2] + A[0][2] * A[1][0] * A[2][1] -
      A[0][2] * A[1][1] * A[2][0] - A[0][0] * A[1][2] * A[2][1] -
      A[2][2] * A[1][0] * A[0][1];
    return result;
  }

  public static double[][] inverse(double[][] A) {
    double[][] result = new double[A.length][A.length];
    
    double determinant = deltaA(A);
    result[0][0] = (A[1][1] * A[2][2] - A[1][2] * A[2][1]) / determinant;
    result[0][1] = (A[0][2] * A[2][1] - A[0][1] * A[2][2]) / determinant;
    result[0][2] = (A[0][1] * A[1][2] - A[0][2] * A[1][1]) / determinant;
    result[1][0] = (A[1][2] * A[2][0] - A[1][0] * A[2][2]) / determinant;
    result[1][1] = (A[0][0] * A[2][2] - A[0][2] * A[2][0]) / determinant;
    result[1][2] = (A[0][2] * A[1][0] - A[0][0] * A[1][2]) / determinant;
    result[2][0] = (A[1][0] * A[2][1] - A[1][1] * A[2][0]) / determinant;
    result[2][1] = (A[0][1] * A[2][0] - A[0][0] * A[2][1]) / determinant;
    result[2][2] = (A[0][0] * A[1][1] - A[0][1] * A[1][0]) / determinant;
    
    return result;
  }
}
