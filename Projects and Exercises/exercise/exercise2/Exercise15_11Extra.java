import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Exercise15_11Extra extends Application {
  private double delta = -1;

  @Override
  // Override the start method in the Application class
  public void start(Stage primaryStage) {  
    ImageView imageView = new ImageView("image/us.gif");  
    StackPane pane = new StackPane();
    pane.getChildren().add(imageView);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 250, 150);
    primaryStage.setTitle("Exercise15_11"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
   
    imageView.setFitWidth(300);
    imageView.setFitHeight(300);

    EventHandler<ActionEvent> handler = e -> {
      if (imageView.getFitWidth() == 50) {
        delta = 1;
      }
      else if (imageView.getFitWidth() == 300) {
        delta = -1;
      }
      imageView.setFitWidth(imageView.getFitWidth() + delta);
      imageView.setFitHeight(imageView.getFitHeight() + delta);
    };
    
    Timeline animation = new Timeline(
        new KeyFrame(Duration.millis(50), handler));
      animation.setCycleCount(Timeline.INDEFINITE);
      animation.play(); // Start animation
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
