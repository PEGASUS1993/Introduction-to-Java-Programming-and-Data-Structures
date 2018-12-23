import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise14_14 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Pane pane = new MyCubePane();

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 300, 300);
    primaryStage.setTitle("Exercise14_14"); // Set the stage title
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

class MyCubePane extends Pane {
  private void paint() {
    double paneWidth = getWidth();
    double paneHeight = getHeight();
    double width = paneWidth * 0.90 - 30;
    double height = paneHeight * 0.80 - 30;
    double diff = Math.min(50, Math.min(width, height) * 0.4);
    
    // Draw the front rectangle
    Rectangle r1 = new Rectangle(10, 60, width, height);
    r1.setFill(new Color(1, 1, 1, 0));
    r1.setStroke(Color.BLACK);
    
    // Draw the back rectangle
    Rectangle r2 = new Rectangle(30, 60 - diff, width, height);
    r2.setFill(new Color(1, 1, 1, 0));
    r2.setStroke(Color.BLACK);
    
    // Connect the corners
    Line line1 = new Line(10, 60, 30, 60 - diff);
    Line line2 = new Line(10, 60 + height, 30, 60 - diff + height);
    Line line3 = new Line(10 + width, 60, 30 + width, 60 - diff);
    Line line4 = new Line(10 + width, 60 + height, 30 + width, 60 - diff + height);

    getChildren().clear();
    getChildren().addAll(r1, r2, line1, line2, line3, line4);
  }
  
  @Override
  public void setWidth(double width) {
  	super.setWidth(width);
  	paint();
  }
  
  @Override
  public void setHeight(double height) {
  	super.setHeight(height);
  	paint();
  }
}
