import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;

public class Exercise32_03 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a pane 
    Pane pane = new Pane();
    
    // Add an image view and add it to pane
    ImageView imageView = new ImageView("image/us.gif");
    pane.getChildren().add(imageView);
    
    imageView.setX(40);
    imageView.setY(400);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 250, 400);
    primaryStage.setTitle("Exercise32_03"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Thread(() -> {
      try {
        while (true) {
          Platform.runLater(() -> imageView.setY(imageView.getY() - 1));
          Thread.sleep(200);
        }
      }
      catch (InterruptedException ex) {
      }
    }).start();
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
