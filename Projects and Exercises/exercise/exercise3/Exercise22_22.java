public class Exercise22_22 {
  public static void main(String[] args) {
    // Read a Sudoku puzzle from the console
    int[][] grid = readAPuzzle();

    if (!isValid(grid)) 
      System.out.println("The input is not valid");      
    else if (search(grid)) // Start search from (0, 0)
      printGrid(grid);
    else
      System.out.println("No solutions");
  }

  /** Read a Sudoku puzzle from the keyboard */
  private static int[][] readAPuzzle() {
    // Create a Scanner
    java.util.Scanner input = new java.util.Scanner(System.in);

    System.out.println("Enter a Sudoku puzzle:");
    int[][] grid = new int[9][9];
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        grid[i][j] = input.nextInt();

    return grid;
  }
  
  /** Obtain a list of free cells from the puzzle */
  private static int[][] getFreeCellList(int[][] grid) {
    // Determine the number of free cells 
    int numberOfFreeCells = 0;   
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++) 
        if (grid[i][j] == 0) 
          numberOfFreeCells++;
    
    // Store free cell positions into freeCellList 
    int[][] freeCellList = new int[numberOfFreeCells][2];
    int count = 0;
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++) 
        if (grid[i][j] == 0) {
          freeCellList[count][0] = i;
          freeCellList[count++][1] = j;
        }
    
    return freeCellList;
  }
  
  /** Search for a solution */
  public static boolean search(int[][] grid) {
    int[][] freeCellList = getFreeCellList(grid); // Locate free cells   
    return search(0, freeCellList, grid); // Search from free cell 0
  }

  /** Search a solution starting from kth free cell in freeCellList */
  private static boolean search(
      int k, int[][] freeCellList, int[][] grid) {
    if (k < freeCellList.length) {
      int row = freeCellList[k][0];
      int column = freeCellList[k][1];
      
      for (int i = 1; i < 10; i++) {
        grid[row][column] = i;
        if (isValid(row, column, grid) &&
            search(k + 1, freeCellList, grid)) 
          return true;
      }
      
      // No solution at current position k. Backtrack to k - 1
      grid[row][column] = 0; // Reset grid[row][column] free
      return false;
    }
    else
      return true; // All free cells are filled
  }
  
  /** Check whether grid[i][j] is valid in the grid */
  private static boolean isValid(int i, int j, int[][] grid) {
    // Check whether grid[i][j] is valid at the i's row
    for (int column = 0; column < 9; column++)
      if (column != j && grid[i][column] == grid[i][j])
        return false;

    // Check whether grid[i][j] is valid at the j's column
    for (int row = 0; row < 9; row++)
      if (row != i && grid[row][j] == grid[i][j])
        return false;

    // Check whether grid[i][j] is valid in the 3 by 3 box
    for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
      for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
        if (row != i && col != j && grid[row][col] == grid[i][j])
          return false;

    return true; // The current value at grid[i][j] is valid
  }

  /** Check whether the fixed cells are valid in the grid */
  public static boolean isValid(int[][] grid) {
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (grid[i][j] != 0 && !isValid(i, j, grid)) 
          return false;

    return true; // The fixed cells are valid
  }

  /** Print the values in the grid */
  public static void printGrid(int[][] grid) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++)
        System.out.print(grid[i][j] + " ");
      System.out.println();
    }
  }
}
