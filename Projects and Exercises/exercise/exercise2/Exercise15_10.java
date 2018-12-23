import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_10 extends Application {
  StringBuilder builder = new StringBuilder();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    StackPane pane = new StackPane();
    Text text = new Text("To be replaced");
    pane.getChildren().add(text);
    
    pane.setFocusTraversable(true);
    pane.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        text.setText(builder.toString());
      }
      else {
        builder.append(e.getText());
      }
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_10"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    pane.requestFocus();
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
