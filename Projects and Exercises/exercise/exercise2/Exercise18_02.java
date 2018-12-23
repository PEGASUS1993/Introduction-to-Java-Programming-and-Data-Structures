// Exercise18_02.java: Iterative solution for the Fibonacci number
public class Exercise18_02 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter an index for a Fibonacci number: ");
    int index = input.nextInt();

    // Find and display the Fibonacci number
    System.out.println("The Fibonacci number at index "  
      + index + " is " + fib(index));
  }

  public static long fib(int n) {
    int f0 = 0, f1 = 1, currentFib;

    if (n == 0) return 0;
    if (n == 1) return 1;

    for (int i = 2; i <= n; i++) {
      currentFib = f0 + f1;
      f0 = f1;
      f1 = currentFib;
    }

    return f1;
  }
}
