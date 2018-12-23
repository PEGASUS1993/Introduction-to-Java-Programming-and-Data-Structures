import java.util.Scanner;

public class Exercise08_20 {
  public static Scanner input = new Scanner(System.in);  

  public static void main(String[] args) {
    char[][] board = new char[6][7];

    displayBoard(board);

    while (true) {
      // Prompt the first player
      dropADisc('R', board);
      
      displayBoard(board);
      if (isWon(board)) {
        System.out.println("The red player won");
        System.exit(1);
      }
      else if (isDraw(board)) {
        System.out.println("No winner");
        System.exit(2);
      }

      // Prompt the second player
      dropADisc('Y', board);
      displayBoard(board);

      if (isWon(board)) {
        System.out.println("The yellow player won");
        System.exit(3);
      }
      else if (isDraw(board)) {
        System.out.println("No winner");
        System.exit(4);
      }
    }
  }

  public static void dropADisc(char player, char[][] board) { 
    boolean done = false;
    
    do {
      System.out.print("Drop a " + (player == 'R' ? "red" : "yellow") + " disk at column (0-6): ");
      int column = input.nextInt();
      
      if (placeADisk(board, column, player)) { 
        done = true;
      }
      else
        System.out.println("This column is full. Try a different column");
    }
    while (!done);
  }
  
  static boolean placeADisk(char[][] board, int column, char player) {
    for (int i = 0; i < board.length; i++) {
      if (board[i][column] == '\u0000') {
        board[i][column] = player; 
        return true; // successful
      }
    }
    
    return false; // full, unsuccessful
  }
  
  static void displayBoard(char[][] board) {
    for (int i = board.length - 1; i >= 0; i--) {
      System.out.print("|");
      for (int j = 0; j < board[i].length; j++)
        System.out.print(board[i][j] != '\u0000' ?  board[i][j] + "|": " |");
      System.out.println();
    }
    System.out.println("----------------------");
  }

  public static boolean isWon(char[][] board) {
    return isConsecutiveFour(board);
  }

  public static boolean isDraw(char[][] board) {
    for (int i = 0; i < board.length; i++)
      for (int j = 0; j < board[i].length; j++)
        if (board[i][j] == '\u0000') return false;

    return true; // All cells are now occupied
  }
  
  public static boolean isConsecutiveFour(char[][] values) {
    int numberOfRows = values.length;
    int numberOfColumns = values[0].length;

    // Check rows
    for (int i = 0; i < numberOfRows; i++) {
      if (isConsecutiveFour(values[i]))
        return true;
    }

    // Check columns
    for (int j = 0; j < numberOfColumns; j++) {
      char[] column = new char[numberOfRows];
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
      char[] diagonal = new char[numberOfElementsInDiagonal];
      for (int k = 0; k < numberOfElementsInDiagonal; k++)
        diagonal[k] = values[k + i][k];
      
      if (isConsecutiveFour(diagonal))
        return true;
    }
    
    // Check major diagonal (upper part)
    for (int j = 1; j < numberOfColumns - 3; j++) {
      int numberOfElementsInDiagonal 
        = Math.min(numberOfColumns - j, numberOfRows);     
      char[] diagonal = new char[numberOfElementsInDiagonal];
      for (int k = 0; k < numberOfElementsInDiagonal; k++)
        diagonal[k] = values[k][k + j];
      
      if (isConsecutiveFour(diagonal))
        return true;
    }

    // Check sub-diagonal (left part)
    for (int j = 3; j < numberOfColumns; j++) {
      int numberOfElementsInDiagonal 
        = Math.min(j + 1, numberOfRows);     
      char[] diagonal = new char[numberOfElementsInDiagonal];
      
      for (int k = 0; k < numberOfElementsInDiagonal; k++)
        diagonal[k] = values[k][j - k];
      
      if (isConsecutiveFour(diagonal))
        return true;
    }
    
    // Check sub-diagonal (right part)
    for (int i = 1; i < numberOfRows - 3; i++) {
      int numberOfElementsInDiagonal 
        = Math.min(numberOfRows - i, numberOfColumns);     
      char[] diagonal = new char[numberOfElementsInDiagonal];
    
      for (int k = 0; k < numberOfElementsInDiagonal; k++)
        diagonal[k] = values[k + i][numberOfColumns - k - 1];
    
      if (isConsecutiveFour(diagonal))
        return true;
    }
    
    return false; 
  }
  
  public static boolean isConsecutiveFour(char[] values) {    
    for (int i = 0; i < values.length - 3; i++) {
      boolean isEqual = true;        
      for (int j = i; j < i + 3; j++) {
        if (values[j] == '\u0000' || values[j] != values[j + 1]) {
          isEqual = false;
          break;
        }
      }
     
      if (isEqual) return true;
    }
    
    return false;
  }
}
