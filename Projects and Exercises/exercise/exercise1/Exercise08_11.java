import java.util.*;

public class Exercise08_11 {

  public static void main(String[] args) {
    System.out.print("Enter a number between 0 and 511: ");
    Scanner input = new Scanner(System.in);
    short number = input.nextShort();

    char[] binaryChars = toBinaryChars(number);

    for (int i = 1; i <= binaryChars.length; i++) {
      System.out.print(((binaryChars[i - 1] == '0') ? 'H' : 'T') + " ");
      if (i % 3 == 0) {
        System.out.println();
      }
    }
  }

  public static char[] toBinaryChars(short number) {
    char[] result = new char[9];

    int i = result.length - 1;
    while (number != 0) {
      if (number % 2 == 0) {
        result[i--] = '0';
      } else {
        result[i--] = '1';
      }
      number /= 2;
    }

    for (int k = i; k >= 0; k--) {
      result[k] = '0';
    }

    return result;
  }
}
