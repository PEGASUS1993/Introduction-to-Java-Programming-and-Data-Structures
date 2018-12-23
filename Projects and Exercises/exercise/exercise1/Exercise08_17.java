import java.util.Scanner;

public class Exercise08_17 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Read the number of banks
    int n = input.nextInt();
    
    double[] balances = new double[n]; // Balance for each bank
    double[][] loan = new double[n][n]; // loan[i][j] is the amount bank i loans to bank j
    
    // Read the limit (minimum asset for keeping a bank safe)
    double limit = input.nextDouble(); 
    
    for (int i = 0; i < n; i++) {
      // Read each line
      balances[i] = input.nextDouble(); // Bank i's balance
      int numberOfBorrowers = input.nextInt(); // Number of banks borrowing from bank i
      for (int j = 0; j < numberOfBorrowers; j++) {
        int k = input.nextInt(); // Bank k borrows from bank i
        loan[i][k] = input.nextDouble();  // the loan amount of Bank k borrowing from bank i
      }
    }
    
    double[] asset = new double[n]; // asset[i] is bank i's total asset
    boolean[] isSafe = new boolean[n]; // All banks are safe initially
    for (int i = 0; i < n; i++)
      isSafe[i] = true;
    
    System.out.print("Unsafe banks: ");
    boolean newUnsafeFound = true; // Indicate whether a new unsafe bank is discovered
    while (newUnsafeFound) {
      newUnsafeFound = false; // Assume no new unsafe banks are found
      for (int i = 0; i < n; i++) {
        asset[i] = balances[i];
        for (int j = 0; j < n; j++)
          asset[i] += loan[i][j];
        
        if (isSafe[i] && asset[i] < limit) {
          isSafe[i] = false;
          newUnsafeFound = true;
          // Remove bank i is unsafe 
          System.out.print(i + " ");
          
          for (int k = 0; k < n; k++) {
            loan[k][i] = 0; // Bank i's borrowed loans are gone
          }
        }
      }
    }
  }
}
