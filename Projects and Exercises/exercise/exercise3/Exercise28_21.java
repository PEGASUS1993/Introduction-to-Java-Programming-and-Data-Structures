import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise28_21 extends Application {
  private List<Circle> circles = new ArrayList<Circle>();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a scene and place it in the stage
    Scene scene = new Scene(new MyCirclePane(), 250, 450);
    primaryStage.setTitle("Exercise28_21"); // Set the stage title
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

  class MyCirclePane extends Pane {
    int colorIndex = 0;
    private Color[] colorList = {Color.BLACK, Color.BLUE, Color.GREEN, Color.CYAN, 
      Color.YELLOW, Color.ORANGE, Color.RED};

    public MyCirclePane() {
      this.setOnMouseClicked(e -> {
        if (!isInsideAnyCircle(e.getX(), e.getY())) { // Add a new circle
          circles.add(new Circle(e.getX(), e.getY(), 20));
          repaint();
        }
      });
    }

    private boolean isInsideAnyCircle(double x, double y) {
      for (Circle circle : circles)
        if (circle.contains(x, y))
          return true;

      return false;
    }

    protected void repaint() {
      this.getChildren().clear();
      
      if (circles.size() == 0)
        return; // Nothing to paint

      // Build the edges
      List<Edge> edges = new ArrayList<>();
      for (int i = 0; i < circles.size(); i++)
        for (int j = i + 1; j < circles.size(); j++)
          if (overlaps(circles.get(i), circles.get(j))) {
            edges.add(new Edge(i, j));
            edges.add(new Edge(j, i));
          }

      // Create a graph with circles as vertices
      // MyGraph is defined in Programming Exercise 28.4
      MyGraph<Circle> graph = new MyGraph<>(circles, edges);

      // The getConnectedComponent is defined in Programming Exercise 28.4
      List<List<Integer>> connectedComponents = graph.getConnectedComponents();

      int k = 0;
      for (List<Integer> list : connectedComponents) {
        Color color = colorList[k++ % colorList.length];
        for (int i : list) {
          Circle c = circles.get(i);
          c.setFill(color);
          getChildren().add(c);
        }
      }
    }
  }
    
  public boolean overlaps(Circle circle1, Circle circle2) {
    return new Point2D(circle1.getCenterX(), circle1.getCenterY()).distance(circle2.getCenterX(), circle2.getCenterY()) 
      <= circle1.getRadius() + circle2.getRadius();
  }
}
