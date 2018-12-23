public class Exercise20_16 {
  public static void main(String[] args) {
    // Check number of arguments passed
    if (args.length < 1) {
      System.out.println(
        "Usage: java Exercise20_16 expression");
      System.exit(1);
    }

    String expression = "";
    for (int i = 0; i < args.length; i++) {
      expression += args[i];
    }

    try {
      System.out.println(infixToPostfix(expression));
    }
    catch (Exception ex) {
      System.out.println("Wrong expression");
    }
  }

  public static String infixToPostfix(String expression) {
    // Result string
    String s = "";

    // Create operandStack to store operands
    MyStack operandStack = new MyStack();

    // Create operatorStack to store operators
    MyStack operatorStack = new MyStack();

    // Extract operands and operators
    java.util.StringTokenizer tokens =
      new java.util.StringTokenizer(expression, "()+-/*%", true);

    // Phase 1: Scan tokens
    while (tokens.hasMoreTokens()) {
      String token = tokens.nextToken().trim(); // Extract a token
      if (token.length() == 0) { // Blank space
        continue; // Back to the while loop to extract the next token
      }
      else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
        // Process all +, -, *, / in the top of the operator stack
        while (!operatorStack.isEmpty() &&
          (operatorStack.peek().equals('+') ||
          operatorStack.peek().equals('-') ||
          operatorStack.peek().equals('*') ||
          operatorStack.peek().equals('/')
          )) {
          s += operatorStack.pop() + " ";
        }

        // Push the + or - operator into the operator stack
        operatorStack.push(new Character(token.charAt(0)));
      }
      else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
        // Process all *, / in the top of the operator stack
        while (!operatorStack.isEmpty() &&
          (operatorStack.peek().equals('*') ||
          operatorStack.peek().equals('/')
          )) {
					s += operatorStack.pop() + " ";
        }

        // Push the * or / operator into the operator stack
        operatorStack.push(new Character(token.charAt(0)));
      }
      else if (token.trim().charAt(0) == '(') {
        operatorStack.push(new Character('(')); // Push '(' to stack
      }
      else if (token.trim().charAt(0) == ')') {
        // Process all the operators in the stack until seeing '('
        while (!operatorStack.peek().equals('(')) {
					s += operatorStack.pop() + " ";
        }

        operatorStack.pop(); // Pop the '(' symbol from the stack
      }
      else { // An operand scanned
        // Push an operand to the stack
        s += token + " ";
      }
    }

    // Phase 2: process all the remaining operators in the stack
    while (!operatorStack.isEmpty()) {
			s += operatorStack.pop() + " ";
    }

    // Return the result
    return s;
  }
}
