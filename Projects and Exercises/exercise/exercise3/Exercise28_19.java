import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Use the old Graph.java, UnweightedGraph.java, UnweightedGraph.java. Need to
 * replace it in the future.
 */
public class Exercise28_19 extends Application {
  private TextField tfStartCity = new TextField();
  private Button btDisplayDFS = new Button("Display DFS Tree");
  private Button btDisplayBFS = new Button("Display BFS Tree");
  private Label lblStatus = new Label();
  
  private City[] vertices = {new City("Seattle", 75, 50),
    new City("San Francisco", 50, 210), new City("Los Angeles", 75, 275),
    new City("Denver", 275, 175), new City("Kansas City", 400, 245),
    new City("Chicago", 450, 100), new City("Boston", 700, 80),
    new City("New York", 675, 120), new City("Atlanta", 575, 295),
    new City("Miami", 600, 400), new City("Dallas", 408, 325),
    new City("Houston", 450, 360)};

  // Edge array for graph in Figure 27.1
  private int[][] edges = {
    {0, 1}, {0, 3}, {0, 5},
    {1, 0}, {1, 2}, {1, 3},
    {2, 1}, {2, 3}, {2, 4}, {2, 10},
    {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
    {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
    {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
    {6, 5}, {6, 7},
    {7, 4}, {7, 5}, {7, 6}, {7, 8},
    {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
    {9, 8}, {9, 11},
    {10, 2}, {10, 4}, {10, 8}, {10, 11},
    {11, 8}, {11, 9}, {11, 10}
  };

  private Graph<City> graph1 = new UnweightedGraph<>(vertices, edges);
  private GraphView view = new GraphView(graph1);

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
    pane.setCenter(view);

    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Starting City:"), tfStartCity,
            btDisplayDFS, btDisplayBFS);
    hBox.setAlignment(Pos.CENTER);

    pane.setBottom(hBox);
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 750, 450);
    primaryStage.setTitle("Exercise28_19"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    view.repaint();
    
    btDisplayDFS.setOnAction(e -> {
      String name = tfStartCity.getText();
      int index = graph1.getIndex(new City(name, 0, 0));
      if (index < 0) 
        lblStatus.setText(name + " is not in the map");
      else
        view.setTree(graph1.dfs(index));
    });
    
    btDisplayBFS.setOnAction(e -> {
      String name = tfStartCity.getText();
      int index = graph1.getIndex(new City(name, 0, 0));
      if (index < 0) 
        lblStatus.setText(name + " is not in the map");
      else
        view.setTree(graph1.bfs(index));
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

  class GraphView extends Pane {
    private Graph<? extends Displayable> graph;
    private UnweightedGraph<? extends Displayable>.SearchTree tree;

    public GraphView(Graph<? extends Displayable> graph,
            UnweightedGraph<? extends Displayable>.SearchTree tree) {
      this.graph = graph;
      this.tree = tree;
    }

    public GraphView(Graph<? extends Displayable> graph) {
      this.graph = graph;
    }

    public void setTree(UnweightedGraph<? extends Displayable>.SearchTree tree) {
      this.tree = tree;
      repaint();
    }

    protected void repaint() {
      getChildren().clear();

      // Draw vertices
      List<? extends Displayable> vertices = graph.getVertices();

      for (int i = 0; i < graph.getSize(); i++) {
        double x = vertices.get(i).getX();
        double y = vertices.get(i).getY();
        String name = vertices.get(i).getName();

        getChildren().addAll(new Circle(x, y, 8),
                new Text(x - 12, y - 12, name));
      }

      // Draw edges
      for (int i = 0; i < graph.getSize(); i++) {
        List<Integer> edges = graph.getNeighbors(i);
        for (int j = 0; j < edges.size(); j++) {
          int v = edges.get(j);
          double x1 = graph.getVertex(i).getX();
          double y1 = graph.getVertex(i).getY();
          double x2 = graph.getVertex(v).getX();
          double y2 = graph.getVertex(v).getY();

          Line line = new Line(x1, y1, x2, y2);
          getChildren().add(line);
        }
      }

      // Highlight the edges in the spanning tree
      if (tree == null) {
        return;
      }

      for (int i = 0; i < graph.getSize(); i++) {
        if (tree.getParent(i) != -1) {
          int v = tree.getParent(i);
          double x1 = graph.getVertex(i).getX();
          double y1 = graph.getVertex(i).getY();
          double x2 = graph.getVertex(v).getX();
          double y2 = graph.getVertex(v).getY();

          drawArrowLine(x2, y2, x1, y1);

        }
      }
    }

    public void drawArrowLine(double x1, double y1,
        double x2, double y2) {

      Line line1 = new Line(x1, y1, x2, y2);
      line1.setStroke(Color.RED);
      getChildren().add(line1);

      // find slope of this line
      double slope = ((((double) y1) - (double) y2))
              / (((double) x1) - (((double) x2)));

      double arctan = Math.atan(slope);

      // This will flip the arrow 45 off of a
      // perpendicular line at pt x2
      double set45 = 1.57 / 2;

      // arrows should always point towards i, not i+1
      if (x1 < x2) {
        // add 90 degrees to arrow lines
        set45 = -1.57 * 1.5;
      }

      // set length of arrows
      int arrlen = 15;

      // draw arrows on line
      Line line2 = new Line(x2, y2, x2 + Math.cos(arctan + set45) * arrlen,
              y2 + Math.sin(arctan + set45) * arrlen);
      line2.setStroke(Color.RED);
      getChildren().add(line2);

      Line line3 = new Line(x2, y2, (int) ((x2 + (Math.cos(arctan - set45) * arrlen))),
              (int) (((y2)) + (Math.sin(arctan - set45) * arrlen)));
      line3.setStroke(Color.RED);
      getChildren().add(line3);
    }
  }

  class City implements Displayable {

    private double x, y;
    private String name;

    City(String name, int x, int y) {
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
      return ((City) o).name.equals(this.name);
    }
  }
}
