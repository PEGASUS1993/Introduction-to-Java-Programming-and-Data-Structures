import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise28_23 extends Application {
  // Rectangles are stored in a list

  private List<MyRectangle> rectangles = new ArrayList<>();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a scene and place it in the stage
    Scene scene = new Scene(new RectanglePane(), 450, 350);
    primaryStage.setTitle("Exercise28_23"); // Set the stage title
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

  /**
   * Pane for displaying rectangles
   */
  class RectanglePane extends Pane {

    public RectanglePane() {
      setOnMouseClicked(e -> {
        if (!isInsideARectangle(e.getX(), e.getY())) { // Add a new rectangle
          rectangles.add(new MyRectangle(e.getX(), e.getY()));
          repaint();
        }
      });
    }

    /**
     * Returns true if the point is inside an existing rectangle
     */
    private boolean isInsideARectangle(double x, double y) {
      for (MyRectangle rectangle : rectangles) {
        if (rectangle.contains(x, y)) {
          return true;
        }
      }

      return false;
    }

    public void repaint() {
      if (rectangles.size() == 0) {
        return; // Nothing to paint
      }
      // Build the edges
      List<Edge> edges = new ArrayList<>();
      for (int i = 0; i < rectangles.size(); i++) {
        for (int j = i + 1; j < rectangles.size(); j++) {
          if (rectangles.get(i).overlaps(rectangles.get(j))) {
            edges.add(new Edge(i, j));
            edges.add(new Edge(j, i));
          }
        }
      }

      // Create a graph with rectangles as vertices
      Graph<MyRectangle> graph = new UnweightedGraph<>(rectangles, edges);
      UnweightedGraph<MyRectangle>.SearchTree tree = graph.dfs(0); // a DFS tree
      boolean isAllRectanglesConnected =
              rectangles.size() == tree.getNumberOfVerticesFound();

      for (MyRectangle rectangle : rectangles) {
        double w = rectangle.width;
        double h = rectangle.height;

        Rectangle rect = new Rectangle(rectangle.x - w / 2, rectangle.y - h / 2, w, h);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        getChildren().add(rect);
        if (isAllRectanglesConnected) { // All rectangles are connected
          rect.setFill(Color.RED);
          rect.setStroke(Color.RED);
        }
      }
    }
  }

  private static class MyRectangle {
    double width = 40;
    double height = 40;
    double x, y;

    MyRectangle(double x, double y) {
      this.x = x;
      this.y = y;
    }

    public boolean contains(double x1, double y1) {
      return Math.abs(x1 - x) <= width / 2 && Math.abs(y1 - y) <= height / 2;
    }

    public boolean overlaps(MyRectangle rectangle) {
      return Math.abs(rectangle.x - x) <= rectangle.width / 2 + width / 2
              && Math.abs(rectangle.y - y) <= rectangle.height / 2 + height / 2;
    }
  }
}
