import java.io.*;
import java.util.Scanner;

public class Exercise17_18 {
  public static void main(String[] args) throws IOException {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a file name: ");
    String filename = input.nextLine();
    
    FileInputStream in = new FileInputStream(filename);
    
    int value;
    while ((value = in.read()) != -1) {
      System.out.print(getBits(value));
    }
    
    in.close();
  }
  
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
