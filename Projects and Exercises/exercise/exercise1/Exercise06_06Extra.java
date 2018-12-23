import java.util.Scanner;

public class Exercise06_06Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter a credit card number as a string: ");
    String number = input.nextLine();
    
    if (isValid(number))
      System.out.println(number + " is valid");
    else
      System.out.println(number + " is invalid");
  }

  /** Return true if the card number is valid */
  public static boolean isValid(String number) {
    return (number.startsWith("4") || number.startsWith("5") ||
       number.startsWith("6") || number.startsWith("37")) &&
    (sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0;
  }
  
  /** Get the result from Step 2 */
  public static int sumOfDoubleEvenPlace(String cardNumber) {
    int result = 0;
    
    for (int i = cardNumber.length() - 2; i >= 0; i = i - 2) {
      result += getDigit(
          Integer.parseInt(cardNumber.charAt(i) + "") * 2);
    }
    
    return result;
  }
  
  /** Return this number if it is a single digit, otherwise, return 
   * the sum of the two digits */
  public static int getDigit(int number) {
    return number % 10 + (number / 10 % 10);
  }
  
  /** Return sum of odd place digits in number */
  public static int sumOfOddPlace(String cardNumber) {
    int result = 0;

    for (int i = cardNumber.length() - 1; i >= 0; i = i - 2) {
      result += Integer.parseInt(cardNumber.charAt(i) + "");
    }
    
    return result;
  }
}
