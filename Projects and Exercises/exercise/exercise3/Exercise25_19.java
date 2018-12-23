/**
 * Huffman Un-Zip Utility
 * Data Structures & Algorithms
 */
import java.io.*;

public class Exercise25_19 {
  /** Global Variables */
  private static final int SIZE = 2 * 128; // Number of ASCII Characters
  private static BufferedInputStream inFile; // Input file stream
  private static ObjectInputStream inKey; // Object input stream
  private static DataOutputStream outFile; // Output data stream
  private static String[] charCodes; // Array to hold Huffman codes of chars
  private static long originalSize, keySize; // Original & Key File Sizes
  private static long readSize = 0; // Size that has been read so far

  /** Main Method */
  public static void main(String[] args) {
    /** Check parameters & Get the file names from parameters */
    if ((args.length != 2)) {
      System.out.println("Usage: java Exercise25_19 input output");
      System.exit(1);
    }

    /**
     * Read Huffman Key & Original file size from compressed file Decompress
     * compressed file into output file
     */
    readHuffmanKey(args[0]);
    decompressFile(args[0], args[1]);
    System.out.println("Done!");
  }

  /** Read the Huffman Key & Original file size from file */
  public static void readHuffmanKey(String inputFile) {
    try {
      inKey = new ObjectInputStream(new FileInputStream(inputFile));
      originalSize = inKey.readLong(); // Read original file size
      charCodes = (String[]) (inKey.readObject()); // Read the key object

      // (Size of the key part + 2 bytes) till the start of zipped data
      keySize = (inKey.readLong()) + (Long.SIZE / 8) + 2;
      inKey.close();
    } catch (FileNotFoundException ex) {
      System.out.println(inputFile + " file cannot be found in the folder");
    } catch (IOException ex) {
      ex.printStackTrace(System.out);
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace(System.out);
    }
  }

  /** Decompress compressed file using the Huffman Codes */
  public static void decompressFile(String inputFile, String outputFile) {
    try {
      inFile = new BufferedInputStream(new FileInputStream(inputFile));
      outFile = new DataOutputStream(new FileOutputStream(outputFile));
      inFile.skip(keySize); // Skip the key part of the compressed file
      int p = 0; // Pointer in the input file
      String encodedText = "";

      // Read all the encoded binary text to string variable
      while ((p = inFile.read()) != -1)
        encodedText += getBits(p);

      // Iterate through encoded text char by char
      String temp = "";
      for (int i = 0; i < encodedText.length(); i++) {
        // Discard trash characters at the end
        if (readSize == originalSize)
          continue;
        temp += encodedText.charAt(i); // Temporary string appended

        // Test current string with all possible huffman codes
        for (int j = 0; j < SIZE; j++) {
          if ((charCodes[j] != null) && (temp.equals(charCodes[j]))) {
            // If match found, write char to output, reset & return
            outFile.write(j);
            readSize++;
            temp = "";
            continue;
          }
        }
      }

      // Close input & output files
      inFile.close();
      outFile.close();
    } catch (FileNotFoundException ex) {
      System.out.println(inputFile + " file cannot be found in the folder");
    } catch (IOException ex) {
      ex.printStackTrace(System.out);
    }
  }

  /** Read bits from a file */
  public static String getBits(int value) {
    String result = "";
    int mask = 1;

    for (int i = 7; i >= 0; i--) {
      int temp = value >> i;
      int bit = temp & mask;
      result = result + bit;
    }
    return result;
  }
}
