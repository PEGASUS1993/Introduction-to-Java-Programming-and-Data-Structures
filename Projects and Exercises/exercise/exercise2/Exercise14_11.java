import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise14_11 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Circle circle = new Circle(100, 100, 80);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    
    Circle circle1 = new Circle(70, 70, 10);
    Circle circle2 = new Circle(130, 70, 10);

    Ellipse ellipse1 = new Ellipse(70, 70, 20, 15);
    ellipse1.setFill(Color.WHITE);
    ellipse1.setStroke(Color.BLACK);

    Ellipse ellipse2 = new Ellipse(130, 70, 20, 15);
    ellipse2.setFill(Color.WHITE);
    ellipse2.setStroke(Color.BLACK);

    Line line1 = new Line(100, 80, 80, 120);
    Line line2 = new Line(80, 120, 120, 120);
    Line line3 = new Line(120, 120, 100, 80);

    Arc arc = new Arc(100, 130, 40, 15, 180, 180);
    arc.setFill(Color.WHITE);
    arc.setStroke(Color.BLACK);
    
    Group group = new Group();
    group.getChildren().addAll(circle, ellipse1, ellipse2, 
      circle1, circle2, line1, line2, line3, arc);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(new BorderPane(group), 200, 200);
    primaryStage.setTitle("Exercise14_11"); // Set the stage title
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
