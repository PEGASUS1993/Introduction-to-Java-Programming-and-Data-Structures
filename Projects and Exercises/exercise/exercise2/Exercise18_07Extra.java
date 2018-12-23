import java.util.Scanner;

public class Exercise18_07Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter an expression: ");
    String exp = input.nextLine();

    System.out.println(exp + " = " + evaluateExpression(exp));
  }

  public static double evaluateExpression(String s) {
    if (s.contains("+")) {
      int index = s.indexOf('+');
      return evaluateExpression(s.substring(0, index)) + evaluateExpression(s.substring(index + 1));
    }
    else if (s.contains("-")) {
      int index = s.indexOf('-');
      return evaluateExpression(s.substring(0, index)) - evaluateExpression(s.substring(index + 1));
    }
    else if (s.contains("*")) {
      int index = s.indexOf('*');
      return evaluateExpression(s.substring(0, index)) * evaluateExpression(s.substring(index + 1));
    }
    else if (s.contains("/")) {
      int index = s.indexOf('/');
      return evaluateExpression(s.substring(0, index)) / evaluateExpression(s.substring(index + 1));
    }
    else // It is an integer base case
      return Double.parseDouble(s.trim());  
  }
}
