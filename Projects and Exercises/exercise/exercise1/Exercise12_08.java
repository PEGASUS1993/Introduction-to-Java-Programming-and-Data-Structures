public class Exercise12_08 {
  public static void main(String[] args) throws HexFormatException {
    System.out.println(hex2Dec("A5"));
    System.out.println(hex2Dec("FAA"));
    System.out.println(hex2Dec("T10"));
    System.out.println(hex2Dec("ABC"));
    System.out.println(hex2Dec("10A"));
  }

  public static int hex2Dec(String hexString) throws HexFormatException {
    int value = convertHexToDec(hexString.charAt(0));
    for (int i = 1; i < hexString.length(); i++) {
      value = value * 16 + hexString.charAt(i) - '0';
    }

    return value;
  }

  static int convertHexToDec(char ch) throws HexFormatException {
    if (ch == 'A') {
      return 10;
    }
    else if (ch == 'B') {
      return 11;
    }
    else if (ch == 'C') {
      return 12;
    }
    else if (ch == 'D') {
      return 13;
    }
    else if (ch == 'E') {
      return 14;
    }
    else if (ch == 'F') {
      return 15;
    }
    else if (ch <= '9' && ch >= '0')
      return ch - '0';
    else
      throw new HexFormatException("Illegal hex character: " + ch);
  }
}

class HexFormatException extends Exception {
  HexFormatException() {
    super("Illegal hex character");
  }

  HexFormatException(String message) {
    super(message);
  }
}
