import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise15_07 extends Application {  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create UI
    StackPane pane = new StackPane();
    Circle circle = new Circle(50);
    pane.getChildren().add(circle);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    
    circle.setOnMousePressed(e -> circle.setFill(Color.BLACK));
    circle.setOnMouseReleased(e -> circle.setFill(Color.WHITE));
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_07"); // Set title
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
