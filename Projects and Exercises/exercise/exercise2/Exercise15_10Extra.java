import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyCode;
import java.util.LinkedList;

public class Exercise15_10Extra extends Application {
  final static double PANEL_WIDTH = 200;
  final static double PANEL_HEIGHT = 200;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    // Create a scene and place it in the stage
    BalloonPane pane = new BalloonPane();
    
    Scene scene = new Scene(pane, PANEL_WIDTH, PANEL_HEIGHT);
    primaryStage.setTitle("Exercise15_10"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    Timeline animation = new Timeline(
      new KeyFrame(Duration.millis(1500), e -> pane.paint()));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation
    pane.requestFocus();
  }

  class BalloonPane extends Pane {
    final static double BALLOON_RADIUS = 10;
    final static double BALL_RADIUS = 5;
    final static double GUN_LENGTH = 25;
    
    private double x_Balloon = Math.random() * PANEL_WIDTH;
    private double y_Balloon = Math.random() * PANEL_HEIGHT;
    
    private double angle = 90;    

    private LinkedList<SmallBall> list = new LinkedList<SmallBall>();
    
    private Circle balloon = new Circle(x_Balloon, y_Balloon, BALLOON_RADIUS);
    private Line gun = new Line();

    class SmallBall {
      double length;
      double angle;
      
      SmallBall(double length, double angle) {
        this.length = length;
        this.angle = angle;
      }
    }

    public BalloonPane() {            
      gun.setStartX(PANEL_WIDTH / 2);
      gun.setStartY(PANEL_HEIGHT);
      gun.setStrokeWidth(10);
      
      balloon.setFill(Color.WHITE);
      balloon.setStroke(Color.BLACK);
      
      paint();
      
      this.setOnKeyPressed(e -> {
        if (e.getCode() == KeyCode.LEFT) {
          if (angle < 180) angle += 5;
        }
        else if (e.getCode() == KeyCode.RIGHT) {
          if (angle > 0) angle -= 5;
        }
        else if (e.getCode() == KeyCode.UP) {
          // Launch a small ball
          list.add(new SmallBall(GUN_LENGTH, angle));
        }
        
        paint();
      });
    }
    
    boolean hit = false;
    
    public void paint() {           
      this.getChildren().clear();
      
      // Display the gun
      double x = GUN_LENGTH * Math.cos(Math.toRadians(angle)) + PANEL_WIDTH / 2;
      double y = PANEL_HEIGHT - GUN_LENGTH * Math.sin(Math.toRadians(angle));
      gun.setEndX(x);
      gun.setEndY(y);
      
      this.getChildren().addAll(balloon, gun);
      
      if (hit) {
        // Display four small pieces
        Circle circle1 = new Circle(x_Balloon - 5, y_Balloon, BALLOON_RADIUS / 2);
        Circle circle2 = new Circle(x_Balloon + 2 * BALLOON_RADIUS + 5, y_Balloon, BALLOON_RADIUS / 2);
        Circle circle3 = new Circle(x_Balloon, y_Balloon + 2 * BALLOON_RADIUS + 5, BALLOON_RADIUS / 2);
        Circle circle4 = new Circle(x_Balloon, y_Balloon - 2 * BALLOON_RADIUS - 5, BALLOON_RADIUS / 2);
        this.getChildren().addAll(circle1, circle2, circle3, circle4);
        circle1.setFill(Color.WHITE);
        circle2.setFill(Color.WHITE);
        circle3.setFill(Color.WHITE);
        circle4.setFill(Color.WHITE);
        circle1.setStroke(Color.BLACK);
        circle2.setStroke(Color.BLACK);
        circle3.setStroke(Color.BLACK);
        circle4.setStroke(Color.BLACK);
        
        hit = false;
        
        // New random location
        x_Balloon = Math.random() * PANEL_WIDTH;
        y_Balloon = Math.random() * PANEL_HEIGHT;
        balloon.setCenterX(x_Balloon);
        balloon.setCenterY(y_Balloon);        

        return;
      }
            
      // Draw small hitting balls
      for (int i = 0; i < list.size(); i++) {
        SmallBall ball = list.get(i);
        ball.length += 5;
        
        x = ball.length * Math.cos(Math.toRadians(ball.angle)) + getWidth() / 2;
        y = getHeight() - ball.length * Math.sin(Math.toRadians(ball.angle));
          
        Circle hittingBall = new Circle(x, y, BALL_RADIUS);
        this.getChildren().add(hittingBall);
        
        if (overlaps(x, y, BALL_RADIUS, 
            x_Balloon, y_Balloon, BALLOON_RADIUS)) {
          list.remove(i);
          hit = true;
        }
        
        if (x > getWidth() || x < 0 || y < 0)
          list.remove(i);         
      }
    }
  }
  
  public static boolean overlaps(double x1, double y1, double radius1,
      double x2, double y2, double radius2) {    
    return Math.sqrt((x1 - x2) * (x1 - x2)
      + (y1 - y2) * (y1 - y2)) <= radius1 + radius2;
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
