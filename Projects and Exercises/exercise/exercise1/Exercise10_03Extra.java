public class Exercise10_03Extra {
  public static void main(String[] args) {
    EQ board1 = new EQ();
    board1.set(0, 2);
    board1.set(1, 4);
    board1.set(2, 7);
    board1.set(3, 1);
    board1.set(4, 0);
    board1.set(5, 3);
    board1.set(6, 6);
    board1.set(7, 5);
    System.out.println("Is board 1 a correct eight queen placement? "
        + board1.isSolved());

    EQ board2 = new EQ(new int[] { 0, 4, 7, 5, 2, 6, 1, 3 });
    if (board2.isSolved()) {
      System.out.println("Eight queens are placed correctly "
        + "in board 2");
      board2.printBoard();
    }
    else {
      System.out.println("Eight queens are placed incorrectly " 
        + "in board 2");
    }
  }
}

class EQ {
  private int[] queens;

  public EQ() {
    queens = new int[8];
    for (int i = 0; i < queens.length; i++)
      queens[i] = -1;
  }

  public EQ(int[] queens) {
    this.queens = queens;
  }

  public int get(int i) {
    return queens[i];
  }

  public void set(int i, int j) {
    queens[i] = j;
  }

  public boolean isSolved() {
    for (int i = 0; i < queens.length; i++)
      if (!isValid(i, queens[i]))
        return false;
    return true;
  }

  public void printBoard() {
    for (int i = 0; i < queens.length; i++) {
      for (int k = 0; k < queens[i]; k++) {
        System.out.print("| ");
      }
      System.out.print("|X");
      for (int k = queens[i] + 1; k <= queens.length; k++) {
        System.out.print("| ");
      }
      System.out.println();
    }
  }

  /** Return true if a queen can be placed at (row, column) */
  private boolean isValid(int row, int column) {
    for (int i = 1; i <= row; i++)
      if (queens[row - i] == column // Check column
          || queens[row - i] == column - i // Check upleft diagonal
          || queens[row - i] == column + i) // Check upright diagonal
        return false; // There is a conflict
    return true; // No conflict
  }
}
