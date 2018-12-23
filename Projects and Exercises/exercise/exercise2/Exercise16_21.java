import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_21 extends Application {
  TextField tfSeconds = new TextField();
  
  Timeline animation = new Timeline(
          new KeyFrame(Duration.millis(1000), e -> {
            int seconds = Integer.parseInt(tfSeconds.getText());
            tfSeconds.setText(seconds - 1 + "");
            if (seconds - 1 <= 0) {
              stopAnimation();
            }
          }));

  Media media = new Media("http://www.cs.armstrong.edu/liang/common/audio/anthem/anthem0.mp3");
  MediaPlayer mp = new MediaPlayer(media);

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    mp.setCycleCount(Timeline.INDEFINITE);

    animation.setCycleCount(Timeline.INDEFINITE);

    tfSeconds.setAlignment(Pos.CENTER);
    tfSeconds.setOnAction(e -> {
      animation.play();
    });

    tfSeconds.setFont(Font.font("Times", 35));

    // Create a scene and place it in the stage
    Scene scene = new Scene(tfSeconds, 200, 100);
    primaryStage.setTitle("Exercise16_21"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  private void stopAnimation() {
    animation.stop();

    mp.play();
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
