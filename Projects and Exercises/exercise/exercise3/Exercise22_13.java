import java.util.ArrayList;
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

public class Exercise22_13 extends Application {  
  double radius = 2;
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new View();
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise22_13"); // Set the stage title
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
        
        ArrayList<Exercise22_11.MyPoint> listOfPoints = Exercise22_11.getConvexHull(points);
        for (int i = 0; i < listOfPoints.size(); i++) {
          polygon.getPoints().addAll(listOfPoints.get(i).x, listOfPoints.get(i).y);
        }
      }
    }
  }
}
