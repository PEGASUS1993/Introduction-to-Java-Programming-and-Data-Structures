import java.util.Scanner;

public class Exercise08_35 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter the number of rows in the square matrix: ");
    int size = input.nextInt();

    int[][] m = new int[size][size];

    System.out.print("Enter the matrix row by row: ");

    for (int i = 0; i < m.length; i++)
      for (int j = 0; j < m[i].length; j++)
        m[i][j] = input.nextInt();

    int[] result = findLargestBlock(m);

    System.out.println("The maximum square submatrix is at (" + result[0]
        + ", " + result[1] + ") with size " + result[2]);
  }

  public static int[] findLargestBlock(int[][] m) {
    int[][] count = new int[m.length][m.length];

    for (int i = m.length - 1; i >= 0; i--) {
      for (int j = m[i].length - 1; j >= 0; j--) {
        if (m[i][j] == 1) {
          count[i][j] = 1;

          if (i < m.length - 1 && j < m[i].length - 1 && m[i + 1][j + 1] == 1) {
            // Check to expand the block with (i, j) at its upper right corner
            for (int k = 1; k <= count[i + 1][j + 1]; k++) {
              if (m[i][j + k] == 1 && m[i + k][j] == 1) {
                count[i][j] += 1;
              } else {
                break;
              }
            }
          }
        }
      }
    }

    int max = count[0][0];
    int maxOfx = 0;
    int maxOfy = 0;
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[i].length; j++) {
        if (count[i][j] > max) {
          max = count[i][j];
          maxOfx = i;
          maxOfy = j;
        }
      }
    }

    int[] result = new int[3];
    result[0] = maxOfx;
    result[1] = maxOfy;
    result[2] = max;

    return result;
  }
}
