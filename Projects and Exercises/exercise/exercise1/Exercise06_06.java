public class Exercise06_06 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter line number: ");
    int lineNumber = input.nextInt();

    displayPattern(lineNumber);
  }

  public static void displayPattern(int n) {
    for (int row = 1; row <= n; row++) {
      // Print spaces
      for (int i = row; i < n; i++)
        System.out.print("  ");

      // Print numbers
      for (int i = row; i >= 1; i--)
        System.out.print(" " + i);

      System.out.println();
    }
  }
}
