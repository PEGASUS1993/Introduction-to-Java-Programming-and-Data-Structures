import java.util.Scanner;

public class Exercise08_26 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    final int SIZE = 3;
    System.out.print("Enter a 3 by 3 matrix row by row: ");
    double[][] m = new double[SIZE][SIZE];
    
    for (int i = 0; i < m.length; i++)
      for (int j = 0; j < m[0].length; j++)
        m[i][j] = input.nextDouble();

    double[][] result = sortRows(m);
    
    System.out.println("The row sorted matrix is: ");
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++)
        System.out.print(result[i][j] + " ");
      System.out.println();
    }
  }
  
  public static double[][] sortRows(double[][] m) {
  	double[][] result = new double[m.length][m[0].length];
  	
  	// Copy from m to result
  	for (int i = 0; i < m.length; i++)
      for (int j = 0; j < m[0].length; j++)
        result[i][j] = m[i][j];

  	// Check the sum of each column
  	for (int i = 0; i < result.length; i++) 
  		java.util.Arrays.sort(result[i]);

  	return result;
  } 
}
