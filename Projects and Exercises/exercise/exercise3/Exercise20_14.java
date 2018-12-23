public class Exercise20_14 {
  public static void main(String[] args) {
    // Check number of arguments passed
    if (args.length < 1) {
      System.out.println(
        "Usage: java Exercise20_14 expression");
      System.exit(1);
    }

    String expression = "";
    for (int i = 0; i < args.length; i++) {
      expression += args[i] + " ";
    }

    try {
      System.out.println(evaluateExpression(expression));
    }
    catch (Exception ex) {
      System.out.println("Wrong expression");
    }
  }

  /** Evaluate an expression */
  public static int evaluateExpression(String expression) {
    // Create operandStack to store operands
    MyStack operandStack = new MyStack();

    // Extract operands and operators
    java.util.StringTokenizer tokens =
      new java.util.StringTokenizer(expression, " +-/*%", true);

    // Phase 1: Scan tokens
    while (tokens.hasMoreTokens()) {
      String token = tokens.nextToken().trim(); // Extract a token
      if (token.length() == 0) { // Blank space
        continue; // Back to the while loop to extract the next token
      }
      else if (token.charAt(0) == '+' || token.charAt(0) == '-' ||
        token.charAt(0) == '*' || token.charAt(0) == '/') {
        processAnOperator(token.charAt(0), operandStack);
      }
      else { // An operand scanned
        // Push an operand to the stack
        operandStack.push(new Integer(token));
      }
    }

    // Return the result
    return ((Integer)(operandStack.pop())).intValue();
  }

  /** Process one operator: Take an operator from operatorStack and
   *  apply it on the operands in the operandStack */
  public static void processAnOperator(char op, MyStack operandStack) {
    if (op == '+') {
      int op1 = ((Integer)(operandStack.pop())).intValue();
      int op2 = ((Integer)(operandStack.pop())).intValue();
      operandStack.push(new Integer(op2 + op1));
    }
    else if (op == '-') {
      int op1 = ((Integer)(operandStack.pop())).intValue();
      int op2 = ((Integer)(operandStack.pop())).intValue();
      operandStack.push(new Integer(op2 - op1));
    }
    else if ((op == '*')) {
      int op1 = ((Integer)(operandStack.pop())).intValue();
      int op2 = ((Integer)(operandStack.pop())).intValue();
      operandStack.push(new Integer(op2 * op1));
    }
    else if (op == '/') {
      int op1 = ((Integer)(operandStack.pop())).intValue();
      int op2 = ((Integer)(operandStack.pop())).intValue();
      operandStack.push(new Integer(op2 / op1));
    }
  }
	}
