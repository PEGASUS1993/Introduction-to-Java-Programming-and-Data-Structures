/** Java has the Integer.toBinaryString(int) for converting a 
 * decimal value to a binary as a string. 
 */
import java.util.Scanner;

public class Exercise05_37 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter an integer: ");
    int decimal = input.nextInt();
    
    String binaryString = "";
    int value = decimal;
    while (value != 0) {
      binaryString = value % 2 + binaryString;
      value = value / 2;
    }
        
    System.out.println(decimal + "'s binary representation is " +
        binaryString);
  }
}
