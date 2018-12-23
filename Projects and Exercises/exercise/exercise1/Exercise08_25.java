import java.util.Scanner;

public class Exercise08_25 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    final int SIZE = 3;
    System.out.print("Enter a 3 by 3 matrix row by row: ");
    double[][] m = new double[SIZE][SIZE];
    
    for (int i = 0; i < m.length; i++)
      for (int j = 0; j < m[0].length; j++)
        m[i][j] = input.nextDouble();

    if (isMarkovMatrix(m))
    	System.out.print("It is a Markov matrix");
    else
    	System.out.print("It is not a Markov matrix");
  }
  
  public static boolean isMarkovMatrix(double[][] m) {
  	// Check positive
  	for (int i = 0; i < m.length; i++)
      for (int j = 0; j < m[0].length; j++)
        if (m[i][j] < 0) return false;

  	// Check the sum of each column
  	for (int j = 0; j < m[0].length; j++) {
  		double sum = 0;
      for (int i = 0; i < m.length; i++)
      	sum += m[i][j];
      
      if (sum != 1)
      	return false;
  	}

  	return true;
  } 
}
