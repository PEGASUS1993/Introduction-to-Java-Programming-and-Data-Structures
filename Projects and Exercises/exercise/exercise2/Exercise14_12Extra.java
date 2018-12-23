import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise14_12Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {           
    HBox pane = new HBox(10);   
    pane.getChildren().add(new JuliaCanvas());    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 450, 450);
    primaryStage.setTitle("Exercise14_12"); // Set the stage title
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

  class JuliaCanvas extends Pane {
    JuliaCanvas() {
      paint();
    }
    
    private void paint() {
      for (double x = -1.5; x < 1.5; x += 0.005)
        for (double y = -1.5; y < 1.5; y += 0.005) {  
          Rectangle rectangle = new Rectangle(x * 100 + 150, y * 100 + 150, 1, 1);
          this.getChildren().add(rectangle);
          
          int times = count(x, y);
          if (count(x, y) == 50) {
            rectangle.setFill(Color.BLACK);
          }
          else {
            int c = 50 - times;
            rectangle.setFill(Color.rgb(c * 14 % 256, c * 6 % 256, c * 13 % 256));
          }
        }
    }
  
    int count(double x, double y) {
      Complex c = new Complex(0.2, -0.6);
      Complex z = new Complex(x, y);
      
      for (int i = 0; i < 50; i++) {
        z = z.multiply(z).add(c);
        if (z.abs() > 2) return i;
      }
      
      return 50;
    }
  }
}
