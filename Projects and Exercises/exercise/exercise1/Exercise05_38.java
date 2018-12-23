/** Java has the Integer.toHexString(int) for converting a 
 * decimal value to a binary as a string. 
 */
import java.util.Scanner;

public class Exercise05_38 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter an integer: ");
    int decimal = input.nextInt();
    
    String octalString = "";
    int value = decimal;
    while (value != 0) {
      int single = value % 8;
      octalString = single + octalString;
      value = value / 8;
    }
        
    System.out.println(decimal + "'s octal representation is " +
        octalString);
  }
}
