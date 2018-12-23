import java.io.*;

public class Exercise22_08 {
  final static int ARRAY_SIZE = 100000;

  public static void main(String[] args) throws Exception {
    // A list to hold prime numbers
    final long N = 100000002; // Find primes up to N
    long[] primeNumbers = new long[ARRAY_SIZE];

    long number; // A number to be tested for primeness
    RandomAccessFile inout =
      new RandomAccessFile("PrimeNumbers.dat", "rw");
    if (inout.length() == 0) {
      number = 1;
    }
    else {
      inout.seek(inout.length() - 8); // A long is 8 bytes
      number = inout.readLong(); // Get the last prime number in the file 
    }

    int squareRoot = 1;
    
    // Repeatedly find prime numbers
    newNumber:while (number <= N) {
      // Check if 2, 3, 4, ..., N is prime
      number++;
      inout.seek(0);

      if (squareRoot * squareRoot < number) {
        squareRoot++;
      }

      while (inout.getFilePointer() < inout.length()) {
        int size = readNextBatch(primeNumbers, inout);

        // Exercise03_21 if number is prime
        for (int k = 0; k < size && primeNumbers[k] <= squareRoot; k++) {
          if (number % primeNumbers[k] == 0) { // If true, not prime
            continue newNumber; // Exit the for loop
          }
        }
      }

      // Append a new prime number to the end of the file
      inout.seek(inout.length());
      inout.writeLong(number);
    }
    
    inout.close();
  }

  public static int readNextBatch(long[] primeNumbers, RandomAccessFile inout) {
    int size = 0;
    try {
      while (inout.getFilePointer() < inout.length() && size < ARRAY_SIZE) {
        primeNumbers[size++] = inout.readLong();
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    return size;
  }
}
