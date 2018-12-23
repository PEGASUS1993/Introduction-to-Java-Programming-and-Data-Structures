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

public class Exercise15_09Extra extends Application {
  Arc arc = new Arc(60, 240, 40, 20, 0, 180); // Draw the base
  
  Line line1 = new Line(20 + 40, 220, 20 + 40, 20); // Draw the pole
  Line line2 = new Line(20 + 40, 20, 20 + 40 + 100, 20); // Draw the hanger
  Line line3 = new Line(20 + 40 + 100, 20, 20 + 40 + 100, 40); // Draw the hanger

  // Draw the circle
  int radius = 20;
  Circle circle = new Circle(20 + 40 + 100, 40 + radius, radius); // Draw the hanger
  
  // Draw the left arm
  Line line4 = new Line(20 + 40 + 100 - radius * Math.cos(Math.toRadians(45)), 
      40 + radius + radius * Math.sin(Math.toRadians(45)),
      20 + 40 + 100 - 60, 40 + radius + 60);
  
  // Draw the right arm
  Line line5 = new Line(20 + 40 + 100 + radius * Math.cos(Math.toRadians(45)), 
      40 + radius + radius * Math.sin(Math.toRadians(45)),
      20 + 40 + 100 + 60, 40 + radius + 60);

  // Draw the body
  Line line6 = new Line(20 + 40 + 100, 40 + 2 * radius,
      20 + 40 + 100, 40 + radius + 80);

  // Draw the left leg
  Line line7 = new Line(20 + 40 + 100, 40 + radius + 80, 20 + 40 + 100 - 40, 40 + radius + 80 + 40);

  // Draw the right leg
  Line line8 = new Line(20 + 40 + 100, 40 + radius + 80, 20 + 40 + 100 + 40, 40 + radius + 80 + 40);

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Pane pane = new Pane();
       
    arc.setFill(Color.WHITE);
    arc.setStroke(Color.BLACK);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    
    pane.getChildren().addAll(arc, line1, line2, line3, circle, line4, line5, line6, line7, line8);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_09"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
   Timeline animation = new Timeline(
     new KeyFrame(Duration.millis(500), e -> move()));
   animation.setCycleCount(Timeline.INDEFINITE);
   animation.play(); // Start animation
   
   pane.setOnKeyPressed(e -> {
     if (e.getCode() == KeyCode.UP)
       animation.setRate(animation.getRate() + 0.1); 
     else if (e.getCode() == KeyCode.DOWN)
       animation.setRate(animation.getRate() - 0.1); 
     else if (e.getCode() == KeyCode.S)
       animation.pause(); 
     else if (e.getCode() == KeyCode.R)
       animation.play(); 
   });
   pane.requestFocus();
  }
  
  int leftAngle = 120;
  int rightAngle = 60;
  int angle = 90; // Start from center
  int angleDelta = 1; // Swing interval
  
  private void move() {
    if (angle < rightAngle)
      angleDelta = 1; // Swing to the left
    else if (angle > leftAngle)
      angleDelta = -1; // Swing to the right
          
    angle += angleDelta;

    double x1 = 20 + 40 + 100;
    double y1 = 20;
    double radius = 20;
    
    // Draw the hanger line
    double x = x1 + radius * Math.cos(Math.toRadians(angle));
    double y = y1 + radius * Math.sin(Math.toRadians(angle));
    line3.setEndX(x);
    line3.setEndY(y);
    
    // Draw the circle
    double length = 20 + 20;
    x = x1 + length * Math.cos(Math.toRadians(angle));
    y = y1 + length * Math.sin(Math.toRadians(angle));      
    circle.setCenterX(x);
    circle.setCenterY(y);

    // Draw the left arm      
    length = distance(x1, y1, 
        20 + 40 + 100 - radius * Math.cos(Math.toRadians(45)),
        40 + radius + radius * Math.sin(Math.toRadians(45)));    
    double angle1 = Math.toDegrees(Math.asin(
        radius * Math.cos(Math.toRadians(45)) / length));
    double x2 = x1 + length * Math.cos(Math.toRadians(angle + angle1));
    double y2 = y1 + length * Math.sin(Math.toRadians(angle + angle1));      
    
    length = (int)distance(x1, y1, 
        20 + 40 + 100 - 60, 40 + radius + 60);
    angle1 = Math.toDegrees(Math.asin(60 / length));
    double x3 = x1 + length * Math.cos(Math.toRadians(angle + angle1));
    double y3 = y1 + length * Math.sin(Math.toRadians(angle + angle1));      
   
    line4.setStartX(x2);
    line4.setStartY(y2);
    line4.setEndX(x3);
    line4.setEndY(y3);
    
    // Draw the right arm
    length = distance(x1, y1, 
        20 + 40 + 100 + radius * Math.cos(Math.toRadians(45)),
        40 + radius + radius * Math.sin(Math.toRadians(45)));    
    angle1 = - Math.toDegrees(Math.asin(
        radius * Math.cos(Math.toRadians(45)) / length));
    x2 = x1 + length * Math.cos(Math.toRadians(angle + angle1));
    y2 = y1 + length * Math.sin(Math.toRadians(angle + angle1));      
    
    length = distance(x1, y1, 
        20 + 40 + 100 + 60, 40 + radius + 60);
    angle1 = -Math.toDegrees(Math.asin(60 / length));
    x3 = x1 + length * Math.cos(Math.toRadians(angle + angle1));
    y3 = y1 + length * Math.sin(Math.toRadians(angle + angle1)); 
    
    line5.setStartX(x2);
    line5.setStartY(y2);
    line5.setEndX(x3);
    line5.setEndY(y3);

    // Draw the body
    length = 40 + 20;
    x2 = x1 + (int)(length * Math.cos(Math.toRadians(angle)));
    y2 = y1 + (int)(length * Math.sin(Math.toRadians(angle)));
    
    length = 40 + 20 + 60;
    x3 = x1 + length * Math.cos(Math.toRadians(angle));
    y3 = y1 + length * Math.sin(Math.toRadians(angle));
    
    line6.setStartX(x2);
    line6.setStartY(y2);
    line6.setEndX(x3);
    line6.setEndY(y3);

    // Draw the left leg
    length = distance(x1, y1, 
        20 + 40 + 100 - 40, 40 + radius + 80 + 40);     
    angle1 = Math.toDegrees(Math.asin(
        40.0 / length));
    double x4 = x1 + length * Math.cos(Math.toRadians(angle + angle1));
    double y4 = y1 + length * Math.sin(Math.toRadians(angle + angle1));            
    line7.setStartX(x3);
    line7.setStartY(y3);
    line7.setEndX(x4);
    line7.setEndY(y4);

    // Draw the right leg
    angle1 = -Math.toDegrees(Math.asin(40.0 / length));
    x4 = x1 + length * Math.cos(Math.toRadians(angle + angle1));
    y4 = y1 + length * Math.sin(Math.toRadians(angle + angle1));            
    line8.setStartX(x3);
    line8.setStartY(y3);
    line8.setEndX(x4);
    line8.setEndY(y4);
  }

  /** Compute the distance between two points (x1, y1) and (x2, y2)*/
  public static double distance(
      double x1, double y1, double x2, double y2) {
    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
