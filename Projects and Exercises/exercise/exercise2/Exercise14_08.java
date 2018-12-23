import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise14_08 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {    
    double WIDTH = 500;
    double HEIGHT = 500;
    
    GridPane pane = new GridPane();
        
    for (int i = 0; i < 10; i++) { 
      for (int j = 0; j < 10; j++) {
        TextField tf = new TextField((int)(Math.random() + 0.5) + "");  
        tf.setPrefColumnCount(1);
        tf.setAlignment(Pos.CENTER);
        // Set a large preferred width and height to occupy the entire grid pane
        tf.setPrefWidth(400); 
        tf.setPrefHeight(400);
        tf.setAlignment(Pos.CENTER);
        pane.add(tf, j, i);
      }
    }
        
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, WIDTH, HEIGHT);
    primaryStage.setTitle("Exercise14_08"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
