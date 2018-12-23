import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_22 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Pane pane = new Pane();
       
    double paneWidth = 250; 
    double paneHeight = 250;
    double x1 = Math.random() * (paneWidth - 12);
    double y1 = Math.random() * (paneHeight - 12);
    double x2 = Math.random() * (paneWidth - 12);
    double y2 = Math.random() * (paneHeight - 12);
        
    double radius = 15;
    Circle circle1 = new Circle(x1, y1, radius);
    Circle circle2 = new Circle(x2, y2, radius);
    circle1.setFill(Color.WHITE);
    circle1.setStroke(Color.BLACK);
    circle2.setFill(Color.WHITE);
    circle2.setStroke(Color.BLACK);
    
    Text text1 = new Text(x1, y1, "1");
    Text text2 = new Text(x2, y2, "2");
    
    Line line = connectTwoCircles(x1, y1, x2, y2, radius);

    pane.getChildren().addAll(circle1, circle2, line, text1, text2);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_22"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  /** Connect two circles centered at (x1, y1) and (x2, y2) */
  private Line connectTwoCircles(
      double x1, double y1, double x2, double y2, double radius) {
    double d = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    int x11 = (int)(x1 - radius * (x1 - x2) / d);
    int y11 = (int)(y1 - radius * (y1 - y2) / d);
    int x21 = (int)(x2 + radius * (x1 - x2) / d);
    int y21 = (int)(y2 + radius * (y1 - y2) / d);
    return new Line(x11, y11, x21, y21);
  }   
  
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
