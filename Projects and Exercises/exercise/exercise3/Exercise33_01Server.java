// Exercise33_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 200, 200);
    primaryStage.setTitle("Exercise33_01Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Thread(() -> connectToClient()).start();
  }
    
  
  public void connectToClient() {
    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      Platform.runLater(() ->
        ta.appendText("Exercise33_01Server started at " + new Date() + '\n'));

      Socket connectToClient = serverSocket.accept();

      // Display the client number
      Platform.runLater( () -> {ta.appendText("Connected to a client " +
        " at " + new Date() + '\n');});

      // Create data input and output streams
      DataInputStream isFromClient = new DataInputStream(
        connectToClient.getInputStream());
      DataOutputStream osToClient = new DataOutputStream(
        connectToClient.getOutputStream());

      // Continuously serve the client
      while (true) {
        // Receive annual interest rate from the client
        double annualInterestRate = isFromClient.readDouble();

        // Receive number of years f6hhurom the client
        int numOfYears = isFromClient.readInt();

        // Receive loan from the client
        double loanAmount = isFromClient.readDouble();

        // Compute monthly payment and total payment
        Loan mortgage = new Loan(
          annualInterestRate, numOfYears, loanAmount);
        double monthlyPayment = mortgage.getMonthlyPayment();
        double totalPayment = mortgage.getTotalPayment();

        // Send results back to the client
        osToClient.writeDouble(monthlyPayment);
        osToClient.writeDouble(totalPayment);

        Platform.runLater( () -> {
        ta.appendText("Annual Interest Rate: " + annualInterestRate +
          "\nNumber of Years: " + numOfYears + "\nLoan Amount: " +
          loanAmount + "\n");
        ta.appendText("monthlyPayment: " + monthlyPayment + " " +
          "\ntotalPayment: " + totalPayment + '\n');
        });
      }
    }
    catch(IOException e) {
      System.err.println(e);
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
