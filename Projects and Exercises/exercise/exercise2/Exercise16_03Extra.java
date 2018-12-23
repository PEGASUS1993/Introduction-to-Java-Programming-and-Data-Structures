import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise16_03Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {           
    HBox hBox = new HBox(5);
    TextField tfRed = new TextField();
    tfRed.setPrefColumnCount(4);
    TextField tfBlue = new TextField();
    tfBlue.setPrefColumnCount(4);
    TextField tfGreen = new TextField();
    tfGreen.setPrefColumnCount(4);
    hBox.getChildren().addAll(new Label("Red"), tfRed, new Label("Blue"), 
      tfBlue, new Label("Green"), tfGreen);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane pane = new BorderPane();   
    MandelbrotCanvas canvas = new MandelbrotCanvas();
    pane.setCenter(canvas);    
    pane.setBottom(hBox);    
       
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 425, 450);
    primaryStage.setTitle("Exercise16_03"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    tfRed.setOnAction(e -> {
      canvas.setRed(Integer.parseInt(tfRed.getText()));
      canvas.paint();
    });
    
    tfBlue.setOnAction(e -> {
      canvas.setBlue(Integer.parseInt(tfBlue.getText()));
      canvas.paint();
    });
    
    tfGreen.setOnAction(e -> {
      canvas.setGreen(Integer.parseInt(tfGreen.getText()));
      canvas.paint();
    });
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
      this.getChildren().clear();
      for (double x = -2.0; x < 2.0; x += 0.01)
        for (double y = -2.0; y < 2.0; y += 0.01) {
          Rectangle rectangle = new Rectangle(x * 100 + 200, y * 100 + 200, 1.0, 1.0);                  
          this.getChildren().add(rectangle); // Fill the rectangle with the specified color 
          
          int c = count(new Complex(x, y));
          if (c == COUNT_LIMIT) 
            rectangle.setFill(Color.BLACK); // c is in a Mandelbrot set
          else 
            rectangle.setFill(Color.rgb(
              c * red % 256, c * blue % 256, c * green % 256));
        }
    }
    
    int red = 77;
    int blue = 58;
    int green = 159;
    public void setRed(int red) {
      this.red = red;
    }
    
    public void setBlue(int blue) {
      this.blue = blue;
    }
    
    public void setGreen(int green) {
      this.green = green;
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
