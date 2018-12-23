import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_02Server extends Application {
  // Text area for displaying contents
  private TextArea ta = new TextArea();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ta.setWrapText(true);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new ScrollPane(ta), 200, 200);
    primaryStage.setTitle("Exercise33_02Server"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Thread(() -> connectToClient()).start();
  }  
  
  public void connectToClient() {
    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      Platform.runLater(() ->
        ta.appendText("Exercise33_02Server started at " + new Date() + '\n'));

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
        // Receive weight from the client
        double weight = isFromClient.readDouble();

        // Receive height from the client
        double height = isFromClient.readDouble();

        // Compute BMI
        BMI bmi = new BMI("", weight, height);
        String report = "BMI is " + bmi.getBMI() + ". " + bmi.getStatus();

        // Send results back to the client
        osToClient.writeUTF(report);

        Platform.runLater( () -> {
          ta.appendText("Weight: " + weight + "\nHeight: " + height + "\n");
          ta.appendText(report + '\n');
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
