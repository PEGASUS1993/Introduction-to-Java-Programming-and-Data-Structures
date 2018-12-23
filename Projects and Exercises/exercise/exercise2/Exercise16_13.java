import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise16_13 extends Application {
  private double paneWidth = 500;
  private double paneHeight = 250;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
    TextArea taTable = new TextArea();
    pane.setCenter(new ScrollPane(taTable));
    
    TextField tfLoanAmount = new TextField();
    tfLoanAmount.setPrefColumnCount(7);
    TextField tfNumberOfYears = new TextField();
    tfNumberOfYears.setPrefColumnCount(2);
    Button btShowTable = new Button("Show Table");
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(new Label("Loan Amount"),
      tfLoanAmount, new Label("Number of Years"),
      tfNumberOfYears, btShowTable);
    hBox.setAlignment(Pos.CENTER);
    pane.setTop(hBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_13"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btShowTable.setOnAction(e -> {
      double loanAmount = Double.parseDouble(
        tfLoanAmount.getText().trim());
      int numOfYears = Integer.parseInt(
        tfNumberOfYears.getText().trim());
      Loan loan = new Loan();
      loan.setLoanAmount(loanAmount);
      loan.setNumberOfYears(numOfYears);

      taTable.setText("Interest Rate\tMonthly Payment\tTotal Payment\n");

      for (double rate = 5; rate <= 8; rate += 1 / 8.0) {
        loan.setAnnualInterestRate(rate);
        taTable.appendText(rate + "\t\t\t" +
          (int)(loan.getMonthlyPayment() * 100) / 100.0 +
          "\t\t\t" + (int)(loan.getTotalPayment() * 100) / 100.0 + "\n");
      }
    });
  }
  


  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
