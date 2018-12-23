import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_28 extends Application {

  private final static int NUMBER_OF_SLIDES = 10;
  private int current = 0;
  private String[] slides = new String[NUMBER_OF_SLIDES];

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Load slide from files to strings
    loadTextToSlides();

    TextArea ta = new TextArea();
    ta.setWrapText(true);
    
    StackPane pane = new StackPane();
    pane.getChildren().add(new ScrollPane(ta));

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 200);
    primaryStage.setTitle("Exercise16_28"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    Timeline animation = new Timeline(
            new KeyFrame(Duration.millis(2000), e -> {
              ta.setText(slides[current]);
              current = (current + 1) % NUMBER_OF_SLIDES;
              ;
            }));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation

    pane.setOnMouseClicked(e -> {
      if (animation.getStatus() == Animation.Status.PAUSED) {
        animation.play();
      } else {
        animation.pause();
      }
    });
  }

  private void loadTextToSlides() {
    for (int i = 0; i < NUMBER_OF_SLIDES; i++) {
      slides[i] = readAFile("text/slide" + i + ".txt");
    }
  }

  private String readAFile(String file) {
    String text = "";
    try {
      java.util.Scanner input = new java.util.Scanner(
              new java.io.File(file));
      while (input.hasNext()) {
        text += input.nextLine() + "\n";
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return text;
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
