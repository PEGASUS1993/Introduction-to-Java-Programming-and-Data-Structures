import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Exercise14_08Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {         
    double paneWidth = 570;
    double paneHeight = 310;  
    
    HBox hBox = new HBox(5);
    for (int i = 4; i < 10; i++) {
      hBox.getChildren().add(new RegularPolygonPane(i));
    }
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(hBox, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_08"); // Set the stage title
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

class RegularPolygonPane extends StackPane {
  int numberOfSides = 6;
  public RegularPolygonPane(int numberOfSides) { 
    this.numberOfSides = numberOfSides;
    
    Polygon polygon = new Polygon();
    this.getChildren().add(polygon); 
    polygon.setFill(Color.WHITE);
    polygon.setStroke(Color.BLACK);
    ObservableList<Double> list = polygon.getPoints();
    
    final double WIDTH = 200, HEIGHT = 200;
    double centerX = WIDTH / 2, centerY = HEIGHT / 2;
    double radius = Math.min(WIDTH, HEIGHT) * 0.4;

    // Add points to the polygon list
    for (int i = 0; i < numberOfSides; i++) {
      list.add(centerX + radius * Math.cos(2 * i * Math.PI / numberOfSides)); 
      list.add(centerY - radius * Math.sin(2 * i * Math.PI / numberOfSides));
    }     
  }
}
