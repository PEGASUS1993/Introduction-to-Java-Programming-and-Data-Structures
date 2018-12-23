import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise31_15 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Group group = new Group();      
    Circle circle = new Circle(100, 100, 50);
    circle.setFill(Color.ORANGE);
    
    for (int i = 0; i < 16; i++) { 
      Line line = new Line(100, 172.5, 100, 27.25);
      line.getStrokeDashArray().addAll(5.0);
      line.setRotate(i * 180 / 16);
      group.getChildren().add(line);
    }

    group.getChildren().addAll(circle);

    // Create a scene and place it in the stage
    Scene scene = new Scene(new BorderPane(group), 200, 200);
    primaryStage.setTitle("Exercise31_15"); // Set the stage title
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
