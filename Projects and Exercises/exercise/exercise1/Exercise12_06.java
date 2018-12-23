public class Exercise12_06 {
  public static void main(String[] args) {
    System.out.println(hex2Dec("A5"));
    System.out.println(hex2Dec("FAA"));
    System.out.println(hex2Dec("T10"));
    System.out.println(hex2Dec("ABC"));
    System.out.println(hex2Dec("10A"));
  }

  public static int hex2Dec(String hexString) {
    int value = 0;
    for (int i = 0; i < hexString.length(); i++) {
      value = value * 16 + convertHexToDec(hexString.charAt(i));
    }

    return value;
  }

  static int convertHexToDec(char ch) {
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
      throw new NumberFormatException("Illegal character: " + ch);
  }
}
