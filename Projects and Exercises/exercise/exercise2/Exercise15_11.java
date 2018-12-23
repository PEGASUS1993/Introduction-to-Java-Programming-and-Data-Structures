import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise15_11 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new Pane();
    Circle circle = new Circle(50, 50, 10);
    pane.getChildren().add(circle);
    
    circle.setFocusTraversable(true);
    circle.setOnKeyPressed(e -> { 
      if (e.getCode() == KeyCode.UP) {
        circle.setCenterY(circle.getCenterY() - 2); 
      }
      else if (e.getCode() == KeyCode.DOWN) {
        circle.setCenterY(circle.getCenterY() + 2); 
      }
      else if (e.getCode() == KeyCode.LEFT) {
        circle.setCenterX(circle.getCenterX() - 2); 
      }
      if (e.getCode() == KeyCode.RIGHT) {
        circle.setCenterX(circle.getCenterX() + 2); 
      }
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_11"); // Set the stage title
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
