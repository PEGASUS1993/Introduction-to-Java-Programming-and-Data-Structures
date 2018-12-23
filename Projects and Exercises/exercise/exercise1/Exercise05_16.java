public class Exercise05_16 {
  // Main method
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Prompt the user to enter a positive integer
    System.out.print(
      "Enter a positive integer: ");
    int number = input.nextInt();

    // Find all the smallest factors of the integer
    System.out.print("The factors for " + number + " is ");
    int factor = 2;
    while (factor <= number) {
      if (number % factor == 0) {
        number = number / factor;
        System.out.print(factor + " ");
      }
      else {
        factor++;
      }
    }
    System.out.println();
  }
}
