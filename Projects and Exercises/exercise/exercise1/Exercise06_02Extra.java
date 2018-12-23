public class Exercise06_02Extra {
  public static void main(String[] args) {
    // Prompt the user to enter a string
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a binary number: ");
    String binaryValue = input.nextLine();
    System.out.println("The hex value is " + binaryToHex(binaryValue));
  }

  public static String binaryToHex(String binaryValue) {
    int decimalValue = binaryToDecimal(binaryValue);
    return decimalToHex(decimalValue);
  }

  public static int binaryToDecimal(String binaryString) {
    int value = binaryString.charAt(0) - '0';
    for (int i = 1; i < binaryString.length(); i++) {
      value = value * 2 + binaryString.charAt(i) - '0';
    }

    return value;
  }
  
  /** Convert a decimal to a hex as a string */
  public static String decimalToHex(int decimal) {
    String hex = "";
    
    while (decimal != 0) {
      int hexValue = decimal % 16; 
      hex = toHexChar(hexValue) + hex;
      decimal = decimal / 16;
    }
    
    return hex;
  }
  
  /** Convert an integer to a single hex digit in a character */
  public static char toHexChar(int hexValue) {
    if (hexValue <= 9 && hexValue >= 0)
      return (char)(hexValue + '0');
    else  // hexValue <= 15 && hexValue >= 10
      return (char)(hexValue - 10 + 'A');
  }
}
