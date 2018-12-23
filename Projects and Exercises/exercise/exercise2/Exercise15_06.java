import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_06 extends Application {  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create UI
    StackPane pane = new StackPane();
    Text text = new Text("Java is fun");
    pane.getChildren().add(text);
    
    pane.setOnMouseClicked(e -> {
      if (text.getText().equals("Java is fun")) {
        text.setText("Java is powerful");
      }
      else {
        text.setText("Java is fun");
      }
    });

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_06"); // Set title
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
