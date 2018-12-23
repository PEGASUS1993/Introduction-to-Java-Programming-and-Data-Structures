import java.util.*;

public class Exercise20_19 {
  public static void main(String[] args) {
    int[] deck = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    
    long startTime = System.currentTimeMillis();
    
    int totalNumberOfCombinations = 0;
    int solutionCount = 0;
    for (int i1 = 0; i1 < deck.length; i1++) 
      for (int i2 = i1 + 1; i2 < deck.length; i2++) 
        for (int i3 = i2 + 1; i3 < deck.length; i3++) 
          for (int i4 = i3 + 1; i4 < deck.length; i4++) {
            totalNumberOfCombinations++;
            
            int a = deck[i1];
            int b = deck[i2];
            int c = deck[i3];
            int d = deck[i4];
            String s = findSolution(a, b, c, d);

            if (!s.equals("No solution"))
              solutionCount++;
          }
    
    System.out.println("Total number of combinations is " 
      + totalNumberOfCombinations);
    System.out.println("Total number of combinations with solutions is " 
      + solutionCount);
    System.out.println("The solution ratio is " 
      + 1.0 * solutionCount / totalNumberOfCombinations);
    
    long endTime = System.currentTimeMillis();
    System.out.println("Total time spent " + (endTime - startTime) / 1000);
  }

  /* Finds a solution for the cards shown */
  public static String findSolution(int a, int b, int c, int d) {
    String noSolution = "No solution";
    String solution;
    String[] operators = {"+", "-", "*", "/"};

    int[][] allCombinations = { { a, b, c, d }, { d, a, b, c },
        { c, d, a, b }, { b, c, d, a }, { a, b, d, c }, { c, a, b, d },
        { d, c, a, b }, { b, d, c, a }, { a, d, c, b }, { b, a, d, c },
        { c, b, a, d }, { d, c, b, a }, { a, c, b, d }, { d, a, c, b },
        { b, d, a, c }, { c, b, d, a }, { b, a, c, d }, { d, b, a, c },
        { c, d, b, a }, { a, c, d, b }, { a, d, b, c }, { c, a, d, b },
        { b, c, a, d }, { d, b, c, a } };

    for (String firstOp : operators)
      for (String secondOp : operators)
        for (String thirdOp : operators)
          for (int[] cardNums : allCombinations)
            for (int i = 0; i < 3; i++)
              for (int j = 0; j < 5; j++) {
                if (i == 0) {
                  if (j == 0) {
                    solution = cardNums[0] + firstOp
                        + cardNums[1] + secondOp
                        + cardNums[2] + thirdOp
                        + cardNums[3];
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;
    
                  } else if (j == 1) {
                    solution = "(" + cardNums[0] + firstOp
                        + cardNums[1] + ")" + secondOp
                        + cardNums[2] + thirdOp
                        + cardNums[3];
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;

                  } else if (j == 2) {
                    solution = cardNums[0] + firstOp + "("
                        + cardNums[1] + secondOp
                        + cardNums[2] + ")" + thirdOp
                        + cardNums[3];
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;

                  } else if (j == 3) {
                    solution = cardNums[0] + firstOp
                        + cardNums[1] + secondOp + "("
                        + cardNums[2] + thirdOp
                        + cardNums[3] + ")";
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;

                  } else if (j == 4) {
                    solution = "(" + cardNums[0] + firstOp
                        + cardNums[1] + ")" + secondOp
                        + "(" + cardNums[2] + thirdOp
                        + cardNums[3] + ")";
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;

                  }
                } else if (i == 1) {
                  if (j == 0) {
                    solution = "(" + cardNums[0] + firstOp
                        + cardNums[1] + secondOp
                        + cardNums[2] + ")" + thirdOp
                        + cardNums[3];
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;

                  } else if (j == 1) {
                    solution = "((" + cardNums[0] + firstOp
                        + cardNums[1] + ")" + secondOp
                        + cardNums[2] + ")" + thirdOp
                        + cardNums[3];
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;

                  } else if (j == 2) {
                    solution = "(" + cardNums[0] + firstOp
                        + "(" + cardNums[1] + secondOp
                        + cardNums[2] + "))" + thirdOp
                        + cardNums[3];
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;
                  }
                } else if (i == 2) {
                  if (j == 0) {
                    solution = cardNums[0] + firstOp + "("
                        + cardNums[1] + secondOp
                        + cardNums[2] + thirdOp
                        + cardNums[3] + ")";
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;

                  } else if (j == 1) {
                    solution = cardNums[0] + firstOp + "(("
                        + cardNums[1] + secondOp
                        + cardNums[2] + ")" + thirdOp
                        + cardNums[3] + ")";
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;

                  } else if (j == 2) {
                    solution = cardNums[0] + firstOp + "("
                        + cardNums[1] + secondOp + "("
                        + cardNums[2] + thirdOp
                        + cardNums[3] + "))";
                    if (EvaluateExpression
                        .evaluateExpression(solution) == 24)
                      return solution;

                  }
                }
              }
    return noSolution;
  }

