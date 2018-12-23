import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise14_11Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {           
    HBox pane = new HBox(10);   
    pane.getChildren().add(new MandelbrotCanvas());    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 450, 450);
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

  class MandelbrotCanvas extends Pane {
    final static int COUNT_LIMIT = 60;
    
    MandelbrotCanvas() {
      paint();
    }
    
    private void paint() {     
      for (double x = -2.0; x < 2.0; x += 0.01)
        for (double y = -2.0; y < 2.0; y += 0.01) {
          Rectangle rectangle = new Rectangle(x * 100 + 200, y * 100 + 200, 1.0, 1.0);                  
          this.getChildren().add(rectangle); // Fill the rectangle with the specified color 
          
          int c = count(new Complex(x, y));
          if (c == COUNT_LIMIT) 
            rectangle.setFill(Color.BLACK); // c is in a Mandelbrot set
          else 
            rectangle.setFill(Color.rgb(
              c * 77 % 256, c * 58 % 256, c * 159 % 256));
        }
    }
    
    /** Returns the iteration count */
    int count(Complex c) {
      Complex z = new Complex(0, 0); // z0
      
      for (int i = 0; i < COUNT_LIMIT; i++) {
        z = z.multiply(z).add(c); // Get z1, z2, ...
        if (z.abs() > 2) return i; // The sequence is unbounded
      }
      
      return COUNT_LIMIT; // Indicates a bounded sequence
    }
  }
}
