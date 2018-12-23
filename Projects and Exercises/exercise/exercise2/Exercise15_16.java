import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_16 extends Application {  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new Pane();
    Circle circle1 = new Circle(40, 40, 10);
    circle1.setFill(Color.WHITE);
    circle1.setStroke(Color.BLACK);
    Circle circle2 = new Circle(120, 150, 10);
    circle2.setFill(Color.WHITE);
    circle2.setStroke(Color.BLACK);
    Line line = new Line(circle1.getCenterX(), circle1.getCenterY(), 
        circle2.getCenterX(), circle2.getCenterY());
    
    double distance = new Point2D(circle1.getCenterX(), 
      circle1.getCenterY()).distance(circle2.getCenterX(), circle2.getCenterY());
    Text text = new Text((circle1.getCenterX() + circle2.getCenterX()) / 2,
      (circle1.getCenterY() + circle2.getCenterY()) / 2, (int)distance + "");
    pane.getChildren().addAll(line, circle1, circle2, text);
    
    circle1.setOnMouseDragged(e -> { 
      circle1.setCenterX(e.getX());
      circle1.setCenterY(e.getY());
      line.setStartX(e.getX());
      line.setStartY(e.getY());
      double d = new Point2D(circle1.getCenterX(), circle1.getCenterY()).distance(circle2.getCenterX(), circle2.getCenterY()); 
      text.setX((circle1.getCenterX() + circle2.getCenterX()) / 2);
      text.setY((circle1.getCenterY() + circle2.getCenterY()) / 2);
      text.setText(d + "");
    });
    
    circle2.setOnMouseDragged(e -> { 
      circle2.setCenterX(e.getX());
      circle2.setCenterY(e.getY());
      line.setEndX(e.getX());
      line.setEndY(e.getY());
      double d = new Point2D(circle1.getCenterX(), circle1.getCenterY()).distance(circle2.getCenterX(), circle2.getCenterY()); 
      text.setX((circle1.getCenterX() + circle2.getCenterX()) / 2);
      text.setY((circle1.getCenterY() + circle2.getCenterY()) / 2);
      text.setText((int)d + "");
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_16"); // Set the stage title
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
