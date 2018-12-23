import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_21 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Pane pane = new Pane();
       
    double paneWidth = 250; 
    double paneHeight = 250;
    double x1 = Math.random() * (paneWidth - 12);
    double y1 = Math.random() * (paneHeight - 12);
    double x2 = Math.random() * (paneWidth - 12);
    double y2 = Math.random() * (paneHeight - 12);
        
    Circle circle1 = new Circle(x1, y1, 10);
    Circle circle2 = new Circle(x2, y2, 10);
    Line line = new Line(x1, y1, x2, y2);
    Text text = new Text((x1 + x2) / 2, (y1 + y2) / 2, 
      getDistance(x1, y1, x2, y2) + "");

    pane.getChildren().addAll(circle1, circle2, line, text);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_21"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  public static double getDistance(double x1, double y1, double x2, double y2) {
    return Math.sqrt((x1 - x2) * (x1 -x2) + (y1 - y2) * (y1 - y2));
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
