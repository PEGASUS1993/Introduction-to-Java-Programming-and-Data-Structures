import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_31 extends Application {
  private double index = 0;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {      
    PalindromePane pane = new PalindromePane();
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 300, 300);
    primaryStage.setTitle("Exercise15_31"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    Timeline animation = new Timeline(
      new KeyFrame(Duration.millis(100), e -> {pane.next();}));
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
    
    pane.requestFocus();
    pane.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.UP) {
        animation.setRate(animation.getRate() + 1);
      }
      else if (e.getCode() == KeyCode.DOWN) {
        animation.setRate(animation.getRate() - 1);
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

class PalindromePane extends Pane {
  private double w = 300;
  private double pendulumRadius = 150;
  private double ballRadius = 10;
  private double leftAngle = 120;
  private double rightAngle = 60;
  private double angle = leftAngle; // Start from leftAngle
  private double angleDelta = 1; // Swing interval

  private Line line = new Line();
  private Circle c1 = new Circle();
  private Circle c2 = new Circle();  
  
  public PalindromePane() {
    getChildren().addAll(line, c1, c2);
    next();
  }
  
  public void next() {
    double x1 = w / 2;
    double y1 = 20;

    if (angle < rightAngle)
      angleDelta = 1; // Swing to the left
    else if (angle > leftAngle)
      angleDelta = -1; // Swing to the right

    angle += angleDelta;
    double x = x1 + pendulumRadius * Math.cos(Math.toRadians(angle));
    double y = y1 + pendulumRadius * Math.sin(Math.toRadians(angle));

    line.setStartX(x1); line.setStartY(y1);
    line.setEndX(x); line.setEndY(y);
    c1.setCenterX(x1); c1.setCenterY(y1); c1.setRadius(4);
    c2.setCenterX(x); c2.setCenterY(y); c2.setRadius(ballRadius);
  }
}
