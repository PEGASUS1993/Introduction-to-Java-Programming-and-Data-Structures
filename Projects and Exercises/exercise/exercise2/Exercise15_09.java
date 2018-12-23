import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise15_09 extends Application {
  private double x = 100;
  private double y = 100;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new Pane();
    
    // pane.setFocusTraversable(true);
    pane.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.UP) {
        pane.getChildren().add(new Line(x, y, x, y - 10));
        y -= 10;
      }
      else if (e.getCode() == KeyCode.DOWN) {
        pane.getChildren().add(new Line(x, y, x, y + 10));
        y += 10;
      }
      else if (e.getCode() == KeyCode.LEFT) {
        pane.getChildren().add(new Line(x, y, x - 10, y));
        x -= 10;
      }
      else if (e.getCode() == KeyCode.RIGHT) {
        pane.getChildren().add(new Line(x, y, x + 10, y));
        x += 10;
      }
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_09"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    pane.requestFocus();
    x = pane.getWidth() / 2;
    y = pane.getHeight() / 2;
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
