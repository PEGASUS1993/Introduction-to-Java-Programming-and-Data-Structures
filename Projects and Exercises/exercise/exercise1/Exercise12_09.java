public class Exercise12_09 {
  public static void main(String[] args) throws BinaryFormatException {
    System.out.println(bin2Dec("10001"));
    System.out.println(bin2Dec("141111111"));
  }

  public static int bin2Dec(String binaryString) throws BinaryFormatException {
    int value = 0;

    for (int i = 0; i < binaryString.length(); i++) {
      char ch = binaryString.charAt(i);
      if (ch == '0' || ch == '1')
        value = value * 2 + binaryString.charAt(i) - '0';
      else
        throw new BinaryFormatException("Illegal character: " + ch);
    }

    return value;
  }
}

class BinaryFormatException extends Exception {
  BinaryFormatException() {
    super("Illegal binary character");
  }

  BinaryFormatException(String message) {
    super(message);
  }
}
