import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.geometry.*;
import java.util.*;
import javafx.scene.media.*;
import java.net.*;
import java.io.*;

public class Exercise16_07Extra extends Application {
  private TextField tfURL = new TextField();
  private TextArea ta = new TextArea();
  private Label lblStatus = new Label();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    HBox hBox = new HBox(5);
    tfURL.setPrefColumnCount(40);
    Button btView = new Button("View");
    hBox.getChildren().addAll(new Label("Filename"), tfURL, btView);
        
    BorderPane pane = new BorderPane();
    pane.setTop(hBox);
    pane.setCenter(ta);
    pane.setBottom(lblStatus);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 650, 250);
    primaryStage.setTitle("Exercise16_07"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btView.setOnAction(e -> showFile());
  }
   
  private void showFile() {    
    try (java.util.Scanner input = 
        new java.util.Scanner(new URL(tfURL.getText().trim()).openStream())) {
      // Read a line and append the line to the text area
      while (input.hasNext()) {
        ta.appendText(input.nextLine() + "\n");
      }

      lblStatus.setText("File loaded successfully");
    }
    catch (MalformedURLException ex) {
      lblStatus.setText("URL " + tfURL.getText() + " not found.");
    }
    catch (IOException e) {
      lblStatus.setText(e.getMessage());
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
