// Exercise33_04Client.java: The client sends the input to the server and receives
// result back from the server
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Exercise33_04Client extends Application {
  private Label lbl = new Label("You are visitor number ");
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    StackPane pane = new StackPane();
    pane.getChildren().add(lbl);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("Exercise33_04Client"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    try {
      // Establish connection with the server
      // Socket connectToServer = new Socket("liangy.ipfw.edu", 8000);
      Socket connectToServer = new Socket("localhost", 8000);

      BufferedReader isFromServer =
        new BufferedReader(
          new InputStreamReader(connectToServer.getInputStream()));

      String count = isFromServer.readLine();

      lbl.setText("You are visitor " + count);
    }
    catch (IOException ex) {
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
