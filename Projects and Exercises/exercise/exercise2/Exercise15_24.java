import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_24 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a group 
    Group group = new Group();
    
    Circle circle = new Circle(0, 0, 10);
    circle.setFill(Color.ORANGE);
    
    Arc arc = new Arc(125, 100, 80, 40, 210, 125);
    arc.setFill(Color.WHITE);
    arc.setStroke(Color.BLACK);
    
    // Add circle and rectangle to the group
    group.getChildren().add(arc);
    group.getChildren().add(circle);
    
    // Create a path transition 
    PathTransition pt = new PathTransition();
    pt.setDuration(Duration.millis(4000));
    pt.setPath(arc);
    pt.setNode(circle);
    pt.setOrientation(
      PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    pt.setCycleCount(Timeline.INDEFINITE);
    pt.setAutoReverse(true);
    pt.play(); // Start animation 
    
    group.setOnMousePressed(e -> pt.pause());
    group.setOnMouseReleased(e -> pt.play());
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(new BorderPane(group), 250, 200);
    primaryStage.setTitle("Exercise15_24"); // Set the stage title
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
