import java.util.Scanner;

public class Exercise08_06 {
  /** Main method */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    final int N = 3;
    
    // Enter matrix1
    System.out.print("Enter matrix1: ");
    double[][] matrix1 = new double[N][N];
    for (int i = 0; i < matrix1.length; i++)
      for (int j = 0; j < matrix1[i].length; j++) {
        matrix1[i][j] = input.nextDouble();
      }
    
    // Enter matrix2
    System.out.print("Enter matrix2: ");
    double[][] matrix2 = new double[N][N];
    for (int i = 0; i < matrix1.length; i++)
      for (int j = 0; j < matrix1[i].length; j++) {
        matrix2[i][j] = input.nextDouble();
      }

    // Multiply two matrices and print the result
    double[][] resultMatrix = multiplyMatrix(matrix1, matrix2);
    System.out.println("\nThe multiplication of the matrices is ");
    printResult(matrix1, matrix2, resultMatrix, '*');
  }

  /** The method for multiplying two matrices */
  public static double[][] multiplyMatrix(
      double[][] m1, double[][] m2) {
    double[][] result = new double[m1.length][m2[0].length];
    for (int i = 0; i < m1.length; i++)
      for (int j = 0; j < result.length; j++)
        for (int k = 0; k < result[0].length; k++)
           result[i][j] += m1[i][k] * m2[k][j];

    return result;
  }

  /** Print result */
  public static void printResult(
      double[][] m1, double[][] m2, double[][] m3, char op) {
    for (int i = 0; i < m1.length; i++) {
      for (int j = 0; j < m1[0].length; j++)
        System.out.print(" " + m1[i][j]);

      if (i == m1.length / 2)
        System.out.print( "  " + op + "  " );
      else
        System.out.print( "     " );

      for (int j = 0; j < m2[0].length; j++)
        System.out.print(" " + m2[i][j]);

      if (i == m1.length / 2)
        System.out.print( "  =  " );
      else
        System.out.print( "     " );

      for (int j = 0; j < m3[0].length; j++)
        System.out.print(" " + m3[i][j]);

      System.out.println();
    }
  }
}
