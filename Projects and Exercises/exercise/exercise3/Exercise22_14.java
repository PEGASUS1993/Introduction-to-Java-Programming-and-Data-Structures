public class Exercise22_14 {
  public static void main(String[] args) {
    long[] executionTime = new long[6];
    for (int i = 0; i < 6; i++) {
      long startTime = System.currentTimeMillis();
      findPrimeNumbers1(8000000 + i * 2000000);
      executionTime[i] = System.currentTimeMillis() - startTime;
    }
    System.out.println(
      "\t\t\t8000000\t10000000\t12000000\t14000000\t16000000\t18000000");
    System.out.println(
      "-----------------------------------------------------------------");
    System.out.print("Listing 21.4");

    for (int i = 0; i < 6; i++) {
      System.out.print("\t" + executionTime[i]);
    }

    System.out.print("\nListing 21.5");
    for (int i = 0; i < 6; i++) {
      long startTime = System.currentTimeMillis();
      findPrimeNumbers2(8000000 + i * 2000000);
      executionTime[i] = System.currentTimeMillis() - startTime;
    }

    for (int i = 0; i < 6; i++) {
      System.out.print("\t" + executionTime[i]);
    }
  }

  public static void findPrimeNumbers1(int N) {
    // final int N = 100000; // Find primes <= N
    final int NUMBER_PER_LINE = 10; // Display 10 per line
    int count = 0; // Count the number of prime numbers
    int number = 2; // A number to be tested for primeness

//    System.out.println("The prime numbers are \n");

    // Repeatedly find prime numbers
    while (number < N) {
      // Assume the number is prime
      boolean isPrime = true; // Is the current number prime?

      // Exercise03_21 if number is prime
      for (int divisor = 2; divisor <= (int) (Math.sqrt(number)); divisor++) {
        if (number % divisor == 0) { // If true, number is not prime
          isPrime = false; // Set isPrime to false
          break; // Exit the for loop
        }
      }

      // Print the prime number and increase the count
//      if (isPrime) {
//        count++; // Increase the count
//
//        if (count % NUMBER_PER_LINE == 0) {
//          // Print the number and advance to the new line
//          System.out.println(number);
//        }
//        else {
//          System.out.print(number + " ");
//        }
//      }

      // Check if the next number is prime
      number++;
    }
  }

  public static void findPrimeNumbers2(int N) {
    // A list to hold prime numbers
    java.util.List<Integer> list = new java.util.ArrayList<Integer>();
    // final int N = 10000000; // Find primes <= N
    final int NUMBER_PER_LINE = 10; // Display 10 per line
    int count = 0; // Count the number of prime numbers
    int number = 2; // A number to be tested for primeness

//    System.out.println("The prime numbers are \n");

    // Repeatedly find prime numbers
    while (number < N) {
      // Assume the number is prime
      boolean isPrime = true; // Is the current number prime?

      // Exercise03_21 if number is prime
      for (int k = 0; k < list.size() &&
                   list.get(k) <= (int) (Math.sqrt(number)); k++) {
        if (number % list.get(k) == 0) { // If true, number is not prime
          isPrime = false; // Set isPrime to false
          break; // Exit the for loop
        }
      }

      // Check if the next number is prime
      number++;
    }
  }
}
