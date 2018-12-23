import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise14_04Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {           
    Pane pane = new Pane();
    double paneWidth = 570;
    double paneHeight = 310;  
    
    Rectangle rectangle = new Rectangle(10, 10, 240, 160);
    rectangle.setFill(Color.BLUE);
    pane.getChildren().addAll(rectangle);
    
    int centerX = 30;
    int centerY = 30;
    int radius = 10;
    pane.getChildren().addAll(getStar(centerX, centerY, radius));
    
    for (int j = 0; j < 5; j++) {
      for (int i = 0; i < 6; i++) {
        pane.getChildren().addAll(getStar(centerX, centerY, radius));
        centerX += 40;  
      }
      
      centerX = 30;
      centerY += 30;
    }

    centerX = 50;
    centerY = 45;
    for (int j = 0; j < 4; j++) {
      for (int i = 0; i < 5; i++) {
        pane.getChildren().addAll(getStar(centerX, centerY, radius));
        centerX += 40;  
      }
      
      centerX = 50;
      centerY += 30;
    }
    
    int y = 0;
    for (int i = 0; i < 4; i++) {
      Rectangle r = new Rectangle(250, 10 + y, 550 - 240, 160 / 7);
      r.setFill(Color.RED);
      y += 2 * 160 / 7;
      pane.getChildren().addAll(r);
    }
    
    for (int i = 0; i < 3; i++) {
      Rectangle r = new Rectangle(10, 10 + y, 550, 160 / 7);
      r.setFill(Color.RED);
      y += 2 * 160 / 7;
      pane.getChildren().addAll(r);
    }
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_04"); // Set the stage title
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
