import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_15Extra extends Application {
  private double[] xpoints = {25, 170, 300, 100, 50, 20};
  private double[] ypoints = {20, 25, 100, 110, 90, 50};
  Polygon polygon = new Polygon();
  ObservableList<Double> list = polygon.getPoints();
  ArrayList<Circle> circles = new ArrayList<>();
  Circle strategicPoint = new Circle(0, 0, 0);
  Text strategicPointText = new Text(0, 0, "");
  Line[] line = new Line[6];
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {              
    Pane pane = new Pane();
    double paneWidth = 570;
    double paneHeight = 310;  

    polygon.setFill(new Color(1, 1, 1, 0));
    polygon.setStroke(Color.BLACK);

    for (int i = 0; i < xpoints.length; i++) {      
      list.add(xpoints[i]);
      list.add(ypoints[i]);
      
      // Draw a small filled circle for each point
      Circle circle = new Circle(xpoints[i] - 2, ypoints[i] - 2, 6);
      Text text = new Text(xpoints[i] - 16, ypoints[i] - 6, 
          "(" + xpoints[i] + ", " + ypoints[i] + ")");
      circle.setOnMouseDragged(e -> {
        circle.setCenterX(e.getX()); 
        circle.setCenterY(e.getY());
        text.setX(e.getX() - 16);
        text.setY(e.getY() - 6);
        text.setText("(" + e.getX() + ", " + e.getY() + ")");
        updatePolygon();
        updateStrategicPoint();
      });
      
      line[i] = new Line();
      pane.getChildren().addAll(circle, text, line[i]);
      circles.add(circle);
    }
    
    updateStrategicPoint();
    pane.getChildren().addAll(polygon, strategicPoint, strategicPointText);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise15_15"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  

  private void updatePolygon() {
    list.clear();
    for (int i = 0; i < circles.size(); i++) {      
      list.add(circles.get(i).getCenterX());
      list.add(circles.get(i).getCenterY());
    }
  }
  
  private void updateStrategicPoint() {
    for (int i = 0; i < circles.size(); i++) {      
      xpoints[i] = circles.get(i).getCenterX();
      ypoints[i] = circles.get(i).getCenterY();
    }
    double[] p = getStrategicPoint();

    strategicPointText.setX(p[0] - 16);
    strategicPointText.setY(p[1] - 3);
    strategicPointText.setText("(" + p[0] + ", " + p[1] + ")");
    strategicPoint.setRadius(4);
    strategicPoint.setFill(Color.RED);
    strategicPoint.setCenterX(p[0]);
    strategicPoint.setCenterY(p[1]);
    
    // Update lines connecting strategic point to all other vertices
    for (int i = 0; i < circles.size(); i++) {
      line[i].setStartX(circles.get(i).getCenterX());
      line[i].setStartY(circles.get(i).getCenterY());
      line[i].setEndX(strategicPoint.getCenterX());
      line[i].setEndY(strategicPoint.getCenterY());
    }
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
