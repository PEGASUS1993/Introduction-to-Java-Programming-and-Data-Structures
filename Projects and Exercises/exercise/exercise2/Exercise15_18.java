import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise15_18 extends Application {  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new Pane();
    Rectangle rectangle = new Rectangle(40, 40, 50, 60);
    rectangle.setFill(Color.WHITE);
    rectangle.setStroke(Color.BLACK);

    rectangle.setOnMouseDragged(e -> {
      if (rectangle.contains(e.getX(), e.getY())) {
        rectangle.setX(e.getX() - 50 / 2);
        rectangle.setY(e.getY() - 60 / 2);
      }
    });
    
    pane.getChildren().add(rectangle);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_16"); // Set the stage title
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
