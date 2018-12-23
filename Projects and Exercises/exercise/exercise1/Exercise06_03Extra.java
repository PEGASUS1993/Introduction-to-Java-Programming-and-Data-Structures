public class Exercise06_03Extra {
  public static void main(String[] args) {
    // Prompt the user to enter a string
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter an integer: ");
    int value = input.nextInt();
    System.out.println("The binary value is " + decimalToBinary(value));
  }

  public static String decimalToBinary(int value) {
    StringBuffer buffer = new StringBuffer();

    while (value != 0) {
      int bit = value % 2;
      buffer.insert(0, bit);
      value = value / 2;
    }

    return buffer.toString();
  }
}
