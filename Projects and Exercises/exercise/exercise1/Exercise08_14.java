import java.util.Scanner;

public class Exercise08_14 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the length of a square matrix: ");
    int n = input.nextInt();
    
    int[][] board = new int[n][n];
    boolean isSameOnARow = false, isSameOnAColumn = false,
      isSameOnADiagonal = false, isSameOnASubdiagonal = false;

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] = (int) (Math.random() * 2);
        System.out.print(board[i][j]);
      }

      System.out.println();
    }

    // Check rows
    for (int i = 0; i < board.length; i++) {
      boolean same = true;
      for (int j = 1; j < board[0].length; j++) {
        if (board[i][0] != board[i][j]) {
          same = false;
          break;
        }
      }
      if (same) {
        System.out.println("All " + board[i][0] + "'s on row " + i);
        isSameOnARow = true;
      }
    }

    // Check columns
    for (int j = 0; j < board[0].length; j++) {
      boolean same = true;
      for (int i = 1; i < board.length; i++) {
        if (board[0][j] != board[i][j]) {
          same = false;
          break;
        }

      }
      if (same) {
        System.out.println("All " + board[0][j] + "'s on column " + j);
        isSameOnAColumn = true;
      }
    }

    // Check major diagonal
    boolean same = true;
    for (int i = 1; i < board.length; i++) {
      if (board[0][0] != board[i][i]) {
        same = false;
        break;
      }
    }
    if (same) {
      System.out.println("All " + board[0][0] + "'s on major diagonal");
      isSameOnADiagonal = true;
    }

    // Check subdiagonal
    same = true;
    for (int i = 1; i < board.length; i++) {
      if (board[0][board.length - 1] != board[i][board.length - 1 - i]) {
        same = false;
        break;
      }
    }
    if (same) {
      System.out.println("All " + board[0][board.length - 1] + "'s on sub-diagonal");
      isSameOnASubdiagonal = true;
    }
    
    if (!isSameOnARow) 
      System.out.println("No same numbers on a row");

    if (!isSameOnAColumn) 
      System.out.println("No same numbers on a column");

    if (!isSameOnADiagonal) 
      System.out.println("No same numbers on the major diagonal");

    if (!isSameOnASubdiagonal) 
      System.out.println("No same numbers on the sub-diagonal");
  }
}

// An alternative version without using break
//import java.util.Scanner;
//
//public class Exercise08_14 {
//  public static void main(String[] args) {
//    Scanner input = new Scanner(System.in);
//    System.out.print("Enter the length of a square matrix: ");
//    int n = input.nextInt();
//    
//    int[][] board = new int[n][n];
//    boolean isSameOnARow = false, isSameOnAColumn = false,
//      isSameOnADiagonal = false, isSameOnASubdiagonal = false;
//
//    for (int i = 0; i < board.length; i++) {
//      for (int j = 0; j < board[0].length; j++) {
//        board[i][j] = (int) (Math.random() * 2);
//        System.out.print(board[i][j]);
//      }
//
//      System.out.println();
//    }
//
//    // Check rows
//    for (int i = 0; i < board.length; i++) {
//      boolean same = true;
//      for (int j = 1; j < board[0].length && same; j++) {
//        if (board[i][0] != board[i][j]) {
//          same = false;
//        }
//      }
//      if (same) {
//        System.out.println("All " + board[i][0] + "'s on row " + i);
//        isSameOnARow = true;
//      }
//    }
//
//    // Check columns
//    for (int j = 0; j < board[0].length; j++) {
//      boolean same = true;
//      for (int i = 1; i < board.length && same; i++) {
//        if (board[0][j] != board[i][j]) {
//          same = false;
//        }
//
//      }
//      if (same) {
//        System.out.println("All " + board[0][j] + "'s on column " + j);
//        isSameOnAColumn = true;
//      }
//    }
//
//    // Check major diagonal
//    boolean same = true;
//    for (int i = 1; i < board.length && same; i++) {
//      if (board[0][0] != board[i][i]) {
//        same = false;
//      }
//    }
//    if (same) {
//      System.out.println("All " + board[0][0] + "'s on major diagonal");
//      isSameOnADiagonal = true;
//    }
//
//    // Check subdiagonal
//    same = true;
//    for (int i = 1; i < board.length && same; i++) {
//      if (board[0][board.length - 1] != board[i][board.length - 1 - i]) {
//        same = false;
//      }
//    }
//    if (same) {
//      System.out.println("All " + board[0][board.length - 1] + "'s on sub-diagonal");
//      isSameOnASubdiagonal = true;
//    }
//    
//    if (!isSameOnARow) 
//      System.out.println("No same numbers on a row");
//
//    if (!isSameOnAColumn) 
//      System.out.println("No same numbers on a column");
//
//    if (!isSameOnADiagonal) 
//      System.out.println("No same numbers on the major diagonal");
//
//    if (!isSameOnASubdiagonal) 
//      System.out.println("No same numbers on the sub-diagonal");
//  }
//}
