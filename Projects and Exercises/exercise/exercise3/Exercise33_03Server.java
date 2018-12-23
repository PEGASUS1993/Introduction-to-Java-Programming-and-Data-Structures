// Exercise33_03Server.java: The server can communicate with
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

public class Exercise33_03Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 200, 200);
    ta.setText("RERER");
    primaryStage.setTitle("Exercise33_03Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Thread(() -> connectToClient()).start();
  }
    
  // Number a client
  int clientNo = 1;
  
  public void connectToClient() {
    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      ta.appendText("Exercise33_03Server started at " + new Date() + '\n');

      while (true) {
        // Listen for a new connection request
        Socket connectToClient = serverSocket.accept();

        final int number = clientNo;
        // Display the client number
        Platform.runLater( () -> {ta.appendText("Starting thread for client " + number +
          " at " + new Date() + '\n');});

        // Find the client's host name, and IP address
        InetAddress clientInetAddress =
          connectToClient.getInetAddress();
        Platform.runLater( () -> {
          ta.appendText("Client " + number + "'s host name is "
            + clientInetAddress.getHostName() + "\n");
          ta.appendText("Client " + number + "'s IP Address is "
            + clientInetAddress.getHostAddress() + "\n");
        });

        // Create a new thread for the connection
        HandleAClient thread = new HandleAClient(connectToClient);

        // Start the new thread
        thread.start();

        // Increment clientNo
        clientNo++;
      }
    }
    catch(IOException ex) {
      System.err.println(ex);
    }
  }

  // Inner class
  // Define the thread class for handling new connection
  class HandleAClient extends Thread {
    private Socket connectToClient; // A connected socket

    /** Construct a thread */
    public HandleAClient(Socket socket) {
      connectToClient = socket;
    }

    /** Run a thread */
    public void run() {
      try {
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
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
