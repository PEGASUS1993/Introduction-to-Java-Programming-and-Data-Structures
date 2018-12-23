import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise29_17 extends Application {
  private Button btMST = new Button("Show MST");

  private TextField tfStartVertex = new TextField();
  private Button btAllSP = new Button("Show All SP From the Source");

  private TextField tfStartVertex1 = new TextField();
  private TextField tfEndVertex1 = new TextField();
  private Button btShortestPath = new Button("Show Shortest Path");

  private ArrayList<Vertex> list = new ArrayList<>();
  private ArrayList<Edge1> edges = new ArrayList<>();
  private Label lblStatus = new Label();
  
  private WeightedGraph<Vertex> graph = null;
  private GraphView view = new GraphView();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    tfStartVertex.setPrefColumnCount(2);
    tfStartVertex1.setPrefColumnCount(2);
    tfEndVertex1.setPrefColumnCount(2);

    HBox hBox1 = new HBox(5);
    hBox1.setStyle("-fx-border-color: black");
    hBox1.getChildren().addAll(new Label("Source vertex: "),
            tfStartVertex, btAllSP);

    HBox hBox2 = new HBox(5);
    hBox2.getChildren().addAll(new Label("Starting vertex: "),
       tfStartVertex1, new Label("Ending vertex: "),
            tfEndVertex1, btShortestPath);

    VBox vBox = new VBox(5);
    vBox.setStyle("-fx-border-color: black");
    vBox.getChildren().addAll(new Label("Find a shortest path"), hBox2);

    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(btMST, hBox1, vBox);

    BorderPane pane = new BorderPane();
    pane.setCenter(view);
    pane.setBottom(hBox);
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 980, 350);
    primaryStage.setTitle("Exercise29_17"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    view.repaint();
    
    btMST.setOnAction(e -> {
      updateGraph();
      WeightedGraph<Vertex>.SearchTree tree = graph.getMinimumSpanningTree();
      view.setTree(tree);
      view.setPath(null); // repaint is invoked
    }); 
    
    btAllSP.setOnAction(e -> {
      try {
        int u = Integer.parseInt(tfStartVertex.getText().trim());
              if (u < 0 || u >= list.size())
                lblStatus.setText("Vertex " + u + " is not in the graph");

        updateGraph();
        UnweightedGraph<Vertex>.SearchTree tree = graph.getShortestPath(u);

        view.setTree(tree);
        view.repaint();
     }
     catch (Exception ex) {
       ex.printStackTrace();   		
     }
    });    
    
    btShortestPath.setOnAction(e -> {
      	try {
          int u = Integer.parseInt(tfStartVertex1.getText().trim());
	        if (u < 0 || u >= list.size())
	          lblStatus.setText(
	            "Vertex " + u + " is not in the graph");

	        int v = Integer.parseInt(tfEndVertex1.getText().trim());
	        if (v < 0 || v >= list.size())
	          lblStatus.setText("Vertex " + v + " is not in the graph");
          
          updateGraph();
          UnweightedGraph<Vertex>.SearchTree tree = graph.getShortestPath(u);
          List<Vertex> path = tree.getPath(v);
          
          view.setTree(null);
          view.setPath(path);
          view.repaint();
       }
       catch (Exception ex) {
         ex.printStackTrace();   		
  	   }
    });    
  }
  
  private void updateGraph() {
    ArrayList<WeightedEdge> listOfEdges = new ArrayList<>();
    for (int i = 0; i < edges.size(); i++) {
      int x = list.indexOf(edges.get(i).u);
      int y = list.indexOf(edges.get(i).v);
      int w = (int)Vertex.getDistance(edges.get(i).u, edges.get(i).v);
      listOfEdges.add(new WeightedEdge(x, y, w));
      listOfEdges.add(new WeightedEdge(y, x, w));
    }
   
    graph = new WeightedGraph<Vertex>(list, listOfEdges);
  }

  class GraphView extends Pane {
    private Vertex startV = null;
    private boolean isLineOn = false;
    private double endOfLineX, endOfLineY;
    private UnweightedGraph<Vertex>.SearchTree tree = null;
    private List<Vertex> path = null;
    private Line line = new Line();
    
    GraphView() {
      setOnMousePressed(e -> {
        if (e.getButton() == MouseButton.PRIMARY) {
                  Vertex c = getContainingVertex(e.getX(), e.getY());
          if (c != null) {
            if (!isLineOn) {
              startV = c;
              isLineOn = true;
              line.setStartX(e.getX());
              line.setStartY(e.getY());
              line.setEndX(e.getX());
              line.setEndY(e.getY());   
            }
          }
          else {
            list.add(new Vertex(e.getX(), e.getY()));
            view.setTree(null); // repaint is invoked
          }
        } 
        else if (e.getButton() == MouseButton.SECONDARY) {
          // remove a vertex
          Vertex c = getContainingVertex(e.getX(), e.getY());
          if (c != null) {
            list.remove(c);
            removeAdjacentEdges(c);
            view.setTree(null); // repaint is invoked
          }
        }
      });

      setOnMouseReleased(e -> {
        Vertex c = getContainingVertex(e.getX(), e.getY());
        if (isLineOn && c != null && !c.equals(startV)) {
          // Add a new edge
          edges.add(new Edge1(startV, c));
          view.setTree(null); // repaint is invoked
          view.setPath(null); // repaint is invoked
        }

        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(0);
        line.setEndY(0);
        
        isLineOn = false;
        repaint();
      });

      setOnMouseDragged(e -> {
        if (e.isControlDown()) {
          isLineOn = false;
          Vertex c = getContainingVertex(e.getX(), e.getY());
          if (c != null) {
            c.setX(e.getX());
            c.setY(e.getY());
            view.setTree(null); // repaint is invoked
            view.setPath(null); // repaint is invoked
          }
        } else if (isLineOn) {
          line.setEndX(e.getX());
          line.setEndY(e.getY());
          // repaint();
        }
      });
    }

    public void setTree(UnweightedGraph<Vertex>.SearchTree tree) {
      this.tree = tree;
      repaint();
    }

    public void setPath(List<Vertex> path) {
      this.path = path;
      repaint();
    }

    boolean isTooCloseToVertex(double x, double y) {
      for (int i = 0; i < list.size(); i++) {
        if (Vertex.getDistance(list.get(i).getX(), list.get(i).getY(), x, y) <= 2 * Vertex.RADIUS + 5) {
          return true;
        }
      }

      return false;
    }

    Vertex getContainingVertex(double x, double y) {
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).contains(x, y)) {
          return list.get(i);
        }
      }

      return null;
    }

    void removeAdjacentEdges(Vertex vertex) {
      for (int i = 0; i < edges.size(); i++) {
        if (edges.get(i).u.equals(vertex) || edges.get(i).v.equals(vertex)) {
          edges.remove(i--);
        }
      }
    }

    protected void repaint() {
      getChildren().clear();
      
      // Draw a line
      // if (isLineOn)
        getChildren().add(line);

      // Draw edges
      for (int i = 0; i < edges.size(); i++) {
        double x1 = edges.get(i).u.getX();
        double y1 = edges.get(i).u.getY();
        double x2 = edges.get(i).v.getX();
        double y2 = edges.get(i).v.getY();
        
        getChildren().addAll(new Line(x1, y1, x2, y2));
        
        // Draw distance
        double distance = Vertex.getDistance(x1, y1, x2, y2);
        getChildren().addAll(new Text((x1 + x2) / 2, (y1 + y2) / 2, (int)distance + ""));
      }

      drawInstructions(20, 20);

      // Highlight the edges in the spanning tree
      if (tree != null) {
        for (int i = 0; i < graph.getSize(); i++) {
          if (tree.getParent(i) != -1) {
            int v = tree.getParent(i);
            double x1 = graph.getVertex(i).getX();
            double y1 = graph.getVertex(i).getY();
            double x2 = graph.getVertex(v).getX();
            double y2 = graph.getVertex(v).getY();

            drawArrowLine(x2, y2, x1, y1, Vertex.RADIUS, this);
          }
        }
      } 
      else if (path != null) {
        // Display the path    
        for (int i = 1; i < path.size(); i++) {
          double x1 = path.get(i).getX();
          double y1 = path.get(i).getY();
          double x2 = path.get(i - 1).getX();
          double y2 = path.get(i - 1).getY();
          drawArrowLine(x1, y1, x2, y2, Vertex.RADIUS, this);
        }
      }
      
      // Draw nodes
      for (int i = 0; i < list.size(); i++) {
        Circle circle = new Circle(list.get(i).getX(), list.get(i).getY(), Vertex.RADIUS);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(list.get(i).getX() - 4, list.get(i).getY() + 4, i + ""));
      }
    }

    final String[] instructions = {"INSTRUCTIONS", "Add:", "Left Click",
      "Move:", "Ctrl Drag", "Connect:", "Drag", "Remove:", "Right Click"};

    void drawInstructions(int x, int y) {
      Rectangle rectangle = new Rectangle(x, y, x + 150, y + 90);
      rectangle.setFill(Color.WHITE);
      rectangle.setStroke(Color.BLACK);
      getChildren().add(rectangle);
      getChildren().add(new Text(x + 10, y + 20, instructions[0]));
      for (int i = 1; i < instructions.length; i = i + 2) {
        getChildren().add(new Text(x + 10, y + 20 + (i + 1) * 10, instructions[i]));
        getChildren().add(new Text(x + 100, y + 20 + (i + 1) * 10, instructions[i + 1]));
      }
    }
  }

  /**
   * Connect two circles centered at (x1, y1) and (x2, y2)
   */
  private static void drawArrowLine(
          double x1, double y1, double x2, double y2, double radius, Pane pane) {
    double d = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    int x11 = (int) (x1 - radius * (x1 - x2) / d);
    int y11 = (int) (y1 - radius * (y1 - y2) / d);
    int x21 = (int) (x2 + radius * (x1 - x2) / d);
    int y21 = (int) (y2 + radius * (y1 - y2) / d);
    drawArrowLine(x11, y11, x21, y21, pane);
  }

  public static void drawArrowLine(double x1, double y1, 
      double x2, double y2, Pane pane) {
    Line line1 = new Line(x1, y1, x2, y2);
    line1.setStroke(Color.RED);
    pane.getChildren().add(line1);
    
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
    Line line2 = new Line(x2, y2, (x2 + (Math.cos(arctan + set45) * arrlen)),
      ((y2)) + (Math.sin(arctan + set45) * arrlen));
    line2.setStroke(Color.RED);
    pane.getChildren().add(line2);

    Line line3 = new Line(x2, y2, (x2 + (Math.cos(arctan - set45) * arrlen)),
      ((y2)) + (Math.sin(arctan - set45) * arrlen));
    line3.setStroke(Color.RED);
    pane.getChildren().add(line3);
  }

  static class Vertex {
    final static int RADIUS = 20;
    double x, y;

    public Vertex() {
    }

    public Vertex(double x, double y) {
      this.x = x;
      this.y = y;
    }

    public Vertex(Point p) {
      this(p.x, p.y);
    }

    public double getX() {
      return x;
    }

    public void setX(double x) {
      this.x = x;
    }

    public double getY() {
      return y;
    }

    public void setY(double y) {
      this.y = y;
    }

    public boolean equals(Object o) {
      Vertex c = (Vertex) o;
      return c.getX() == x && c.getY() == y;
    }

    public double getDistance(Vertex c) {
      return getDistance(x, y, c.x, c.y);
    }

    public static double getDistance(Vertex c1, Vertex c2) {
      return getDistance(c1.x, c1.y, c2.x, c2.y);
    }

    public static double getDistance(double x1, double y1, double x2, double y2) {
      return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public boolean contains(double x1, double y1) {
      return getDistance(x, y, x1, y1) <= RADIUS;
    }
  }

  class Edge1 {
    Vertex u, v;

    public Edge1(Vertex u, Vertex v) {
      this.u = u;
      this.v = v;
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
