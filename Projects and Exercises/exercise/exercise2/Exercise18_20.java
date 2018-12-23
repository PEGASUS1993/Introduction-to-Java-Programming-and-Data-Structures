import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise18_20 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {               
    // Create a scene and place it in the stage
    Scene scene = new Scene(new CirclePane(), 200, 200);
    primaryStage.setTitle("Exercise18_20"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /** Pane for displaying triangles */
  static class CirclePane extends Pane {
    private double radius = 80;

    public CirclePane() {
      displayCircles();
    }
    
    private void displayCircles() {
      if (radius >= 5) {
        Circle c = new Circle(100, 100, radius);
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
        getChildren().add(c);
        radius -= 5;
        displayCircles();
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
