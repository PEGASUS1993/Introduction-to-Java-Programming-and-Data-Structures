public class Exercise13_16 {
  public static void main(String[] args) {
    Rational result = new Rational(0, 1);

    if (args.length != 1) {
      System.out.println(
        "Usage: java Exercise13_16 \"operand1 operator operand2\"");
      System.exit(1);
    }

    String[] tokens = args[0].split(" ");

    switch (tokens[1].charAt(0)) {
      case '+': result = getRational(tokens[0]).add(getRational(tokens[2]));
      break;
      case '-': result = getRational(tokens[0]).subtract(getRational(tokens[2]));
      break;
      case '*': result = getRational(tokens[0]).multiply(getRational(tokens[2]));
      break;
      case '/': result = getRational(tokens[0]).divide(getRational(tokens[2]));
    }

    System.out.println(tokens[0] + " " + tokens[1] + " " + tokens[2] + " = " + result);
  }

  static Rational getRational(String s) {
    String[] st = s.split("/");
    int numer = Integer.parseInt(st[0]);
    int denom = Integer.parseInt(st[1]); 
    return new Rational(numer, denom);
  }
}

/* Alternatively, you can use StringTokenizer. See Supplement III.AA on StringTokenizer as alternative 
import java.util.StringTokenizer;

public class Exercise15_18 {
  public static void main(String[] args) {
    Rational result = new Rational(0, 1);

    if (args.length != 3) {
      System.out.println(
        "Usage: java Exercise15_22 operand1 operator operand2");
      System.exit(0);
    }

    switch (tokens[1].charAt(0)) {
      case '+': result = getRational(tokens[0]).add(getRational(tokens[2]));
      break;
      case '-': result = getRational(tokens[0]).subtract(getRational(tokens[2]));
      break;
      case '*': result = getRational(tokens[0]).multiply(getRational(tokens[2]));
      break;
      case '/': result = getRational(tokens[0]).divide(getRational(tokens[2]));
    }

    System.out.println(tokens[0] + " " + tokens[1] + " " + tokens[2] + " = " + result);
  }

  static Rational getRational(String s) {
    StringTokenizer st = new StringTokenizer(s, "/");
    int numer = new Integer(st.nextToken()).intValue();
    int denom = new Integer(st.nextToken()).intValue();
    return new Rational(numer, denom);
  }
}
*/
