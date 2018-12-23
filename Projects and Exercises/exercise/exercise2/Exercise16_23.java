import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_23 extends Application {
  private int index = 0;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Enter information for animation"), 0, 0);
    gridPane.add(new Label("Animation speed in milliseconds"), 0, 1);
    gridPane.add(new Label("Image file prefix"), 0, 2);
    gridPane.add(new Label("Number of images"), 0, 3);
    gridPane.add(new Label("Audio file URL"), 0, 4);

    TextField tfAnimationSpeed = new TextField();
    TextField tfFilePrefix = new TextField();
    TextField tfNumberOfImages = new TextField();
    TextField tfAudioFileURL = new TextField();
    tfAudioFileURL.setPrefColumnCount(38);
    
    gridPane.add(tfAnimationSpeed, 1, 1);
    gridPane.add(tfFilePrefix, 1, 2);
    gridPane.add(tfNumberOfImages, 1, 3);
    gridPane.add(tfAudioFileURL, 1, 4);
    gridPane.setAlignment(Pos.CENTER);

    Button btStartAnimation = new Button("Start Animation");

    BorderPane pane = new BorderPane();
    pane.setBottom(gridPane);
    pane.setTop(btStartAnimation);
    BorderPane.setAlignment(btStartAnimation, Pos.BOTTOM_RIGHT);

    ImageView imageView = new ImageView();
    pane.setCenter(imageView);

    btStartAnimation.setOnAction(e -> {
      int n = Integer.parseInt(tfNumberOfImages.getText());
      Image[] images = new Image[n];
      for (int i = 1; i <= n; i++) {
        images[i - 1] = new Image("image/" + tfFilePrefix.getText() + i + ".gif");
      }

      imageView.setImage(images[0]);

      Timeline animation = new Timeline(
        new KeyFrame(Duration.millis(Integer.parseInt(tfAnimationSpeed.getText())), e1 -> {
          imageView.setImage(images[index++ % 24]);
        }));
      
      animation.setCycleCount(Timeline.INDEFINITE);
      animation.play();
      
      AudioClip audioClip = new AudioClip(tfAudioFileURL.getText());
      audioClip.setCycleCount(Timeline.INDEFINITE);
      audioClip.play();
    });

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 350, 300);
    primaryStage.setTitle("Exercise16_23"); // Set the stage title
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
