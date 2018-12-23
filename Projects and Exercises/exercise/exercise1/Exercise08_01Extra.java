import java.util.Scanner;

public class Exercise08_01Extra {
  public static void main(String[] args) {
    final int N = 2;
    Scanner input = new Scanner(System.in);

    double[][] A = new double[N][N];
    System.out.print("Enter a, b, c, d: ");
    A[0][0] = input.nextDouble();
    A[0][1] = input.nextDouble();
    A[1][0] = input.nextDouble();
    A[1][1] = input.nextDouble();
    
    double[][] inverseA = inverse(A);
    if (inverseA == null)
      System.out.println("No inverse matrix");
    else
      printMatrix(inverse(A));
  }
  
  public static void printMatrix(double[][] A) {
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) 
        System.out.print(A[i][j] + " ");
      System.out.println();  
    }
  }

  public static double[][] inverse(double[][] A) {
    double[][] result = new double[A.length][A.length];
    
    double determinant = A[0][0] * A[1][1] - A[0][1] * A[1][0];
    if (determinant == 0)
      return null;
    
    result[0][0] = A[1][1] / determinant;
    result[0][1] = -A[0][1] / determinant;
    result[1][0] = -A[1][0] / determinant;
    result[1][1] = A[0][0] / determinant;
    
    return result;
  }
}
