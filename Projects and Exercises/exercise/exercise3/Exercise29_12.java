import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise29_12 extends Application {
  private City[] vertices = { new City("Seattle", 75, 50),
      new City("San Francisco", 50, 210),
      new City("Los Angeles", 75, 275), new City("Denver", 275, 175),
      new City("Kansas City", 400, 245),
      new City("Chicago", 450, 100), new City("Boston", 700, 80),
      new City("New York", 675, 120), new City("Atlanta", 575, 295),
      new City("Miami", 600, 400), new City("Dallas", 408, 325),
      new City("Houston", 450, 360) };

  private int[][] edges = {
    {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
    {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
    {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
    {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, 
      {3, 5, 1003},
    {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260},
      {4, 8, 864}, {4, 10, 496},
    {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, 
      {5, 6, 983}, {5, 7, 787},
    {6, 5, 983}, {6, 7, 214},
    {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
    {8, 4, 864}, {8, 7, 888}, {8, 9, 661}, 
      {8, 10, 781}, {8, 11, 810},
    {9, 8, 661}, {9, 11, 1187},
    {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
    {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
  };

  private WeightedGraph<City> graph1 = new WeightedGraph<>(vertices, edges);
  private GraphView view = new GraphView(graph1);
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create a scene and place it in the stage
    Scene scene = new Scene(new BorderPane(view), 450, 350);
    primaryStage.setTitle("Exercise29_12"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  class GraphView extends Group {
    Graph<? extends Displayable> graph;
    
    public GraphView(Graph<? extends Displayable> graph) {
      this.graph = graph;
      paint();
    }

    protected void paint() {
      // Draw vertices
      List<? extends Displayable> vertices = graph.getVertices();
      
      for (int i = 0; i < graph.getSize(); i++) {
        double x = vertices.get(i).getX();
        double y = vertices.get(i).getY();
        String name = vertices.get(i).getName();
        
        getChildren().addAll(new Circle(x, y, 8), 
          new Text(x - 12, y - 12, name));
      }
      
      // Display edges and weights
      for (int i = 0; i < graph.getSize(); i++) {
        List<Integer> neighbors = graph.getNeighbors(i);
        for (int j = 0; j < neighbors.size(); j++) {
          int v = neighbors.get(j);
          double x1 = graph.getVertex(i).getX();
          double y1 = graph.getVertex(i).getY();
          double x2 = graph.getVertex(v).getX();
          double y2 = graph.getVertex(v).getY();
          
          try {
            getChildren().addAll(new Line(x1, y1, x2, y2), 
              new Text((x1 + x2) / 2, (y1 + y2) / 2 - 6, ((WeightedGraph)graph).getWeight(i, v) + ""));
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
    }
  }

  class City implements Displayable {
    private double x, y;
    private String name;
    
    City(String name, double x, double y) {
      this.name = name;
      this.x = x;
      this.y = y;
    }
    
    public double getX() {
      return x;
    }
    
    public double getY() {
      return y;
    }
    
    public String getName() {
      return name;
    }
    
    public boolean equals(Object o) {
      return ((City)o).name.equals(this.name);
    }
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
