public class Exercise08_19 {
  public static void main(String[] args) {
/*    int[][] board = {
        {0, 1, 0, 6, 1, 6, 1},
        {0, 1, 6, 8, 6, 0, 1},
        {5, 2, 2, 1, 8, 2, 9},
        {6, 5, 6, 1, 1, 2, 1},
        {6, 9, 6, 2, 1, 9, 1},
        {3, 5, 9, 1, 3, 1, 1},
        {6, 5, 6, 3, 1, 9, 3},
        {1, 3, 6, 1, 9, 0, 7}    
    }; */
    
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter the number of rows: ");
    int numberOfRows = input.nextInt();
    System.out.print("Enter the number of columns: ");
    int numberOfColumns = input.nextInt();
    
    int[][] board = new int[numberOfRows][numberOfColumns];
    
    System.out.println("Enter the array values: ");
    for (int i = 0; i < board.length; i++)
      for (int j = 0; j < board[i].length; j++)
        board[i][j] = input.nextInt();
    
    System.out.println(isConsecutiveFour(board));
  }

  public static boolean isConsecutiveFour(int[][] values) {
    int numberOfRows = values.length;
    int numberOfColumns = values[0].length;

    // Check rows
    for (int i = 0; i < numberOfRows; i++) {
      if (isConsecutiveFour(values[i]))
        return true;
    }

    // Check columns
    for (int j = 0; j < numberOfColumns; j++) {
      int[] column = new int[numberOfRows];
      // Get a column into an array
      for (int i = 0; i < numberOfRows; i++)
        column[i] = values[i][j];
      
      if (isConsecutiveFour(column))
        return true;
    }
        
    // Check major diagonal (lower part)   
    for (int i = 0; i < numberOfRows - 3; i++) {
      int numberOfElementsInDiagonal 
        = Math.min(numberOfRows - i, numberOfColumns);     
      int[] diagonal = new int[numberOfElementsInDiagonal];
      for (int k = 0; k < numberOfElementsInDiagonal; k++)
        diagonal[k] = values[k + i][k];
      
      if (isConsecutiveFour(diagonal))
        return true;
    }
    
    // Check major diagonal (upper part)
    for (int j = 1; j < numberOfColumns - 3; j++) {
      int numberOfElementsInDiagonal 
        = Math.min(numberOfColumns - j, numberOfRows);     
      int[] diagonal = new int[numberOfElementsInDiagonal];
      for (int k = 0; k < numberOfElementsInDiagonal; k++)
        diagonal[k] = values[k][k + j];
      
      if (isConsecutiveFour(diagonal))
        return true;
    }

    // Check sub-diagonal (left part)
    for (int j = 3; j < numberOfColumns; j++) {
      int numberOfElementsInDiagonal 
        = Math.min(j + 1, numberOfRows);     
      int[] diagonal = new int[numberOfElementsInDiagonal];
      
      for (int k = 0; k < numberOfElementsInDiagonal; k++)
        diagonal[k] = values[k][j - k];
      
      if (isConsecutiveFour(diagonal))
        return true;
    }
    
    // Check sub-diagonal (right part)
    for (int i = 1; i < numberOfRows - 3; i++) {
      int numberOfElementsInDiagonal 
        = Math.min(numberOfRows - i, numberOfColumns);     
      int[] diagonal = new int[numberOfElementsInDiagonal];
    
      for (int k = 0; k < numberOfElementsInDiagonal; k++)
        diagonal[k] = values[k + i][numberOfColumns - k - 1];
    
      if (isConsecutiveFour(diagonal))
        return true;
    }
    
    return false; 
  }
  
  public static boolean isConsecutiveFour(int[] values) {    
    for (int i = 0; i < values.length - 3; i++) {
      boolean isEqual = true;        
      for (int j = i; j < i + 3; j++) {
        if (values[j] != values[j + 1]) {
          isEqual = false;
          break;
        }
      }
     
      if (isEqual) return true;
    }
    
    return false;
  }
}
