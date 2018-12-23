public class Exercise10_05 {
  public static void main(String[] args) {
    StackOfIntegers stack = new StackOfIntegers(2);
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Prompt the user to enter an integer
    System.out.print("Enter an integer: ");

    // Convert string to int
    int number = input.nextInt();

    System.out.println("The factors for " + number + " is");

    // Find and store all the smallest factors of the integer
    int factor = 2;
    while (factor <= number) {
      if (number % factor == 0) {
        number = number / factor;
        stack.push(factor);
      }
      else {
        factor++;
      }
    }

    // Display factors
    while (!stack.empty()) {
      System.out.print(stack.pop() + " ");
    }
  }
}
