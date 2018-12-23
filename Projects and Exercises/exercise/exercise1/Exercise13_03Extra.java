import java.util.Scanner;

public class Exercise13_03Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the first rational number: ");
    Rational r1 = parseRationalNumber(input.nextLine());
    System.out.print("Enter the second rational number: ");
    Rational r2 = parseRationalNumber(input.nextLine());
    System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
  }
  
  private static String removeBlank(String s) {
    String result = "";
    for (int i = 0; i < s.length(); i++) 
      if (s.charAt(i) != ' ') result += s.charAt(i);
    return result;
  }
  
  // For simplicity, we define this method in the test program rather 
  // inside the Rational class!!!
  public static Rational parseRationalNumber(String s) {
    s = removeBlank(s);
    if (s.contains("/")) {
      String s1 = s.substring(0, s.indexOf('/'));
      String s2 = s.substring(s.indexOf('/') + 1);
      return new Rational(Integer.parseInt(s1.trim()), Integer.parseInt(s2.trim()));
    }
    else {
      return new Rational(Integer.parseInt(s), 1);
    }
  }
}
