import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_07Extra extends Application {
  private double[] xpoints = new double[6];
  private double[] ypoints = new double[6];
  private Polygon polygon = new Polygon();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {           
    System.out.print("Enter coordinates of six points in a polygon: ");
    Scanner input = new Scanner(System.in);
    
    for (int i = 0; i < 6; i++) {
      xpoints[i] = input.nextDouble();
      ypoints[i] = input.nextDouble();
    }   
    
    Pane pane = new Pane();
    double paneWidth = 570;
    double paneHeight = 310;  

    polygon.setFill(new Color(1, 1, 1, 0));
    polygon.setStroke(Color.BLACK);
    ObservableList<Double> list = polygon.getPoints();

    for (int i = 0; i < xpoints.length; i++) {
      list.add(xpoints[i]);
      list.add(ypoints[i]);
      
      // Draw a small filled circle for each point
      Circle circle = new Circle(xpoints[i] - 2, ypoints[i] - 2, 4);
      // Draw the string text for (x, y) coordinates for the point
      Text text = new Text(xpoints[i] - 16, ypoints[i] - 6, 
        "(" + xpoints[i] + ", " + ypoints[i] + ")");
      
      pane.getChildren().addAll(circle, text);
    }
    
    // Find the strategic point
    double[] center = getStrategicPoint();

    Circle circle = new Circle(center[0] - 2, center[1] - 2, 4);
    circle.setFill(Color.RED);
    Text text = new Text(center[0] - 16, center[1] - 6, 
      "(" + center[0] + ", " + center[0] + ")");
    pane.getChildren().addAll(polygon, circle, text);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_07"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  double[] getStrategicPoint() {    
    double minX = min(xpoints);
    double maxX = max(xpoints);
    double minY = min(ypoints);
    double maxY = max(ypoints);

    double[] result = new double[2];
    double currentTotal = Double.MAX_VALUE;
    for (int i = (int)minX; i <= maxX; i++)
      for (int j = (int)minY; j <= maxY; j++)
        if (polygon.contains(i, j)) {
          double sum = 0;
          for (int k = 0; k < xpoints.length; k++)
            sum += new Point2D(xpoints[k], ypoints[k]).distance(i, j);

          if (currentTotal > sum) {
            result[0] = i;
            result[1] = j;
            currentTotal = sum;
          }
        }

    return result;
  }
  
  private static double min(double[] list) {
    double result = list[0];
    for (int i = 1; i < list.length; i++)
      if (result > list[i])
        result = list[i];
    return result;
  }
  
  private static double max(double[] list) {
    double result = list[0];
    for (int i = 1; i < list.length; i++)
      if (result < list[i])
        result = list[i];
    return result;
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
