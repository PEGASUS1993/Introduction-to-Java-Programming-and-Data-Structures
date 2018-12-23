import java.util.Scanner;

public class Exercise08_27 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    final int ROW_SIZE = 3;
    final int COLUMN_SIZE = 3;
    System.out.print("Enter a 3 by 3 matrix row by row: ");
    double[][] m = new double[ROW_SIZE][COLUMN_SIZE];
    
    for (int i = 0; i < m.length; i++)
      for (int j = 0; j < m[0].length; j++)
        m[i][j] = input.nextDouble();

    double[][] result = sortColumns(m);
    
    System.out.println("The column sorted matrix is ");
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[0].length; j++)
        System.out.print(result[i][j] + " ");
      System.out.println();
    }
  }
  
  public static double[][] sortColumns(double[][] m) {
  	double[][] result = new double[m.length][m[0].length];
  	
  	for (int j = 0; j < m[0].length; j++) {
  		double[] temp = new double[m.length];
  		
  		for (int i = 0; i < temp.length; i++)
  			temp[i] = m[i][j];
  		
  		java.util.Arrays.sort(temp);
  		
  		// Send temp to the i's row in result
  		for (int i = 0; i < temp.length; i++)
  			result[i][j] = temp[i];  		
  	}

  	return result;
  } 
}
