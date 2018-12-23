import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise31_21 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    TabPane tabPane = new TabPane();
    Tab tabInteger = new Tab("Integer Operations");
    Tab tabRational = new Tab("Rational Operations");
    tabInteger.setContent(new IntegerCalculator());
    tabRational.setContent(new RationalCalculator());
    tabPane.getTabs().addAll(tabInteger, tabRational);

    Scene scene = new Scene(tabPane, 300, 250);
    primaryStage.setTitle("Exercise31_21"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
  }

  private class RationalCalculator extends IntegerCalculator {
    public RationalCalculator() {
      setTitle("Rational Calculation");
    }

    Rational getNum1() {
      StringTokenizer st1 = new StringTokenizer(tfNumber1.getText().trim(), "/");
      int numer1 = Integer.parseInt(st1.nextToken());
      int denom1 = Integer.parseInt(st1.nextToken());
      return new Rational(numer1, denom1);
    }

    Rational getNum2() {
      StringTokenizer st2 = new StringTokenizer(tfNumber2.getText().trim(), "/");
      int numer2 = Integer.parseInt(st2.nextToken());
      int denom2 = Integer.parseInt(st2.nextToken());
      return new Rational(numer2, denom2);
    }

    public void perform(char operator) {

      Rational result = new Rational();
      switch (operator) {
        case '+':
          result = getNum1().add(getNum2());
          break;
        case '-':
          result = getNum1().subtract(getNum2());
          break;
        case '*':
          result = getNum1().multiply(getNum2());
          break;
        case '/':
          result = getNum1().divide(getNum2());
          break;
      }

      tfResult.setText(result + "");
    }
  }

  private class IntegerCalculator extends VBox {

    TextField tfNumber1 = new TextField();
    TextField tfNumber2 = new TextField();
    TextField tfResult = new TextField();
    private Label lblTitle = new Label("Integer Calculation");

    public void setTitle(String title) {
      lblTitle.setText(title);
    }

    public IntegerCalculator() {
      HBox hBox1 = new HBox(5);
      tfNumber1.setPrefColumnCount(2);
      tfNumber2.setPrefColumnCount(2);
      tfResult.setPrefColumnCount(2);
      hBox1.getChildren().addAll(new Label("Number 1:"), tfNumber1,
              new Label("Number 2:"), tfNumber2, new Label("Result:"),
              tfResult);
      hBox1.setAlignment(Pos.CENTER);

      HBox hBox2 = new HBox(5);
      Button btAdd = new Button("Add");
      Button btSubtract = new Button("Subtract");
      Button btMultiply = new Button("Multiply");
      Button btDivide = new Button("Divide");
      hBox2.getChildren().addAll(btAdd, btSubtract, btMultiply, btDivide);
      hBox2.setAlignment(Pos.CENTER);

      this.setSpacing(10);
      this.getChildren().addAll(lblTitle, hBox1, hBox2);
      // Handle button actions
      btAdd.setOnAction(e -> perform('+'));
      btSubtract.setOnAction(e -> perform('-'));
      btMultiply.setOnAction(e -> perform('*'));
      btDivide.setOnAction(e -> perform('/'));
    }

    public void perform(char operator) {
      double number1 = Double.parseDouble(tfNumber1.getText());
      double number2 = Double.parseDouble(tfNumber2.getText());

      double result = 0;
      switch (operator) {
        case '+':
          result = number1 + number2;
          break;
        case '-':
          result = number1 - number2;
          break;
        case '*':
          result = number1 * number2;
          break;
        case '/':
          result = number1 / number2;
          break;
      }

      tfResult.setText(result + "");
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
