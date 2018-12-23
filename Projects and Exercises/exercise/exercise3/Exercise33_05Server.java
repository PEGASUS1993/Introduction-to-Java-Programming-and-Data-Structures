// Exercise33_05Server.java: The server can communicate with
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

public class Exercise33_05Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 200, 200);
    primaryStage.setTitle("Exercise33_05Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Thread(() -> connectToClient()).start();
  }
      
  public void connectToClient() {
    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      ta.appendText("Exercise33_05Server started at " + new Date() + '\n');

      Socket connectToClient = serverSocket.accept();

      // Display the client number
      Platform.runLater( () -> {ta.appendText("Connected to a client " +
        " at " + new Date() + '\n');});

      // Create data input and output streams
      ObjectInputStream isFromClient = new ObjectInputStream(
        connectToClient.getInputStream());
      DataOutputStream osToClient = new DataOutputStream(
        connectToClient.getOutputStream());

      // Continuously serve the client
      while (true) {
        Loan loan = (Loan)(isFromClient.readObject());

        double monthlyPayment = loan.getMonthlyPayment();
        double totalPayment = loan.getTotalPayment();

        // Send results back to the client
        osToClient.writeDouble(monthlyPayment);
        osToClient.writeDouble(totalPayment);

        Platform.runLater( () -> {
        ta.appendText("Annual Interest Rate: " + loan.getAnnualInterestRate() +
          "\nNumber of Years: " + loan.getNumberOfYears() + "\nLoan Amount: " +
          loan.getLoanAmount() + "\n");
        ta.appendText("monthlyPayment: " + loan.getMonthlyPayment() + " " +
          "\ntotalPayment: " + loan.getTotalPayment() + '\n');
        });
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
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