  /* Class to evaluate card Expression */
  public static class EvaluateExpression {
    /** Evaluate an expression */
    public static double evaluateExpression(String expression) {
      // Create operandStack to store operands
      Stack<Double> operandStack = new Stack<Double>();

      // Create operatorStack to store operators
      Stack<Character> operatorStack = new Stack<Character>();

      // Extract operands and operators
      java.util.StringTokenizer tokens = new java.util.StringTokenizer(
          expression, "()+-/*", true);

      // Phase 1: Scan tokens
      while (tokens.hasMoreTokens()) {
        String token = tokens.nextToken().trim(); // Extract a token
        if (token.length() == 0) // Blank space
          continue; // Back to the while loop to extract the next
        // token
        else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
          // Process all +, -, *, / in the top of the operator stack
          while (!operatorStack.isEmpty()
              && (operatorStack.peek().equals('+')
                  || operatorStack.peek().equals('-')
                  || operatorStack.peek().equals('*') || operatorStack
                  .peek().equals('/'))) {
            processAnOperator(operandStack, operatorStack);
          }

          // Push the + or - operator into the operator stack
          operatorStack.push(new Character(token.charAt(0)));
        } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
          // Process all *, / in the top of the operator stack
          while (!operatorStack.isEmpty()
              && (operatorStack.peek().equals('*') || operatorStack
                  .peek().equals('/'))) {
            processAnOperator(operandStack, operatorStack);
          }

          // Push the * or / operator into the operator stack
          operatorStack.push(new Character(token.charAt(0)));
        } else if (token.trim().charAt(0) == '(') {
          operatorStack.push(new Character('(')); // Push '(' to stack
        } else if (token.trim().charAt(0) == ')') {
          // Process all the operators in the stack until seeing '('
          while (!operatorStack.peek().equals('(')) {
            processAnOperator(operandStack, operatorStack);
          }

          operatorStack.pop(); // Pop the '(' symbol from the stack
        } else { // An operand scanned
          // Push an operand to the stack
          operandStack.push(new Double(token));
        }
      }

      // Phase 2: process all the remaining operators in the stack
      while (!operatorStack.isEmpty()) {
        processAnOperator(operandStack, operatorStack);
      }

      // Return the result
      return ((Double) (operandStack.pop())).doubleValue();
    }

    /**
     * Process one operator: Take an operator from operatorStack and apply it
     * on the operands in the operandStack
     */
    public static void processAnOperator(Stack<Double> operandStack,
        Stack<Character> operatorStack) {
      if (operatorStack.peek().equals('+')) {
        operatorStack.pop();
        double op1 = ((Double) (operandStack.pop())).doubleValue();
        double op2 = ((Double) (operandStack.pop())).doubleValue();
        operandStack.push(new Double(op2 + op1));
      } else if (operatorStack.peek().equals('-')) {
        operatorStack.pop();
        double op1 = ((Double) (operandStack.pop())).doubleValue();
        double op2 = ((Double) (operandStack.pop())).doubleValue();
        operandStack.push(new Double(op2 - op1));
      } else if (operatorStack.peek().equals('*')) {
        operatorStack.pop();
        double op1 = ((Double) (operandStack.pop())).doubleValue();
        double op2 = ((Double) (operandStack.pop())).doubleValue();
        operandStack.push(new Double(op2 * op1));
      } else if (operatorStack.peek().equals('/')) {
        operatorStack.pop();
        double op1 = ((Double) (operandStack.pop())).doubleValue();
        double op2 = ((Double) (operandStack.pop())).doubleValue();
        operandStack.push(new Double(op2 / op1));
      }
    }
  }
}
