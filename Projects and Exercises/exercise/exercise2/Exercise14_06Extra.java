import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Exercise14_06Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {           
    HBox pane = new HBox(10);   
    pane.getChildren().addAll(new DrawXSquare(), new DrawSine(), new DrawCosine());    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 900, 270);
    primaryStage.setTitle("Exercise14_06"); // Set the stage title
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

  class DrawXSquare extends AbstractDrawFunction {
    /**Implement the function*/
    public double f(double x) {
      // scaleFactor for adjusting y coordinates
      double scaleFactor = 0.01;

      return scaleFactor * x * x;
    }
  }

  class DrawSine extends AbstractDrawFunction {
    public double f(double x) {
      return 50 * Math.sin((x / 100.0) * 2 * Math.PI);
    }
  }

  class DrawCosine extends AbstractDrawFunction {
    public double f(double x) {
      return 50 * Math.cos((x / 100.0) * 2 * Math.PI);
    }
  }

  abstract class AbstractDrawFunction extends Pane {
    /** Polygon to hold the points */
    private Polyline p = new Polyline();
    final int TO_X_AXIS = 125;
    final int TO_Y_AXIS = 125;
    final int END_OF_X_AXIS = 275;
    final int END_OF_Y_AXIS = 200;

    /** Default constructor */
    protected AbstractDrawFunction() {
      drawFunction();
      p.setFill(null);
      p.setStroke(Color.BLACK);
      paint();
    }

    /**Draw the function*/
    public abstract double f(double x);

    /**Obtain points for x coordinates 100, 101, ..., 300*/
    public void drawFunction() {
      for (int x = -100; x <= 100; x++) {
        p.getPoints().addAll((double)x + TO_X_AXIS, TO_Y_AXIS - f(x));
      }
    }

    private void paint() {
      // Draw x axis
      this.getChildren().add(new Line(10, TO_X_AXIS, END_OF_X_AXIS, TO_X_AXIS));

      // Draw y axis
      this.getChildren().add(new Line(TO_Y_AXIS, 30, TO_Y_AXIS, END_OF_Y_AXIS));

      // Draw arrows on x axis
      this.getChildren().add(new Line(END_OF_X_AXIS, TO_X_AXIS, END_OF_X_AXIS - 20, TO_X_AXIS - 10));
      this.getChildren().add(new Line(END_OF_X_AXIS, TO_X_AXIS, END_OF_X_AXIS - 20, TO_X_AXIS + 10));

      // Draw arrows on y axis
      this.getChildren().add(new Line(TO_Y_AXIS, 30, TO_Y_AXIS - 10, 50));
      this.getChildren().add(new Line(TO_Y_AXIS, 30, TO_Y_AXIS + 10, 50));

      // Draw x, y
      this.getChildren().add(new Text(END_OF_X_AXIS - 20, TO_X_AXIS - 30, "X"));
      this.getChildren().add(new Text(TO_Y_AXIS + 20, 40, "Y"));

      // Draw a polygon line by connecting the points in the polygon
      this.getChildren().add(p);
    }
  }
}
