import java.io.*;

public class CheckPrimeNumbers {
  public static void main(String[] args) throws Exception {
    int count = 0; // Count the number of prime numbers

    DataInputStream input =
      new DataInputStream(new BufferedInputStream(
      new FileInputStream(args[0])));

    long[] limits = {
      10,
      100,
      1000,
      10000,
      100000,
      1000000,
      10000000,
      100000000,
      1000000000,
      10000000000L,
      100000000000L,
      1000000000000L,
      10000000000000L,
      100000000000000L,
      1000000000000000L,
      10000000000000000L,
      100000000000000000L,
      1000000000000000000L};
    int k = 0;

    while (input.available() > 0) {
      if (input.readLong() > limits[k]) {
        System.out.println("Number of prime number <= " +
          limits[k] + " is " + count);
        k++;
        if (k == limits.length) break;
      }
      count++;
    }

    input.close();
    System.out.println("Total number of prime numbers found: " +
      count);
  }
}
