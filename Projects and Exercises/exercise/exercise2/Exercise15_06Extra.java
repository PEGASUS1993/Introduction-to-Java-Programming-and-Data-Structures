import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise15_06Extra extends Application {  
  private Pane pane = new Pane();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    pane.setOnMouseClicked(e -> { 
      if (e.getButton() == MouseButton.PRIMARY) { // Left button clicked       
        ObservableList<Node> list = pane.getChildren();
        if (list.size() >= 2) {
          ((Circle)list.get(p1)).setFill(Color.WHITE);
          ((Circle)list.get(p2)).setFill(Color.WHITE);
        }

        Circle circle = new Circle(e.getX(), e.getY(), 5);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        list.add(circle);
        colorClosestPair();
      }
      else if (e.getButton() == MouseButton.SECONDARY) { // Right button clicked
        ObservableList<Node> list = pane.getChildren();
        
        if (list.size() >= 2) {
          ((Circle)list.get(p1)).setFill(Color.WHITE);
          ((Circle)list.get(p2)).setFill(Color.WHITE);
        }
        for (int i = 0; i < list.size(); i++) {
          if (list.get(i).contains(e.getX(), e.getY())) {
            list.remove(i);
          }
        }
        colorClosestPair();
      }
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_06"); // Set the stage title
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
  
  int p1 = 0;
  int p2 = 1;
  
  /** Find two points that are nearest to each other and color the two points */
  private void colorClosestPair() {
    ObservableList<Node> list = pane.getChildren();
    
    // Compare the distance from the newly-added point to all others
    if (list.size() <= 1) {
      return;
    }
   
    p1 = 0;
    p2 = 1;
    double d = distance(((Circle)list.get(p1)).getCenterX(), ((Circle)list.get(p1)).getCenterY(),
        ((Circle)list.get(p2)).getCenterX(), ((Circle)list.get(p2)).getCenterY());
    
    for (int i = 0; i < list.size() - 1; i++) {
      double dis = distance(((Circle)list.get(i)).getCenterX(), 
          ((Circle)list.get(i)).getCenterY(),
          ((Circle)list.get(list.size() - 1)).getCenterX(), 
          ((Circle)list.get(list.size() - 1)).getCenterY());
      if (dis < d) {
        d = dis;
        p1 = i;
        p2 = list.size() - 1;
      }
    }
    
    ((Circle)list.get(p1)).setFill(Color.BLACK);
    ((Circle)list.get(p2)).setFill(Color.BLACK);
  }
  
  /** Compute the distance between two points (x1, y1) and (x2, y2)*/
  private double distance(
      double x1, double y1, double x2, double y2) {
    return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
  }
} 
