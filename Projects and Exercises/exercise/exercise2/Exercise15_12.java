import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_12 extends Application {  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new Pane();
    Circle circle = new Circle(100, 60, 50);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    Text text = new Text();
    pane.getChildren().addAll(circle, text);
    
    pane.setOnMouseMoved(e -> { 
      if (circle.contains(e.getX(), e.getY())) {
        text.setText("Mouse point is inside the circle"); 
      }
      else {
        text.setText("Mouse point is outside the circle"); 
      }
      
      text.setX(e.getX());
      text.setY(e.getY());
    });

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_12"); // Set the stage title
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
