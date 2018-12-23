
public class Exercise08_22 {
  public static void main(String[] args) {
    int[][] matrix = new int[6][6];
    
    // Initialize matrix and display it
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        matrix[i][j] = (int)(Math.random() * 2);
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
    
    if (isEvenParity(matrix))
      System.out.println("All rows and columns are even");
    else
      System.out.println("Not all rows and columns are even");
  }
  
  public static boolean isEvenParity(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      int sum = 0;
      for (int j = 0; j < matrix[i].length; j++)
        sum += matrix[i][j];
      if (sum % 2 != 0)
        return false;
    }
    
    for (int j = 0; j < matrix[0].length; j++) {
      int sum = 0;
      for (int i = 0; i < matrix.length; i++)
        sum += matrix[i][j];
      if (sum % 2 != 0)
        return false;
    }
        
    return true;  
  }
}
