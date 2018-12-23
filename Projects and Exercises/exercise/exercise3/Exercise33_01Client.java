// Exercise33_01Client.java: The client sends the input to the server and receives
// result back from the server
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise33_01Client extends Application {
  // Text field for receiving radius
  private TextField tfAnnualInterestRate = new TextField();
  private TextField tfNumOfYears = new TextField();
  private TextField tfLoanAmount = new TextField();
  private Button btSubmit= new Button("Submit");

  // Text area to display contents
  private TextArea ta = new TextArea();

  // IO streams
  DataOutputStream osToServer;
  DataInputStream isFromServer;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Annual Interest Rate"), 0, 0);
    gridPane.add(new Label("Number Of Years"), 0, 1);
    gridPane.add(new Label("Loan Amount"), 0, 2);
    gridPane.add(tfAnnualInterestRate, 1, 0);
    gridPane.add(tfNumOfYears, 1, 1);
    gridPane.add(tfLoanAmount, 1, 2);
    gridPane.add(btSubmit, 2, 1);
    
    tfAnnualInterestRate.setAlignment(Pos.BASELINE_RIGHT);
    tfNumOfYears.setAlignment(Pos.BASELINE_RIGHT);
    tfLoanAmount.setAlignment(Pos.BASELINE_RIGHT);
    
    tfLoanAmount.setPrefColumnCount(5);
    tfNumOfYears.setPrefColumnCount(5);
    tfLoanAmount.setPrefColumnCount(5);
            
    BorderPane pane = new BorderPane();
    pane.setCenter(new ScrollPane(ta));
    pane.setTop(gridPane);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("Exercise33_01Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btSubmit.setOnAction(e -> connectToServer());
    
    try {
      // Create a socket to connect to the server
      Socket connectToServer = new Socket("localhost", 8000);
      //Socket connectToServer = new Socket("130.254.204.36", 8000);
      //Socket connectToServer = new Socket(
      //  "drake.Armstrong.edu", 8000);

      // Create an input stream to receive data from the server
      isFromServer = new DataInputStream(
        connectToServer.getInputStream());

      // Create an output stream to send data to the server
      osToServer =
        new DataOutputStream(connectToServer.getOutputStream());
    }
    catch (IOException ex) {
      ta.appendText(ex.toString() + '\n');
    }
  }
    
  public void connectToServer() {
    try {
      // Get the annual interest rate from the text field
      double annualInterestRate =
        Double.parseDouble(tfAnnualInterestRate.getText().trim());

      // Get the number of years from the text field
      int numOfYears =
        Integer.parseInt(tfNumOfYears.getText());

      // Get the loan amount from the text field
      double loanAmount =
        Double.parseDouble(tfLoanAmount.getText().trim());

      // Send the annual interest rate to the server
      osToServer.writeDouble(annualInterestRate);

      // Send the number of years to the server
      osToServer.writeInt(numOfYears);

      // Send the loan amount to the server
      osToServer.writeDouble(loanAmount);

      osToServer.flush();

      // Get monthly payment from the server
      double monthlyPayment = isFromServer.readDouble();

      // Get total payment from the server
      double totalPayment = isFromServer.readDouble();

      ta.appendText("Annual Interest Rate: " + annualInterestRate +
        "\nNumber of Years: " + numOfYears + "\nLoan Amount: " +
        loanAmount + "\n");
      ta.appendText("monthlyPayment: " + monthlyPayment + " " +
        "\ntotalPayment: " + totalPayment + '\n');
    }
    catch (IOException ex) {
      System.err.println(ex);
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
