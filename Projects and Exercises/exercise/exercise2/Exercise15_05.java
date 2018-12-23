import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise15_05 extends Application {
  private TextField tfInvestmentAmount = new TextField();
  private TextField tfNumberOfYears = new TextField();
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfFutureValue = new TextField();
  private Button btCalculate = new Button("Calculate");
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create UI
    GridPane gridPane = new GridPane();
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.add(new Label("Investment Amount:"), 0, 0);
    gridPane.add(tfInvestmentAmount, 1, 0);
    gridPane.add(new Label("Number of Years:"), 0, 1);
    gridPane.add(tfNumberOfYears, 1, 1);
    gridPane.add(new Label("Annual Interest Rate:"), 0, 2);
    gridPane.add(tfAnnualInterestRate, 1, 2);
    gridPane.add(new Label("Future value:"), 0, 3);
    gridPane.add(tfFutureValue, 1, 3);
    gridPane.add(btCalculate, 1, 4);

    // Set properties for UI
    gridPane.setAlignment(Pos.CENTER);
    tfAnnualInterestRate.setAlignment(Pos.BOTTOM_RIGHT);
    tfNumberOfYears.setAlignment(Pos.BOTTOM_RIGHT);
    tfInvestmentAmount.setAlignment(Pos.BOTTOM_RIGHT);
    tfFutureValue.setAlignment(Pos.BOTTOM_RIGHT);
    tfFutureValue.setEditable(false);
    GridPane.setHalignment(btCalculate, HPos.RIGHT);

    // Process events
    btCalculate.setOnAction(e -> calculateLoanPayment());

    // Create a scene and place it in the stage
    Scene scene = new Scene(gridPane, 400, 250);
    primaryStage.setTitle("Exercise15_05"); // Set title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  private void calculateLoanPayment() {
    // Get values from text fields
    double annualInterestRate =
      Double.parseDouble(tfAnnualInterestRate.getText());
    int numberOfYears = Integer.parseInt(tfNumberOfYears.getText());
    double investmentAmount =
      Double.parseDouble(tfInvestmentAmount.getText());

    double monthlyInterestRate = annualInterestRate / 1200;
    double futureValue = investmentAmount * 
        Math.pow(1 + monthlyInterestRate, numberOfYears * 12);

    // Display monthly payment and total payment
    tfFutureValue.setText(String.format("$%.2f", futureValue));
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
