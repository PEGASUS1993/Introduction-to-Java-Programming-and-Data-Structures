import java.text.NumberFormat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise36_05 extends Application {
  private TextField tfLoanAmount = new TextField();
  private TextField tfNumOfYears = new TextField();
  private TextField tfAnnualInterestRate = new TextField();
  private Button btLoanSchedule 
    = new Button("Display Loan Schedule");
  private TextArea taLoanSchedule = new TextArea();
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Loan Amount"), 0, 0);
    gridPane.add(new Label("Number of Years"), 0, 1);
    gridPane.add(new Label("Annual Interest Rate"), 0, 2);
    gridPane.add(tfLoanAmount, 1, 0);
    gridPane.add(tfNumOfYears, 1, 1);
    gridPane.add(tfAnnualInterestRate, 1, 2);
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(new Label
      ("Enter Loan Amount, Number of Years, and Annual Interest Rate"), 
       gridPane);
    
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(vBox, btLoanSchedule);
    
    BorderPane pane = new BorderPane();
    pane.setTop(hBox);
    pane.setCenter(taLoanSchedule);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 200);
    primaryStage.setTitle("Exercise36_05"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btLoanSchedule.setOnAction(e -> process());
  }

  private void process() {
    // Obtain loan amount, annual interest rate, and number of years
    double loanAmount = Double.parseDouble(
    tfLoanAmount.getText().trim());
    double annualInterestRate = Double.parseDouble(
    tfAnnualInterestRate.getText().trim());
    int numOfYears = Integer.parseInt(
    tfNumOfYears.getText().trim());

    // Obtain monthly interest rate
    double monthlyInterestRate = annualInterestRate/1200;

    // Compute mortgage
    double monthlyPayment = loanAmount*monthlyInterestRate/
      (1-(Math.pow(1/(1+monthlyInterestRate), numOfYears*12)));

    double balance = loanAmount;
    double interest;
    double principal;
    NumberFormat currencyForm =
      NumberFormat.getCurrencyInstance();

    taLoanSchedule.setText("Monthly Payment: " +
      currencyForm.format(monthlyPayment) + "\n");
    taLoanSchedule.appendText(
      "Total Payment: " +
       currencyForm.format(monthlyPayment * 12 * numOfYears) + "\n\n");
    taLoanSchedule.appendText(
      "Payment#\tInterest\tPrincipal\tBalance\n");
    int i;
    for (i = 1; i <= numOfYears * 12; i++) {
      interest = monthlyInterestRate * balance;
      principal = monthlyPayment - interest;
      balance -= principal;
      taLoanSchedule.appendText(i + "\t" + currencyForm.format(interest)
        + "\t" + currencyForm.format(principal) + "\t" +
        currencyForm.format(balance) + "\n");
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