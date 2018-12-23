public class Exercise08_10 {
  public static void main(String[] args) {
    final int n = 4;
    int[][] matrix = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = (int)(Math.random() * 2);
        System.out.print(matrix[i][j]);
      }

      System.out.println();
    }

    // Check rows
    int rowSum = sumRow(matrix[0]);
    int rowIndex = 0;
    for (int i = 1; i < n; i++) { 
      if (rowSum < sumRow(matrix[i])) {
        rowSum = sumRow(matrix[i]);
        rowIndex = i;
      }
    }

    System.out.println("The largest row index: " + rowIndex);

    // Check columns
    int columnSum = sumColumn(matrix, 0);
    int columnIndex = 0;
    for (int i = 1; i < n; i++) { 
      if (columnSum < sumColumn(matrix, i)) {
        columnSum = sumColumn(matrix, i);
        columnIndex = i;
      }
    }

    System.out.println("The largest column index: " + columnIndex);
  }

  public static int sumRow(int row[]) {
    int sum = 0;
    for (int i = 0; i < row.length; i++)
      sum += row[i];
    return sum;
  }

  public static int sumColumn(int matrix[][], int column) {
    int sum = 0;
    for (int i = 0; i < matrix.length; i++)
      sum += matrix[i][column];
    return sum;
  }
}
