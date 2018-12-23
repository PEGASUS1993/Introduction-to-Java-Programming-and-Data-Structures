import java.util.Scanner;

public class Exercise06_10Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of rows: ");
    int numberOfRows = input.nextInt();
    
    for (int i = 0; i <= numberOfRows; i++) {
      for (int j = 0; j <= numberOfRows - i; j++) {
        System.out.printf("%2s", " ");
      }
      
      for (int j = 0; j <= i; j++) {
        System.out.printf("%4d", c(i, j));
      }
      
      System.out.println();
    }
  }

  public static int c(int m, int n) {
    return factorial(m) / factorial(m - n) / factorial(n);
  }

  public static int factorial(int n) {
    int result = 1;
    for (int i = 1; i <= n; i++)
      result *= i;
    return result;
  }
}
