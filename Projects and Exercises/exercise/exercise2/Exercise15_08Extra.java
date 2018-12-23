import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_08Extra extends Application {
  private double[] xpoints = new double[6];
  private double[] ypoints = new double[6];
  private Polygon polygon = new Polygon();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) { 
    Pane pane = new Pane();
    Rectangle rectangle = new Rectangle(40, 40, 20, 30);
    rectangle.setFill(Color.WHITE);
    rectangle.setStroke(Color.BLACK);
    
    Circle circle = new Circle();
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    
    Text text = new Text();
    pane.getChildren().addAll(circle, rectangle, text);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 300);
    primaryStage.setTitle("Exercise15_08"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    pane.setOnMouseClicked(e -> {
      circle.setCenterX(e.getX());
      circle.setCenterY(e.getY());
      circle.setRadius(30);
      
      text.setX(e.getX() + circle.getRadius());
      text.setY(e.getY() - circle.getRadius() - 4);
      if (circle.contains(rectangle.getX(), rectangle.getY()) &&
          circle.contains(rectangle.getX() + rectangle.getWidth(), rectangle.getY()) &&
          circle.contains(rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight()) &&
          circle.contains(rectangle.getX(), rectangle.getY() + rectangle.getHeight())) {
        text.setText("Circle contains the rectangle");    
      }
      else if (circle.intersects(rectangle.getX(), rectangle.getY(), 
          rectangle.getWidth(), rectangle.getHeight())) {
        text.setText("Circle intersects the rectangle");
      }
      else {
        text.setText("Circle is outside the rectangle");
      }    
    });
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
