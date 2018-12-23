import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise20_13 extends Application {
  private ArrayList<Integer> list = new ArrayList<>();
  private ImageView imageView1 = new ImageView();
  private ImageView imageView2 = new ImageView();
  private ImageView imageView3 = new ImageView();
  private ImageView imageView4 = new ImageView();
  private TextField tfExpression = new TextField();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {  
    for (int i = 1; i <= 52; i++) {
      list.add(i);
    }
 
    VBox vBox = new VBox(5);
    
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 1; i <= 52; i++) {
      list.add(i);
    }
    
    refresh();
    
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(imageView1, imageView2, imageView3, imageView4);
   
    Label lblStatus = new Label();
    Button btShuffle = new Button("Shuffle");
    HBox hBoxTop = new HBox(5);
    hBoxTop.setAlignment(Pos.CENTER_RIGHT);
    hBoxTop.getChildren().addAll(lblStatus, btShuffle);
    
    HBox hBoxBottom = new HBox(5);
    hBoxBottom.setAlignment(Pos.CENTER);
    Button btVerify = new Button("Verify");
    hBoxBottom.getChildren().addAll(
     new Label("Enter an expression: "), tfExpression, btVerify);

    vBox.getChildren().addAll(hBoxTop, hBox, hBoxBottom);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 370, 160);
    primaryStage.setTitle("Exercise20_13"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btShuffle.setOnAction(e -> refresh());
    
    btVerify.setOnAction(e -> {
        if (!correctNumbers()) {
          lblStatus.setText("The numbers in the expression don't \nmatch the numbers in the set ");
        } else {
          // Check whether the expression evaluates to 24.
          if (evaluate()) {
            lblStatus.setText("Correct");
          } else {
            lblStatus.setText("Incorrect result");
          }
        }
    });
  }

  private void refresh() {
    java.util.Collections.shuffle(list);
    imageView1.setImage(new Image("image/card/" + list.get(0) + ".png"));
    imageView2.setImage(new Image("image/card/" + list.get(1) + ".png"));
    imageView3.setImage(new Image("image/card/" + list.get(2) + ".png"));
    imageView4.setImage(new Image("image/card/" + list.get(3) + ".png"));
  }
  
  private boolean evaluate() {
    return EvaluateExpression
        .evaluateExpression(tfExpression.getText().trim()) == 24;
  }
    
  private boolean correctNumbers() {
    // Get the card values from the expression
    String[] values = tfExpression.getText().trim().split("[()+-/* ]");
    //String[] values = jtfExpression.getText().trim().split("[+|-|\\*|/| |(|)]");
    ArrayList<Integer> valueList = new ArrayList<>();
    
    ArrayList<Integer> currentCardValues = new ArrayList<>();
    currentCardValues.add((list.get(0) - 1) % 13 + 1);
    currentCardValues.add((list.get(1) - 1) % 13 + 1);
    currentCardValues.add((list.get(2) - 1) % 13 + 1);
    currentCardValues.add((list.get(3) - 1) % 13 + 1);

    for (int i = 0; i < values.length; i++)
      if (values[i].length() > 0)
        valueList.add(Integer.parseInt(values[i]));

    Collections.sort(valueList);
    Collections.sort(currentCardValues);

    if (valueList.equals(currentCardValues))
      return true;
    else
      return false;
  }

  private static class EvaluateExpression {
    /** Evaluate an expression */
    public static int evaluateExpression(String expression) {
      // Create operandStack to store operands
      Stack<Integer> operandStack = new Stack<>();

      // Create operatorStack to store operators
      Stack<Character> operatorStack = new Stack<>();

      // Extract operands and operators
      java.util.StringTokenizer tokens = new java.util.StringTokenizer(
          expression, "()+-/*", true);

      // Phase 1: Scan tokens
      while (tokens.hasMoreTokens()) {
        String token = tokens.nextToken().trim(); // Extract a token
        if (token.length() == 0) // Blank space
          continue; // Back to the while loop to extract the next token
        else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
          // Process all +, -, *, / in the top of the operator stack
          while (!operatorStack.isEmpty()
              && (operatorStack.peek() == '+' || operatorStack.peek() == '-'
                  || operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
            processAnOperator(operandStack, operatorStack);
          }

          // Push the + or - operator into the operator stack
          operatorStack.push(token.charAt(0));
        } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
          // Process all *, / in the top of the operator stack
          while (!operatorStack.isEmpty()
              && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
            processAnOperator(operandStack, operatorStack);
          }

          // Push the * or / operator into the operator stack
          operatorStack.push(token.charAt(0));
        } else if (token.trim().charAt(0) == '(') {
          operatorStack.push('('); // Push '(' to stack
        } else if (token.trim().charAt(0) == ')') {
          // Process all the operators in the stack until seeing '('
          while (operatorStack.peek() != '(') {
            processAnOperator(operandStack, operatorStack);
          }

          operatorStack.pop(); // Pop the '(' symbol from the stack
        } else { // An operand scanned
          // Push an operand to the stack
          operandStack.push(new Integer(token));
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
     * Process one opeator: Take an operator from operatorStack and apply it on
     * the operands in the operandStack
     */
    public static void processAnOperator(Stack<Integer> operandStack,
        Stack<Character> operatorStack) {
      char op = operatorStack.pop();
      int op1 = operandStack.pop();
      int op2 = operandStack.pop();
      if (op == '+')
        operandStack.push(op2 + op1);
      else if (op == '-')
        operandStack.push(op2 - op1);
      else if (op == '*')
        operandStack.push(op2 * op1);
      else if (op == '/')
        operandStack.push(op2 / op1);
    }
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
