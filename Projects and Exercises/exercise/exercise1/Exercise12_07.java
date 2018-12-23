public class Exercise12_07 {
  public static void main(String[] args) {
    System.out.println(bin2Dec("10001"));
    System.out.println(bin2Dec("141111111"));
  }

  public static int bin2Dec(String binaryString) {
    int value = 0;

    for (int i = 0; i < binaryString.length(); i++) {
      char ch = binaryString.charAt(i);
      if (ch == '0' || ch == '1')
        value = value * 2 + binaryString.charAt(i) - '0';
      else
        throw new NumberFormatException("Illegal character: " + ch);
    }

    return value;
  }
}
