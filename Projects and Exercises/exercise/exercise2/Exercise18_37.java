import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise18_37 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    HilbertCurvePane pane = new HilbertCurvePane(); 
    TextField tfOrder = new TextField(); 
    tfOrder.setOnAction(
      e -> pane.setOrder(Integer.parseInt(tfOrder.getText())));
    tfOrder.setPrefColumnCount(4);
    tfOrder.setAlignment(Pos.BOTTOM_RIGHT);

    // Pane to hold label, text field, and a button
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);
        
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 400, 410);
    primaryStage.setTitle("Exercise18_37"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    scene.widthProperty().addListener(ov -> pane.paint());
    scene.heightProperty().addListener(ov -> pane.paint());
  }

  /** Pane for displaying triangles */
  static class HilbertCurvePane extends Pane {
    private double x, y; // Current cursor position
    private double length; // length of a U side. Dynamically determined based on order
    private int order = 1; // Order of the Hilbert curve

    /** Set a new order */
    public void setOrder(int order) {
      this.order = order;
      paint();
    }

    protected void paint() {
      // Get the length of a U side
      length = Math.min(getWidth(), getHeight());
      for (int i = 0; i < order; i++)
        length /= 2;
      
      // Reset starting cursor position
      x = length / 2; y = length / 2;
      
      this.getChildren().clear(); // Clear the pane before redisplay

      upperU(order); // Start recursion
    }

    /** Display upper U */
    private void upperU(int order) {
      if (order > 0) {
        leftU(order - 1);
        lineNewPosition(0, length);
        upperU(order - 1);
        lineNewPosition(length, 0);
        upperU(order - 1);
        lineNewPosition(0, -length);
        rightU(order - 1);
      }
    }

    /** Display left U */
    private void leftU(int order) {
      if (order > 0) {
        upperU(order - 1);
        lineNewPosition(length, 0);
        leftU(order - 1);
        lineNewPosition(0, length);
        leftU(order - 1);
        lineNewPosition(-length, 0);
        downU(order - 1);
      }
    }

    /** Display right U */
    private void rightU(int order) {
      if (order > 0) {
        downU(order - 1);
        lineNewPosition(-length, 0);
        rightU(order - 1);
        lineNewPosition(0, -length);
        rightU(order - 1);
        lineNewPosition(length, 0);
        upperU(order - 1);
      }
    }

    /** Display down U */
    private void downU(int order) {
      if (order > 0) {
        rightU(order - 1);
        lineNewPosition(0, -length);
        downU(order - 1);
        lineNewPosition(-length, 0);
        downU(order - 1);
        lineNewPosition(0, length);
        leftU(order - 1);
      }
    }

    /** Draw a line from the current position to the new relative position */
    public void lineNewPosition(double deltaX, double deltaY) {
      getChildren().add(new Line(x, y, x + deltaX, y + deltaY));
      x += deltaX;
      y += deltaY;
    }
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
