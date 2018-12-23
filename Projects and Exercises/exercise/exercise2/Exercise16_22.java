import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class Exercise16_22 extends Application {
  AudioClip audioClip = new AudioClip("http://www.cs.armstrong.edu/liang/common/audio/anthem/anthem2.mp3");

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Button btPlay = new Button("Play");
    Button btLoop = new Button("Loop");
    Button btStop = new Button("Stop");
    
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(btPlay, btLoop, btStop);
    
    btPlay.setOnAction(e -> {
      audioClip.setCycleCount(1);
      audioClip.play();
    });

    btLoop.setOnAction(e -> {
      audioClip.setCycleCount(Timeline.INDEFINITE);
      audioClip.play();
    });

    btStop.setOnAction(e -> {
      audioClip.stop();
    });
        
    // Create a scene and place it in the stage
    Scene scene = new Scene(hBox, 200, 100);
    primaryStage.setTitle("Exercise16_22"); // Set the stage title
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
