import java.util.Scanner;

public class Exercise04_06Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a four-digit binary string: ");
    
    String s = input.nextLine();
    
    if (s.length() != 4)
      System.out.println("Please enter exactly four binary digits");
    else {
      int value = 0;
      value += (s.charAt(3) - '0');
      value += (s.charAt(2) - '0') * 2;
      value += (s.charAt(1) - '0') * 2 * 2;
      value += (s.charAt(0) - '0') * 2 * 2 * 2;    
      
      System.out.println("The decimal number for " + s + " is " + value);
    }
  }

}
