import java.util.Scanner;

public class Exercise08_28 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    final int ROW_SIZE = 3;
    final int COLUMN_SIZE = 3;
    System.out.print("Enter m1 (a 3 by 3 matrix) row by row: ");
    int[][] m1 = new int[ROW_SIZE][COLUMN_SIZE];    
    for (int i = 0; i < m1.length; i++)
      for (int j = 0; j < m1[0].length; j++)
        m1[i][j] = input.nextInt();

    System.out.print("Enter m2 (a 3 by 3 matrix) row by row: ");
    int[][] m2 = new int[ROW_SIZE][COLUMN_SIZE];    
    for (int i = 0; i < m2.length; i++)
      for (int j = 0; j < m2[0].length; j++)
        m2[i][j] = input.nextInt();
    

    if (equals(m1, m2))
    	System.out.println("The two arrays are strictly identical");
    else
    	System.out.println("The two arrays are not strictly identical");
  }
  
  public static boolean equals(int[][] m1, int[][] m2) {
  	if (m1.length * m1[0].length != m2.length * m2[0].length)
  		return false;
  	
  	for (int i = 0; i < m1.length; i++)
  		for (int j = 0; j < m1[0].length; j++)
  			if (m1[i][j] != m2[i][j])
  				return false;
  		
    return true;
  } 
}
