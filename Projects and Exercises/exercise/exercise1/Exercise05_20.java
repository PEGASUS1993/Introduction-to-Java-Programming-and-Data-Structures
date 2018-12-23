public class Exercise05_20 {
  // Main method
  public static void main(String[] args) {
    int count = 1; // Count the number of prime numbers
    int number = 2; // A number to be tested for primeness
    boolean isPrime = true;  // If the current number is prime?

    System.out.println("The prime numbers from 2 to 1000 are \n");

    // Repeatedly test if a new number is prime
    while (number <= 1000) {
      // Assume the number is prime
      isPrime = true;

      // Set isPrime to false, if the number is prime
      for (int divisor = 2; divisor <= number / 2; divisor++) {
        if (number % divisor == 0) { // If true, the number is prime
          isPrime = false;
          break;  // Exit the for loop
        }
      }

      // Print the prime number and increase the count
      if (isPrime) {
        if (count%8 == 0) {
          // Print the number and advance to the new line
          System.out.println(number);
        }
        else
          System.out.print(number + " ");

        count++;  // Increase the count
      }

      // Check if the next number is prime
      number++;
    }
  }
}
