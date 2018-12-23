import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Exercise14_25 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    int radius = 80;
    final int SIZE = 5;
    
    double[] angles = new double[SIZE];
    for (int i = 0; i < SIZE; i++)
      angles[i] = Math.random() * 2 * Math.PI;

    Arrays.sort(angles);

    Pane pane = new Pane();
    double paneWidth = 200;
    double paneHeight = 200;  

    Polygon polygon = new Polygon();
    polygon.setFill(Color.WHITE);
    polygon.setStroke(Color.BLACK);
    
    ObservableList<Double> list = polygon.getPoints();
    for (int i = 0; i < SIZE; i++) {
      list.add(radius * Math.cos(angles[i]) + paneWidth / 2);
      list.add(radius * Math.sin(angles[i]) + paneHeight / 2);      
    }
    
    Circle circle = new Circle(paneWidth / 2, paneHeight / 2, radius);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    
    pane.getChildren().addAll(circle, polygon);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_25"); // Set the stage title
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
