import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_30 extends Application {
  private int index = 0;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {      
    Image[] image = new Image[25];;
    for (int i = 0; i < 25; i++) {
      image[i] = new Image("image/slide" + i + ".jpg");
    }
    
    ImageView imageView = new ImageView(image[0]);
    
    StackPane pane = new StackPane();
    pane.getChildren().add(imageView);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 800, 600);
    primaryStage.setTitle("Exercise15_30"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    Timeline animation = new Timeline(
      new KeyFrame(Duration.millis(2000), e -> {imageView.setImage(image[++index % 25]);}));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation
    
    pane.setOnMouseClicked(e -> {
      if (animation.getStatus() == Animation.Status.PAUSED) {
        animation.play();
      }
      else {
        animation.pause();
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
