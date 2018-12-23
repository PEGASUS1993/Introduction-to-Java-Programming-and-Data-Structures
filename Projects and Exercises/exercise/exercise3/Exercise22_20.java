import java.util.Scanner;

public class Exercise22_20 {
  public static void main(String[] args) {
    // Read a Sudoku puzzle
    int[][] grid = readAPuzzle();

    if (!isValid(grid))
      System.out.println("Invalid input");
    else {
      int count = search(grid);
      if (count == 0)
        System.out.println("No solution");
      else
        System.out.println("There are " + count + " solutions");
    }
  }

  /** Read a Sudoku puzzle from the keyboard */
  public static int[][] readAPuzzle() {
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    System.out.println("Enter a Sudoku puzzle:");
    int[][] grid = new int[9][9];
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        grid[i][j] = input.nextInt();

    return grid;
  }

  /** Obtain a list of free cells from the puzzle */
  public static int[][] getFreeCellList(int[][] grid) {
    // 81 is the maximum number of free cells
    int[][] tempList = new int[81][2];
    int numberOfFreeCells = 0;

    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (grid[i][j] == 0) {
          tempList[numberOfFreeCells][0] = i;
          tempList[numberOfFreeCells][1] = j;
          numberOfFreeCells++;
        }

    // Trim freeCellList
    int[][] freeCellList = new int[numberOfFreeCells][2];
    for (int i = 0; i < numberOfFreeCells; i++) {
      freeCellList[i][0] = tempList[i][0];
      freeCellList[i][1] = tempList[i][1];
    }

    return freeCellList;
  }

  /** Print the values in the grid */
  public static void printGrid(int[][] grid) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++)
        System.out.print(grid[i][j] + " ");
      System.out.println();
    }
  }

  /** Search for up to 3 solutions. 
   * This method returns the number of solutions found.
   * Possible return values are 0, 1, 2, and 3.  */
  public static int search(int[][] grid) {
    int[][] freeCellList = getFreeCellList(grid); // Free cells
    int k = 0; // Start from the first free cell
    int count = 0; // Multiple solutions: Count for 3 solutions

    boolean done = false;
    while (!done) {
      int i = freeCellList[k][0];
      int j = freeCellList[k][1];
      if (grid[i][j] == 0)
        grid[i][j] = 1; // Start with 1

      if (isValid(i, j, grid)) {
        if (k + 1 == freeCellList.length) { // No more free cells
          // A solution is found
          count++;
          if (count < 3) { // Display the first two solutions
            System.out.println("Sample solution " + (count) + ":");
            printGrid(grid); // Multiple solutions: print a solution
          }
          // Now search for the next possible solution
          if (grid[i][j] < 9) {
            grid[i][j] = grid[i][j] + 1; // Check the next possible value
          } 
          else { // grid[i][j] is 9, backtrack
            while (grid[i][j] == 9) {
              grid[i][j] = 0; // Reset to free cell
              if (k == 0) {
                done = true; // No possible value any more, done!
                return count; 
              }
              k--; // Backtrack
              i = freeCellList[k][0];
              j = freeCellList[k][1];
            }

            grid[i][j] = grid[i][j] + 1; // Check the next possible value
          }
        } 
        else { // Move to the next free cell
          k++;
        }
      }
      else if (grid[i][j] < 9) {
        grid[i][j] = grid[i][j] + 1; // Check the next possible value
      } 
      else { // grid[i][j] is 9, backtrack
        while (grid[i][j] == 9) {
          grid[i][j] = 0; // Reset to free cell
          if (k == 0) {
            return count; // No possible value
          }
          k--; // Backtrack
          i = freeCellList[k][0];
          j = freeCellList[k][1];
        }

        grid[i][j] = grid[i][j] + 1; // Check the next possible value
      }
    }

    return count; // A solution is found
  }

  /** Check whether grid[i][j] is valid in the grid */
  public static boolean isValid(int i, int j, int[][] grid) {
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
        if (grid[i][j] < 0 || grid[i][j] > 9 ||
           (grid[i][j] != 0 && !isValid(i, j, grid))) {
          System.out.println("grid[i][j] = " + grid[i][j]);
          System.out.println("i = " + i + " j " + j);
          return false;
        }

    return true; // The fixed cells are valid
  }
}
