import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise14_17 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Arc arc = new Arc(60, 240, 40, 20, 0, 180); // Draw the base
    arc.setFill(Color.WHITE);
    arc.setStroke(Color.BLACK);
    
    Line line1 = new Line(20 + 40, 220, 20 + 40, 20); // Draw the pole
    Line line2 = new Line(20 + 40, 20, 20 + 40 + 100, 20); // Draw the hanger
    Line line3 = new Line(20 + 40 + 100, 20, 20 + 40 + 100, 40); // Draw the hanger
    
    // Draw the circle
    int radius = 20;
    Circle circle = new Circle(20 + 40 + 100, 40 + radius, radius); // Draw the hanger
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    
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
    
    Group group = new Group();
    group.getChildren().addAll(arc, line1, line2, line3, circle, line4, line5, line6, line7, line8);

    // Create a scene and place it in the stage
    Scene scene = new Scene(new BorderPane(group), 400, 250);
    primaryStage.setTitle("Exercise14_17"); // Set the stage title
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
