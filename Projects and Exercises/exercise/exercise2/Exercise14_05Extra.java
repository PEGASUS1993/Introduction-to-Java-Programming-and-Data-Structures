import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Exercise14_05Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {           
    Pane pane = new Pane();
    double paneWidth = 200;
    double paneHeight = 200;  
    
    pane.getChildren().addAll(getStar(paneWidth / 2, paneHeight / 2, 80));    
    // Create a scene and place it in the stage
    Scene scene = new Scene(new Group(pane), paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_05"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  private Polygon getStar(double centerX, double centerY, double radius) {
    // Create a Polygon object
    Polygon polygon = new Polygon();
    polygon.setFill(new Color(1, 1, 1, 0));
    polygon.setStroke(Color.BLACK);
    
    ObservableList<Double> list = polygon.getPoints();
    
    // Add points to the polygon
    double angle = 2 * Math.PI / 20;
    
    for (int i = 0; i < 5; i++) {
      list.add(centerX + radius * Math.cos(angle)); 
      list.add(centerY - radius * Math.sin(angle));      
      angle += 2 * 2 * Math.PI / 5;
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
