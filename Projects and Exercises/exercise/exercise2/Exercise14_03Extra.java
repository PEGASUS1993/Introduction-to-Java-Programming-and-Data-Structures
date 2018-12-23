import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Exercise14_03Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {           
    Pane pane = new Pane();
    double paneWidth = 200;
    double paneHeight = 200;  
    
    pane.setStyle("-fx-background-color: blue");
    pane.getChildren().addAll(getStar(paneWidth / 2, paneHeight / 2, 50));

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_03"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  private Polygon getStar(double centerX, double centerY, double radius) {
    // Create a Polygon object
    Polygon polygon = new Polygon();
    polygon.setFill(Color.WHITE);
    polygon.setStroke(Color.BLACK);
    
    double a = 2 * Math.PI / 20;
    double b = 2 * Math.PI / 10;
    
    double h1 = radius * Math.sin(a);
    double r2 = h1 / Math.sin(a + b); // The radius of the inner points
    
    ObservableList<Double> list = polygon.getPoints();
  
    // Add points to the polygon
    double angle = 2 * Math.PI / 20;
    double angle1 = - 2 * Math.PI / 20 + 2 * Math.PI / 5;   
    for (int i = 0; i < 5; i++) {
      // Add an outer point
      list.add(centerX + radius * Math.cos(angle));
      list.add(centerY - radius * Math.sin(angle));
      angle += 2 * Math.PI / 5;
           
      // Add an inner point
      list.add(centerX + r2* Math.cos(angle1));
      list.add(centerY - r2 * Math.sin(angle1));
      angle1 += 2 * Math.PI / 5;
    }
    
    return polygon;
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
