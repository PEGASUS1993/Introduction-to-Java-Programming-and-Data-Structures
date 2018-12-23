import java.util.Scanner;

public class Exercise08_02 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter a 4 by 4 matrix row by row: ");
    double[][] m = new double[4][4];
    
    for (int i = 0; i < 4; i++)
      for (int j = 0; j < 4; j++)
        m[i][j] = input.nextDouble();

    System.out.print("Sum of the elements in the major diagonal is " 
        + sumMajorDiagonal(m));
  }
  
  public static double sumMajorDiagonal(double[][] m) {
    double sum = 0;

    for (int i = 0; i < m.length; i++)
        sum += m[i][i];

    return sum;
  }  
}
