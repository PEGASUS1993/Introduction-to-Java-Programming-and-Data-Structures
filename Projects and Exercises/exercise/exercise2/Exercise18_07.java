import java.util.Scanner;

public class Exercise18_07 {
  static int count = 0;

  public static void main(String[] args) {
    // Read the index
    
    Scanner input = new Scanner(System.in);
    System.out.print("Enter an index for the Fibonacci number: ");
    int index = input.nextInt();

    System.out.println("Fibonacci number at index " + index + " is " + fib(index));
    System.out.println("Number of times fib is invoked? " + count);
  }

  /** The method for finding the Fibonacci number */
  public static long fib(long index) {
    count++;

    if (index == 0) // Base case
      return 0;
    else if (index == 1) // Base case
      return 1;
    else  // Reduction and recursive calls
      return fib(index - 1) + fib(index - 2);
  }
}
