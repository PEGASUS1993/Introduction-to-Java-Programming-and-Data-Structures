import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise22_15 extends Application {  
  double radius = 2;
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new View();
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise22_15"); // Set the stage title
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
    
  private Polygon polygon = new Polygon();
        
  class View extends Pane {
    View() {
      Rectangle rectangle = new Rectangle(20, 10, 140, 60);
      rectangle.setFill(Color.WHITE);
      rectangle.setStroke(Color.BLACK);
      Text text = new Text(30, 30, 
        "INSTRUCTION\nAdd: Left Click\nRemove: Right Click");
      Rectangle boundingRectangle = new Rectangle();
      boundingRectangle.setFill(Color.WHITE);
      boundingRectangle.setStroke(Color.BLACK);

      polygon.setFill(Color.WHITE);
      polygon.setStroke(Color.BLACK);
      
      getChildren().addAll(rectangle, text, boundingRectangle, polygon);
      
      this.setOnMouseClicked(e -> { 
        if (e.getButton() == MouseButton.PRIMARY) { // Left button clicked       
          Circle circle = new Circle(e.getX(), e.getY(), 5);
          getChildren().add(circle);
          drawNewPolygon();
        }
        else if (e.getButton() == MouseButton.SECONDARY) { // Right button clicked
          ObservableList<Node> list = getChildren();
          for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Circle && list.get(i).contains(e.getX(), e.getY())) {
              list.remove(i);
            }
          }
          
          drawNewPolygon();
        }
      });
    }
    
    protected void drawNewPolygon() {     
      // Gather all points
      ObservableList<Node> list = getChildren();
      List<Circle> listOfCircle = new ArrayList<>();
      
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i) instanceof Circle) {
          listOfCircle.add((Circle)(list.get(i)));
        }
      }
      
      polygon.getPoints().clear(); // Clear the polygon to start a new polygon
              
      // Draw the polygon
      if (listOfCircle.size() >= 3) {
        double[][] points = new double[listOfCircle.size()][2];

        for (int i = 0; i < points.length; i++) {
          points[i][0] = listOfCircle.get(i).getCenterX();
          points[i][1] = listOfCircle.get(i).getCenterY();
        }
        
        ArrayList<MyPoint> listOfPoints = Exercise22_15.getPolygon(points);
        for (int i = 0; i < listOfPoints.size(); i++) {
          polygon.getPoints().addAll(listOfPoints.get(i).x, listOfPoints.get(i).y);
        }
      }
    }
  }
  
  /** Return the points that form a convex hull */
  public static ArrayList<MyPoint> getPolygon(double[][] points) {
    // Create MyPoint objects for points
    ArrayList<MyPoint> p = new ArrayList<>();    
    for (int i = 0; i < points.length; i++)
      p.add(new MyPoint(points[i][0], points[i][1]));

    placeP0(p);
    
    for (int i = 0; i < points.length; i++)
      p.get(i).setRightMostLowestPoint(p.get(0));

    Collections.sort(p);
    
    return p;
  }

  /** Place the rightmost lowest point as the first element in p */ 
  private static void placeP0(ArrayList<MyPoint> p) {
    int rightMostIndex = 0;
    double rightMostX = p.get(0).x;
    double rightMostY = p.get(0).y;
    
    for (int i = 1; i < p.size(); i++) {
      if (rightMostY < p.get(i).y) {
        rightMostY = p.get(i).y;
        rightMostX = p.get(i).x;
        rightMostIndex = i;
      }
      else if (rightMostY == p.get(i).y && rightMostX < p.get(i).x) {
        rightMostX = p.get(i).x;
        rightMostIndex = i;
      }
    }      
    
    // Swap p.get(rightMostIndex) with p.get(0)
    if (rightMostIndex != 0) {
      MyPoint temp = p.get(0);
      p.set(0, p.get(rightMostIndex));
      p.set(rightMostIndex, temp);
    }
  }
  
  public static class MyPoint implements Comparable<MyPoint> {
    double x, y;
    
    MyPoint rightMostLowestPoint;
    
    MyPoint(double x, double y) {
      this.x = x; this.y = y;
    }
    
    public void setRightMostLowestPoint(MyPoint p) {
      rightMostLowestPoint = p;
    }
    
    @Override
    public int compareTo(MyPoint o) {
      if (o == rightMostLowestPoint && y == rightMostLowestPoint.y) return 1;
      
      double status = whichSide(rightMostLowestPoint.x, rightMostLowestPoint.y, o.x, o.y, this.x, this.y);        
      if (status < 0) // Left side of the line from rightMostLowestPoint to o, please note we are using the Java coordinate system. y increases downward
        return 1;
      else if (status == 0) {
        if (distance(rightMostLowestPoint.x, rightMostLowestPoint.y, this.x, this.y) >
            distance(rightMostLowestPoint.x, rightMostLowestPoint.y, o.x, o.y))
          return 1;
        else 
          return -1;
      }
      else
        return -1;
    }
  }
  
  private static double whichSide(double x0, double y0, double x1, double y1, double x2, double y2) {
    return (x1 - x0) * (y2 - y0) - (x2 - x0) * (y1 - y0);
  }
  
  private static double distance(double x1, double y1, double x2, double y2) {
    return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
  }
}
