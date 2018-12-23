import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_23 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    System.out.print("Enter coordinates, width, and height of two rectangles in this order x1 y1 w1 h1 x2 y2 w2 h2: ");
    Scanner input = new Scanner(System.in);
    
    double x1 = input.nextDouble();
    double y1 = input.nextDouble();
    double w1 = input.nextDouble();
    double h1 = input.nextDouble();
    double x2 = input.nextDouble();
    double y2 = input.nextDouble();
    double w2 = input.nextDouble();
    double h2 = input.nextDouble();
        
    Rectangle r1 = new Rectangle(x1, y1, w1, h1);
    r1.setFill(new Color(1, 1, 1, 0));
    r1.setStroke(Color.BLACK);
    
    Rectangle r2 = new Rectangle(x2, y2, w2, h2);
    r2.setFill(new Color(1, 1, 1, 0));
    r2.setStroke(Color.BLACK);
    
    Pane pane = new Pane();
    double paneWidth = 200;
    double paneHeight = 200;   
    
    Text text = new Text(40, paneHeight - 20, "");
    if (contains(r1, r2) || contains(r2, r1)) {
      text.setText("One rectangle is contained in another");
    }
    else if (overlaps(r1, r2)) {
      text.setText("The rectangles overlap");
    }
    else {
      text.setText("The rectangles do not overlap");
    }
    
    pane.getChildren().addAll(r1, r2, text);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_23"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  /** Returns true if r1 contains r2 */
  private static boolean contains(Rectangle r1, Rectangle r2) {
    // Four corner points in r2
    double x1 = r2.getX();
    double y1 = r2.getY();
    double x2 = x1 + r2.getWidth();
    double y2 = y1;
    double x3 = x1;
    double y3 = y1 + r2.getHeight();
    double x4 = x1 + r2.getWidth();
    double y4 = y1 + r2.getHeight();
    
    return r1.contains(x1, y1) && r1.contains(x2, y2) && r1.contains(x3, y3) && r1.contains(x4, y4);
  }
  
  /** Returns true if r1 overlaps r2 */
  private static boolean overlaps(Rectangle r1, Rectangle r2) {
    // Four corner points in r2
    double r1xCenter = r1.getX() + r1.getWidth() / 2;
    double r2xCenter = r2.getX() + r2.getWidth() / 2;
    double r1yCenter = r1.getY() + r1.getHeight() / 2;
    double r2yCenter = r2.getY() + r2.getHeight() / 2;
 
    return Math.abs(r1xCenter - r2xCenter) <= (r1.getWidth() + r2.getWidth()) / 2 &&
      Math.abs(r1yCenter - r2yCenter) <= (r1.getHeight() + r2.getHeight()) / 2; 
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
