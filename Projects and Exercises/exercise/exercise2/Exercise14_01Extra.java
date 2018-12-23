import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_01Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    System.out.print("Enter coordinates and radius of two circles in this order x1 y1 r1 x2 y2 r2: ");
    Scanner input = new Scanner(System.in);
    
    double x1 = input.nextDouble();
    double y1 = input.nextDouble();
    double r1 = input.nextDouble();
    double x2 = input.nextDouble();
    double y2 = input.nextDouble();
    double r2 = input.nextDouble();
        
    Circle c1 = new Circle(x1, y1, r1);
    c1.setFill(new Color(1, 1, 1, 0));
    c1.setStroke(Color.BLACK);
    
    Circle c2 = new Circle(x2, y2, r2);
    c2.setFill(new Color(1, 1, 1, 0));
    c2.setStroke(Color.BLACK);
    
    Pane pane = new Pane();
    double paneWidth = 200;
    double paneHeight = 200;   
    
    Text text = new Text(40, paneHeight - 20, "");
    if (contains(c1, c2) || contains(c2, c1)) {
      text.setText("One circle is contained in another");
    }
    else if (overlaps(c1, c2)) {
      text.setText("The circles overlap");
    }
    else {
      text.setText("The circles do not overlap");
    }
    
    pane.getChildren().addAll(c1, c2, text);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_01"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  /** Returns true if c1 contains c2 or c2 contains c1 */
  private static boolean contains(Circle c1, Circle c2) {
    return new Point2D(c1.getCenterX(), 
        c1.getCenterY()).distance(c2.getCenterX(), c2.getCenterY())
        <= Math.abs(c1.getRadius() - c2.getRadius());
  }
  
  /** Returns true if c1 overlaps c2 */
  private static boolean overlaps(Circle c1, Circle c2) {
    return new Point2D(c1.getCenterX(), 
        c1.getCenterY()).distance(c2.getCenterX(), c2.getCenterY())
        <= c1.getRadius() + c2.getRadius();
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
