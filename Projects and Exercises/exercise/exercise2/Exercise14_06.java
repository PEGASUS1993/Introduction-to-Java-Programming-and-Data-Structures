import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

public class Exercise14_06 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {    
    double WIDTH = 200;
    double HEIGHT = 200;
    
    Pane pane = new Pane();
        
    for (int i = 0; i < 8; i++) { 
      boolean isWhite = i % 2 == 0;

      for (int j = 0; j < 8; j++) {
        Rectangle rectangle = new Rectangle(i * WIDTH / 8, 
          j * HEIGHT / 8, WIDTH / 8, HEIGHT / 8);
        
        rectangle.setStroke(Color.BLACK);
        
        if (isWhite) {
          rectangle.setFill(Color.WHITE);
        }
        else {
          rectangle.setFill(Color.BLACK);          
        }
        
        isWhite = !isWhite;
        
        pane.getChildren().add(rectangle);
      }
    }

        
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, WIDTH, HEIGHT);
    primaryStage.setTitle("Exercise14_06"); // Set the stage title
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
