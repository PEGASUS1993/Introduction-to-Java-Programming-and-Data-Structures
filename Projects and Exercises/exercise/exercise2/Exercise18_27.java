import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise18_27 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    KochSnowFlakePane trianglePane = new KochSnowFlakePane(); 
    TextField tfOrder = new TextField(); 
    tfOrder.setOnAction(
      e -> trianglePane.setOrder(Integer.parseInt(tfOrder.getText())));
    tfOrder.setPrefColumnCount(4);
    tfOrder.setAlignment(Pos.BOTTOM_RIGHT);

    // Pane to hold label, text field, and a button
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(trianglePane);
    borderPane.setBottom(hBox);
        
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 200, 210);
    primaryStage.setTitle("Exercise18_27"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    scene.widthProperty().addListener(ov -> trianglePane.paint());
    scene.heightProperty().addListener(ov -> trianglePane.paint());
  }

  /** Pane for displaying fractal */
  static class KochSnowFlakePane extends Pane {
    private int order = 0;

    /** Set a new order */
    public void setOrder(int order) {
      this.order = order;
      paint();
    }

    KochSnowFlakePane() {
    }

    protected void paint() {
      double side = Math.min(getWidth(), getHeight()) * 0.8;
      double triangleHeight = side * Math.sin(Math.toRadians(60));
      
      // Set initial three points
      Point2D p1 = new Point2D(getWidth() / 2, 10);
      Point2D p2 = new Point2D(getWidth() / 2 - side / 2, 10 + triangleHeight);
      Point2D p3 = new Point2D(getWidth() / 2 + side / 2, 10 + triangleHeight);

      this.getChildren().clear(); // Clear the pane before redisplay
      
      displayKochSnowFlake(order, p1, p2);
      displayKochSnowFlake(order, p2, p3);
      displayKochSnowFlake(order, p3, p1);
    }

    private void displayKochSnowFlake(int order, Point2D p1, Point2D p2) {
      if (order == 0) {
        this.getChildren().add(new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
      }
      else { // order > 0
        double deltaX = p2.getX() - p1.getX();
        double deltaY = p2.getY() - p1.getY();

        Point2D x = new Point2D(p1.getX() + deltaX / 3, p1.getY() + deltaY / 3);
        Point2D y = new Point2D(p1.getX() + deltaX * 2 / 3, p1.getY() + deltaY * 2 / 3);
        Point2D z = new Point2D(
          (p1.getX() + p2.getX()) / 2 + Math.cos(Math.toRadians(30)) * (p1.getY() - p2.getY()) / 3,
          (p1.getY() + p2.getY()) / 2 + Math.cos(Math.toRadians(30)) * (p2.getX() - p1.getX()) / 3);

        // Recursively display snow flakes on lines
        displayKochSnowFlake(order - 1, p1, x);
        displayKochSnowFlake(order - 1, x, z);
        displayKochSnowFlake(order - 1, z, y);
        displayKochSnowFlake(order - 1, y, p2);
      }
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
