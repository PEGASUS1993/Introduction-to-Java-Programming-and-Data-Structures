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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise22_17 extends Application {  
  double radius = 2;
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new Pane();

    Rectangle rectangle = new Rectangle(20, 10, 140, 60);
    rectangle.setFill(Color.WHITE);
    rectangle.setStroke(Color.BLACK);
    Text text = new Text(30, 30, 
      "INSTRUCTION\nAdd: Left Click\nRemove: Right Click");
    Rectangle boundingRectangle = new Rectangle();
    boundingRectangle.setFill(Color.WHITE);
    boundingRectangle.setStroke(Color.BLACK);

    Line line = new Line(); 
    pane.getChildren().addAll(rectangle, text, boundingRectangle, line);
    
    pane.setOnMouseClicked(e -> { 
      if (e.getButton() == MouseButton.PRIMARY) { // Left button clicked       
        Circle circle = new Circle(e.getX(), e.getY(), 5);
        pane.getChildren().add(circle);
      }
      else if (e.getButton() == MouseButton.SECONDARY) { // Right button clicked
        ObservableList<Node> list = pane.getChildren();
        for (int i = 0; i < list.size(); i++) {
          if (list.get(i) instanceof Circle && list.get(i).contains(e.getX(), e.getY())) {
            list.remove(i);
          }
        }
      }
      
      ObservableList<Node> list = pane.getChildren();
      List<Circle> listOfCircle = new ArrayList<>();
      
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i) instanceof Circle) {
          listOfCircle.add((Circle)(list.get(i)));
        }
      }
      
      double[][] points = new double[listOfCircle.size()][2];
           
      // Draw the polygon
      if (listOfCircle.size() >= 2) {
        for (int i = 0; i < points.length; i++) {
          points[i][0] = listOfCircle.get(i).getCenterX();
          points[i][1] = listOfCircle.get(i).getCenterY();
        }

        Exercise22_07.Pair pair = Exercise22_07.getClosestPair(points);
        line.setStartX(pair.p1.x);
        line.setStartY(pair.p1.y);
        line.setEndX(pair.p2.x);
        line.setEndY(pair.p2.y); 
      }
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise22_17"); // Set the stage title
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
