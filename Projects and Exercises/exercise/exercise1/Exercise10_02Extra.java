import java.math.BigInteger;
import java.util.Scanner;

public class Exercise10_02Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);    
    System.out.print("Enter n: ");
    int n = input.nextInt();
    
    System.out.print("Enter k: ");
    int k = input.nextInt();

    System.out.println("The P(n, k) is " + getNumberOfPermutations(n, k));
  }
  
  public static BigInteger getNumberOfPermutations(int n, int k) {
    BigInteger result = BigInteger.ONE;
    
    for (int i = n - k + 1; i <= n; i++) {
      result = result.multiply(new BigInteger("" + i));
    }
    
    return result;
  }
}
