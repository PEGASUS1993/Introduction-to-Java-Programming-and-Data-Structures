import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.input.MouseButton;

public class Exercise15_14Extra extends Application {
  Pane pane = new Pane();
  List<Circle> circles = new ArrayList<>();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    double paneWidth = 570;
    double paneHeight = 310;

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise15_14"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    pane.setOnMouseClicked(e -> {
      if (e.getButton() == MouseButton.PRIMARY
          && !isInACircle(e.getX(), e.getY())) {
        Circle c = new Circle(e.getX(), e.getY(), 5);
        circles.add(new Circle(e.getX(), e.getY(), 5));
        pane.getChildren().add(c);
      } 
      else if (e.getButton() == MouseButton.SECONDARY) {
        for (Node circle : circles) {
          if (circle.contains(e.getX(), e.getY())) {
            circles.remove(circle);
            pane.getChildren().remove(circle);
            break;
          }
        }
      }

      if (circles.size() >= 3) {
        int index = getCentralCity();
        color(index);
      }
    });
  }

  private void color(int index) {    
    pane.getChildren().clear();
    
    for (Node circle : circles) {
      ((Circle) circle).setFill(Color.BLACK);
      pane.getChildren().add(circle);
    }
    ((Circle) (circles.get(index))).setFill(Color.RED);
    
    for (int i = 0; i < circles.size(); i++) {
      Circle c = circles.get(i);
      pane.getChildren().add(new Line(c.getCenterX(), 
        c.getCenterY(), 
        ((Circle)(circles.get(index))).getCenterX(), 
        ((Circle)(circles.get(index))).getCenterY()));
    }
  }

  private boolean isInACircle(double x, double y) {
    for (Node circle : circles) {
      if (circle.contains(x, y)) {
        return true;
      }
    }
    return false;
  }

  public int getCentralCity() {   
    double minTotal = totalDistance(0);
    int minIndex = 0;
    for (int i = 1; i < circles.size(); i++) {
      double total = totalDistance(i);

      if (minTotal > total) {
        minTotal = total;
        minIndex = i;
      }
    }

    return minIndex;
  }

  private double totalDistance(int i) {
    double total = 0;
    for (int j = 0; j < circles.size(); j++)
      total += distance((Circle) (circles.get(i)), (Circle) (circles.get(j)));
    return total;
  }

  private double distance(Circle c1, Circle c2) {
    return Math.sqrt((c1.getCenterX() - c2.getCenterX())
        * (c1.getCenterX() - c2.getCenterX())
        + (c1.getCenterY() - c2.getCenterY())
        * (c1.getCenterY() - c2.getCenterY()));
  }

  /**
   * The main method is only needed for the IDE with limited JavaFX support. Not
   * needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
