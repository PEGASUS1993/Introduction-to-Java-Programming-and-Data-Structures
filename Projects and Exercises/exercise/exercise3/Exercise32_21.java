import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.application.Platform;

public class Exercise32_21 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    MultipleBallPane ballPane = new MultipleBallPane();
    ballPane.setStyle("-fx-border-color: yellow");

    Button btSuspend = new Button("Suspend");
    Button btResume = new Button("Resume");
    Button btAdd = new Button("+");
    Button btSubtract = new Button("-");
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(btSuspend, btResume, btAdd, btSubtract);
    hBox.setAlignment(Pos.CENTER);

    // Add or remove a ball
    btAdd.setOnAction(e -> ballPane.add());
    btSubtract.setOnAction(e -> ballPane.subtract());

    // Pause and resume animation
    btSuspend.setOnAction(e -> ballPane.pause());
    btResume.setOnAction(e -> ballPane.play());

    // Use a scroll bar to control animation speed
    ScrollBar sbSpeed = new ScrollBar();
    sbSpeed.setMax(20);
    sbSpeed.setValue(10);
//    ballPane.rateProperty().bind(sbSpeed.valueProperty());
    
    sbSpeed.valueProperty().addListener(ov -> 
      ballPane.setSleepTime(10 + (int)(ballPane.getWidth() - sbSpeed.getValue() * ballPane.getWidth() /
        sbSpeed.getMax())));

    BorderPane pane = new BorderPane();
    pane.setCenter(ballPane);
    pane.setTop(sbSpeed);
    pane.setBottom(hBox);

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 350, 250);
    primaryStage.setTitle("Exercise32_21"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  private class MultipleBallPane extends Pane {
    private int sleepTime = 50;
    
    private Thread thread = new Thread(() -> {
      try {
        while (true) {
          Platform.runLater(() -> moveBall());
          Thread.sleep(sleepTime);
        }
      }
      catch (InterruptedException ex) {
      }
    });
    
    public void pause() {
      thread.suspend();
    }
    
    public void play() {
      thread.resume();
    }
    
    public MultipleBallPane() {    
      thread.start();
      
      this.setOnMousePressed(e -> {
      List<Node> ballList = getChildren();
      for (int i = 0; i < ballList.size(); i++) {
        if (ballList.get(i).contains(e.getX(), e.getY())) {
          ballList.remove(i);
          break;
        }
      }
    });
    }

    public void add() {
      Color color = new Color(Math.random(), 
        Math.random(), Math.random(), 0.5);
      getChildren().add(new Ball(30, 30, 5, color)); 
    }
    
    public void subtract() {
      if (getChildren().size() > 0) {
        getChildren().remove(getChildren().size() - 1); 
      }
    }
    
    public void increaseSpeed() {
      if (sleepTime > 1)
        sleepTime--;
    }

    public void decreaseSpeed() {
      sleepTime++;
    }
    
    public void setSleepTime(int sleepTime) {
      this.sleepTime = sleepTime;
    }

    protected void moveBall() {
      for (Node node: this.getChildren()) {
        Ball ball = (Ball)node;
        // Check boundaries
        if (ball.getCenterX() < ball.getRadius() || 
            ball.getCenterX() > getWidth() - ball.getRadius()) {
          ball.dx *= -1; // Change ball move direction
        }
        if (ball.getCenterY() < ball.getRadius() || 
            ball.getCenterY() > getHeight() - ball.getRadius()) {
          ball.dy *= -1; // Change ball move direction
        }

        // Adjust ball position
        ball.setCenterX(ball.dx + ball.getCenterX());
        ball.setCenterY(ball.dy + ball.getCenterY());
      }
      
      // Detect collision
      List<Node> ballList = this.getChildren();
      for (int i = 0; i < ballList.size(); i++) {
        for (int j = i + 1; j < ballList.size(); j++) {
          if (((Ball)(ballList.get(i))).isCollideWith(
                 (Ball)(ballList.get(j)))) {
            // Change the radius for i
            ((Ball)(ballList.get(i))).setRadius(
              ((Ball)ballList.get(i)).getRadius() + 
              ((Ball)ballList.get(j)).getRadius());
            
            // Remove j
            ballList.remove(ballList.get(j));
          }
        }
      }
    }
  }

  class Ball extends Circle {
    private double dx = 1, dy = 1;

    Ball(double x, double y, double radius, Color color) {
      super(x, y, radius);
      setFill(color); // Set ball color
    }
    
    public boolean isCollideWith(Ball b) {
      double distanceBetweenTheTwoBalls = 
        (Math.sqrt(Math.pow(getCenterX() - b.getCenterX(), 2) +
          Math.pow(getCenterY() - b.getCenterY(), 2)));
      return distanceBetweenTheTwoBalls <= b.getRadius() + getRadius();
    }
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
