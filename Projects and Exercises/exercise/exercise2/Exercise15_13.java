import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_13 extends Application {  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new Pane();
    Rectangle rectangle = new Rectangle(100 - 50, 60 - 20, 100, 40);
    rectangle.setFill(Color.WHITE);
    rectangle.setStroke(Color.BLACK);
    Text text = new Text();
    pane.getChildren().addAll(rectangle, text);
    
    pane.setOnMouseMoved(e -> { 
      if (rectangle.contains(e.getX(), e.getY())) {
        text.setText("Mouse point is inside the rectangle"); 
      }
      else {
        text.setText("Mouse point is outside the rectangle"); 
      }
      
      text.setX(e.getX());
      text.setY(e.getY());
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_13"); // Set the stage title
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
