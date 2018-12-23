public class Exercise05_19 {
  public static void main(String[] args) {
    int number = 0; // Number to print

    for (int row = 0; row <= 7; row++) {
      // Pad leading blanks
      for (int col = 1; col <= 7 - row; col++)
        System.out.printf("%4s", " ");

      // Print left half of the row
      for (int col = 0; col <= row; col++) {
        number = (int)Math.pow(2, col);

        System.out.printf("%4d", number);
      }

      // Print the right half of the row
      for (int col = row - 1; col >= 0; col--) {
        number = (int)Math.pow(2, col);

        System.out.printf("%4d", number);
      }
      
      // Start a new line
      System.out.print('\n');
    }
  }
}
