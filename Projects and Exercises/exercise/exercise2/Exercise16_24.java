import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Exercise16_24 extends Application {
  private static final String MEDIA_URL =
          "http://cs.armstrong.edu/liang/common/sample.mp4";

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Media media = new Media(MEDIA_URL);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    MediaView mediaView = new MediaView(mediaPlayer);

    Button playButton = new Button(">");
    playButton.setOnAction(e -> {
      if (playButton.getText().equals(">")) {
        mediaPlayer.play();
        playButton.setText("||");
      } else {
        mediaPlayer.pause();
        playButton.setText(">");
      }
    });

    Slider slTime = new Slider();

    Slider slVolume = new Slider();
    slVolume.setPrefWidth(150);
    slVolume.setMaxWidth(Region.USE_PREF_SIZE);
    slVolume.setMinWidth(30);
    slVolume.setValue(50);
    mediaPlayer.volumeProperty().bind(
            slVolume.valueProperty().divide(100));

    Label lblTime = new Label();
    
    HBox hBox = new HBox(10);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(playButton, new Label("Time"), 
      slTime, lblTime, new Label("Volume"), slVolume);

    BorderPane pane = new BorderPane();
    pane.setCenter(mediaView);
    pane.setBottom(hBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 650, 500);
    primaryStage.setTitle("Exercise16_24"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage  
    
    slTime.valueProperty().addListener(ov -> {
      if (slTime.isValueChanging()) {
        mediaPlayer.seek(mediaPlayer.getTotalDuration().
          multiply(slTime.getValue() / 100.0));
      }
    });

    mediaPlayer.currentTimeProperty().addListener(ov -> {
      slTime.setValue(mediaPlayer.getCurrentTime().divide(
        mediaPlayer.getMedia().getDuration()).toMillis() * 100.0);
      lblTime.setText(
        format((int)mediaPlayer.getCurrentTime().toSeconds()) + "/" + 
        format((int)mediaPlayer.getTotalDuration().toSeconds()));
    });
  }
  
  public static String format(long seconds) {

    int hour = (int)(seconds / 3600 % 24);
    int minute = (int)(seconds / 60 % 60);
    int second = (int)(seconds % 60);

    
    return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + 
      (second < 10 ? "0" + second : second);
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
