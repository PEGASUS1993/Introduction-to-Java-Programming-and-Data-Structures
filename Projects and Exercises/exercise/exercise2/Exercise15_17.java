import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_17 extends Application {  
  double radius = 5;
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
    pane.getChildren().addAll(rectangle, text, boundingRectangle);
    
    pane.setOnMouseClicked(e -> { 
      if (e.getButton() == MouseButton.PRIMARY) { // Left button clicked       
        Circle circle = new Circle(e.getX(), e.getY(), 5);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
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
      
      // Obtain the x- and y-coordindates of the points
      ArrayList<Double> listX = new ArrayList<>();
      ArrayList<Double> listY = new ArrayList<>();
      for (Node node: pane.getChildren()) {
        if (node instanceof Circle) {
          listX.add(((Circle)node).getCenterX());
          listY.add(((Circle)node).getCenterY());  
        }
      }
      
      double minX = Collections.min(listX);
      double maxX = Collections.max(listX);
      double minY = Collections.min(listY);
      double maxY = Collections.max(listY);
      
      boundingRectangle.setX(minX - radius);
      boundingRectangle.setY(minY - radius);
      boundingRectangle.setWidth(maxX - minX + 2 * radius);
      boundingRectangle.setHeight(maxY - minY + 2 * radius);
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_17"); // Set the stage title
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
