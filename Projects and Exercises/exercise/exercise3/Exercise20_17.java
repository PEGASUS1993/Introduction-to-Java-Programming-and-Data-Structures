import java.util.Stack;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Exercise20_17 extends Application {
  private TextField[] tfValue = new TextField[4];
  private TextField tfSolution = new TextField();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Button btSolve = new Button("Solve");
    HBox hBoxTop = new HBox(5);
    hBoxTop.setAlignment(Pos.CENTER);
    hBoxTop.getChildren().addAll(tfSolution, btSolve);

    for (int i = 0; i < tfValue.length; i++) {
      tfValue[i] = new TextField();
      tfValue[i].setPrefColumnCount(2);
      tfValue[i].setAlignment(Pos.CENTER);
      tfValue[i].setFont(Font.font("Times", 25));
    }

    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(
            tfValue[0], tfValue[1], tfValue[2], tfValue[3]);

    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(hBoxTop, hBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 370, 160);
    primaryStage.setTitle("Exercise20_17"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btSolve.setOnAction(e -> tfSolution.setText(findSolution()));
  }

  /**
   * Evaluate an expression
   */
  public double evaluateExpression(String expression) {
    // Create operandStack to store operands
    Stack<Double> operandStack = new Stack<>();

    // Create operatorStack to store operators
    Stack<Character> operatorStack = new Stack<>();

    // Extract operands and operators
    java.util.StringTokenizer tokens = new java.util.StringTokenizer(
            expression, "()+-/*", true);

    // Phase 1: Scan tokens
    while (tokens.hasMoreTokens()) {
      String token = tokens.nextToken().trim(); // Extract a token
      if (token.length() == 0)  { // Blank space
        continue; // Back to the while loop to extract the next token
      } else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
        // Process all +, -, *, / in the top of the operator stack
        while (!operatorStack.isEmpty()
                && (operatorStack.peek() == '+' || operatorStack.peek() == '-'
                || operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
          processAnOperator(operandStack, operatorStack);
        }

        // Push the + or - operator into the operator stack
        operatorStack.push(token.charAt(0));
      } 
      else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
        // Process all *, / in the top of the operator stack
        while (!operatorStack.isEmpty()
                && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
          processAnOperator(operandStack, operatorStack);
        }

        // Push the * or / operator into the operator stack
        operatorStack.push(token.charAt(0));
      } 
      else if (token.trim().charAt(0) == '(') {
        operatorStack.push('('); // Push '(' to stack
      } 
      else if (token.trim().charAt(0) == ')') {
        // Process all the operators in the stack until seeing '('
        while (operatorStack.peek() != '(') {
          processAnOperator(operandStack, operatorStack);
        }

        operatorStack.pop(); // Pop the '(' symbol from the stack
      } 
      else { // An operand scanned
        // Push an operand to the stack
        operandStack.push(new Double(token));
      }
    }

    // Phase 2: process all the remaining operators in the stack
    while (!operatorStack.isEmpty()) {
      processAnOperator(operandStack, operatorStack);
    }

    // Return the result
    return operandStack.pop();
  }

  /**
   * Process one operator: Take an operator from operatorStack and apply it on
   * the operands in the operandStack
   */
  public void processAnOperator(Stack<Double> operandStack,
          Stack<Character> operatorStack) {
    if (operatorStack.peek().equals('+')) {
      operatorStack.pop();
      double op1 = ((Double) (operandStack.pop())).doubleValue();
      double op2 = ((Double) (operandStack.pop())).doubleValue();
      operandStack.push(new Double(op2 + op1));
    } 
    else if (operatorStack.peek().equals('-')) {
      operatorStack.pop();
      double op1 = ((Double) (operandStack.pop())).doubleValue();
      double op2 = ((Double) (operandStack.pop())).doubleValue();
      operandStack.push(new Double(op2 - op1));
    }
    else if (operatorStack.peek().equals('*')) {
      operatorStack.pop();
      double op1 = ((Double) (operandStack.pop())).doubleValue();
      double op2 = ((Double) (operandStack.pop())).doubleValue();
      operandStack.push(new Double(op2 * op1));
    } 
    else if (operatorStack.peek().equals('/')) {
      operatorStack.pop();
      double op1 = ((Double) (operandStack.pop())).doubleValue();
      double op2 = ((Double) (operandStack.pop())).doubleValue();
      operandStack.push(new Double(op2 / op1));
    }
  }

  /* Get four card values and find a solution */
  public String findSolution() {
    int a = Integer.parseInt(tfValue[0].getText());
    int b = Integer.parseInt(tfValue[1].getText());
    int c = Integer.parseInt(tfValue[2].getText());
    int d = Integer.parseInt(tfValue[3].getText());
    return findSolution(a, b, c, d);
  }

  /* Find a solution for four cards */
  public String findSolution(int a, int b, int c, int d) {
    String noSolution = "No solution";
    String solution;
    String[] operators = {"+", "-", "*", "/"};
    int[][] allCombinations = {
      {a, b, c, d}, {d, a, b, c},
      {c, d, a, b}, {b, c, d, a}, {a, b, d, c}, {c, a, b, d},
      {d, c, a, b}, {b, d, c, a}, {a, d, c, b}, {b, a, d, c},
      {c, b, a, d}, {d, c, b, a}, {a, c, b, d}, {d, a, c, b},
      {b, d, a, c}, {c, b, d, a}, {b, a, c, d}, {d, b, a, c},
      {c, d, b, a}, {a, c, d, b}, {a, d, b, c}, {c, a, d, b},
      {b, c, a, d}, {d, b, c, a}};

    for (String firstOp : operators) {
      for (String secondOp : operators) {
        for (String thirdOp : operators) {
          for (int[] cardNums : allCombinations) {
            for (int i = 0; i < 3; i++) {
              for (int j = 0; j < 5; j++) {
                if (i == 0) {
                  if (j == 0) { // without any parentheses
                    // Create an expression in the form a firstOp b secondOp c thirdOp d
                    solution = cardNums[0] + firstOp
                            + cardNums[1] + secondOp
                            + cardNums[2] + thirdOp
                            + cardNums[3];
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  } 
                  else if (j == 1) {
                    // Create an expression in the form (a firstOp b) secondOp c thirdOp d
                    solution = "(" + cardNums[0] + firstOp
                            + cardNums[1] + ")" + secondOp
                            + cardNums[2] + thirdOp
                            + cardNums[3];
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  } 
                  else if (j == 2) {
                    // Create an expression in the form a firstOp (b secondOp c) thirdOp d
                    solution = cardNums[0] + firstOp + "("
                            + cardNums[1] + secondOp
                            + cardNums[2] + ")" + thirdOp
                            + cardNums[3];
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  } 
                  else if (j == 3) {
                    // Create an expression in the form a firstOp b secondOp (c thirdOp d)
                    solution = cardNums[0] + firstOp
                            + cardNums[1] + secondOp + "("
                            + cardNums[2] + thirdOp
                            + cardNums[3] + ")";
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  } 
                  else if (j == 4) {
                    // Create an expression in the form (a firstOp b) secondOp (c thirdOp d)
                    solution = "(" + cardNums[0] + firstOp
                            + cardNums[1] + ")" + secondOp
                            + "(" + cardNums[2] + thirdOp
                            + cardNums[3] + ")";
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  }
                } 
                else if (i == 1) {
                  if (j == 0) {
                    solution = "(" + cardNums[0] + firstOp
                            + cardNums[1] + secondOp
                            + cardNums[2] + ")" + thirdOp
                            + cardNums[3];
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  } 
                  else if (j == 1) {
                    solution = "((" + cardNums[0] + firstOp
                            + cardNums[1] + ")" + secondOp
                            + cardNums[2] + ")" + thirdOp
                            + cardNums[3];
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  } 
                  else if (j == 2) {
                    solution = "(" + cardNums[0] + firstOp
                            + "(" + cardNums[1] + secondOp
                            + cardNums[2] + "))" + thirdOp
                            + cardNums[3];
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  }
                } 
                else if (i == 2) {
                  if (j == 0) {
                    solution = cardNums[0] + firstOp + "("
                            + cardNums[1] + secondOp
                            + cardNums[2] + thirdOp
                            + cardNums[3] + ")";
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  } 
                  else if (j == 1) {
                    solution = cardNums[0] + firstOp + "(("
                            + cardNums[1] + secondOp
                            + cardNums[2] + ")" + thirdOp
                            + cardNums[3] + ")";
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  } 
                  else if (j == 2) {
                    solution = cardNums[0] + firstOp + "("
                            + cardNums[1] + secondOp + "("
                            + cardNums[2] + thirdOp
                            + cardNums[3] + "))";
                    if (evaluateExpression(solution) == 24) {
                      return solution;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    return noSolution;
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
