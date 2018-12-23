import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_19 extends Application {  
  private int count;
  private long startTime = System.currentTimeMillis();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new Pane();
    Circle circle = new Circle(40, 40, 10);
    pane.getChildren().add(circle);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_19"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    circle.setOnMouseClicked(e -> { 
      if (circle.contains(e.getX(), e.getY())) {
        count++;
        if (count == 20) {
          pane.getChildren().clear();
          pane.getChildren().add(new Text(20, 20, "Time spent is "
            + (System.currentTimeMillis() - startTime) + " milliseconds"));
          return;
        }
        circle.setFill(new Color(Math.random(), Math.random(), 
            Math.random(), Math.random()));
        circle.setCenterX(Math.random() * scene.getWidth());
        circle.setCenterY(Math.random() * scene.getHeight());
      }
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
