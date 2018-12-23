import java.util.Scanner;

public class Exercise18_04Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the number of rows in the square matrix: ");
    int size = input.nextInt();

    int[][] m = new int[size][size];

    System.out.print("Enter the matrix row by row: ");

    for (int i = 0; i < m.length; i++)
      for (int j = 0; j < m[i].length; j++)
        m[i][j] = input.nextInt();

    int maxSize = 0;
    for (int i = 0; i < m.length; i++)
      for (int j = 0; j < m[i].length; j++) {
        int temp = getSize(i, j, m);
        if (maxSize < temp) 
          maxSize = temp;
      }
    
    System.out.println("The size of a maximum square submatrix is " 
      + maxSize);
  }

  public static int getSize(int i, int j, int[][] m)  {
    int n = m.length;
    if (i == n - 1 || j == n - 1) {
      return m[i][j];
    }
    
    if (m[i][j] == 1)
      return 1 + Math.min(getSize(i, j + 1, m), 
        Math.min(getSize(i + 1, j + 1, m), getSize(i + 1, j, m)));
    else 
      return 0;
  }
}