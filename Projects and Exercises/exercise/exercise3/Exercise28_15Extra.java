import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.text.*;
import java.util.*;

public class Exercise28_15Extra extends Application {
  private Button btDisplayLongestPath = new Button("Display a Longest Path");
  private Text text = new Text();
  private View view = new View();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    BorderPane pane = new BorderPane();
    pane.setCenter(view);
    
    HBox hBox1 = new HBox(5);
    hBox1.getChildren().addAll(btDisplayLongestPath, text);
    hBox1.setStyle("-fx-border-color: green");
       
    HBox hBox = new HBox(15);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(hBox1);
    
    pane.setBottom(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 950, 350);
    primaryStage.setTitle("Exercise28_15"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btDisplayLongestPath.setOnAction(e -> view.displayLongestPath());
  }
   
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
  
  class View extends Pane {
    private ArrayList<Vertex> list = new ArrayList<>();
    private ArrayList<Edge1> edges = new ArrayList<>();    
    private Vertex startV = null;
    private boolean isLineOn = false;
    private double endOfLineX, endOfLineY;
    final String[] instructions = { "INSTRUCTIONS", "Add a vertex:", "Left Click Mouse", "Remove a vertex:",
        "Right Click Mouse",
        "Move a Vertex:", "Move Mouse + Ctrl Button", "Connect:", "Click a vertex, move mouse,\nand click another vertex" };
    
    View() {
      repaint();
      
      setOnMouseClicked(e -> {
          if (e.getButton() == MouseButton.PRIMARY) {
            // Add a vertex
            if (!isTooCloseToVertex(e.getX(), e.getY())) {
              if (isLineOn) {
                isLineOn = false;
                repaint();
                return;
              }
              
              list.add(new Vertex(e.getX(), e.getY()));
              repaint(); return;
            }
          }
          else if (e.getButton() == MouseButton.SECONDARY) {
            // remove a vertex
            Vertex c = getContainingVertex(e.getX(), e.getY());
            if (c != null) {
              list.remove(c);
              removeAdjacentEdges(c);
              repaint(); return;
            }
          }         
          
          Vertex c = getContainingVertex(e.getX(), e.getY());
          if (!isLineOn && c != null) {
            startV = c;           
            endOfLineX = e.getX();
            endOfLineY = e.getY();
            isLineOn = true;          
          }
          
          if (isLineOn && c != null && !c.equals(startV)) {
            // Add a new edge
            edges.add(new Edge1(startV, c));    
            isLineOn = false;
            repaint();
          }
        });
    
      setOnMouseMoved(e -> {
        if (e.isControlDown()) {
          isLineOn = false;
          Vertex c = getContainingVertex(e.getX(), e.getY());
          if (c != null) {
            c.setX(e.getX());
            c.setY(e.getY());
            repaint();
          }       
        }
        else if (isLineOn) {
          endOfLineX = e.getX();
          endOfLineY = e.getY();
          repaint();
        }
      });
    }
    
    boolean isTooCloseToVertex(double x, double y) {
      for (int i = 0; i < list.size(); i++)
        if (Vertex.getDistance(list.get(i).getX(), list.get(i).getY(), x, y) <= 2 * Vertex.RADIUS + 20)
          return true;
      
      return false;
    }
    
    Vertex getContainingVertex(double x, double y) {
      for (int i = 0; i < list.size(); i++)
        if (list.get(i).contains(new Point2D(x, y)))
          return list.get(i);
      
      return null;
    }
    
    void removeAdjacentEdges(Vertex vertex) {
      for (int i = 0; i < edges.size(); i++)
        if (edges.get(i).u.equals(vertex) || edges.get(i).v.equals(vertex)) {
          edges.remove(i--); 
        }
    }
    
    void repaint() {
      getChildren().clear();
      
      drawInstruction(5, 5);
      drawEdges();      
      drawVertices();
    }
    
    void drawVertices() {
      for (int i = 0; i < list.size(); i++) {
        Circle c = new Circle(list.get(i).getX(), list.get(i).getY(), Vertex.RADIUS); 
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
        Text text = new Text(list.get(i).getX() - 2, list.get(i).getY() + 2, "" + i);
        getChildren().addAll(c, text);
      }
    }
 
    void drawEdges() {
      if (isLineOn) {
        getChildren().add(new Line(startV.getX(), startV.getY(), endOfLineX, endOfLineY));
      }
      
      for (int i = 0; i < edges.size(); i++) {
        getChildren().add(new Line(edges.get(i).u.getX(), edges.get(i).u.getY(), 
            edges.get(i).v.getX(), edges.get(i).v.getY()));
      }
    }

    void drawInstruction(double x, double y) {
      getChildren().add(new Text(x, y + 20, instructions[0]));
      for (int i = 1; i < instructions.length; i = i + 2) {
        getChildren().add(new Text(x, y + 20 + (i + 1) * 10, instructions[i]));
        getChildren().add(new Text(x + 130, y + 20 + (i + 1) * 10, instructions[i + 1]));
      }
    }
    
    public UnweightedGraphLongestPath<Vertex> getGraph() {     
      List<Edge> edges = new ArrayList<>();
      for (Edge1 e : this.edges) {
        int x = list.indexOf(e.u);
        int y = list.indexOf(e.v);
        edges.add(new Edge(x, y));
        edges.add(new Edge(y, x));
      }
      
      // Create a graph
      UnweightedGraphLongestPath<Vertex> graph = new UnweightedGraphLongestPath<>(list, edges);
      return graph;
    }
    
    public void displayLongestPath() {     
      List<Integer> path = getGraph().getLongestPath();

      text.setText(path.toString()); 
      
      repaint();
      for (int i = 0; path != null && i < path.size() - 1; i++) {
        drawArrowLine(list.get(path.get(i)).getX(), list.get(path.get(i)).getY(), 
            list.get(path.get(i + 1)).getX(), list.get(path.get(i + 1)).getY());
      }
    }
    
    public void displayShortestPath(int startingV, int endingV) {     
      UnweightedGraph<Vertex>.SearchTree tree = getGraph().bfs(startingV);

      repaint();
      
      List<Vertex> path = tree.getPath(endingV);
      for (Vertex v: path) {
        System.out.println(list.indexOf(v));
      }
      
      for (int i = 0; i < path.size() - 1; i++) {
        drawArrowLine(path.get(i + 1).getX(), path.get(i + 1).getY(), 
          path.get(i).getX(), path.get(i).getY());       
      }
    }

    
    public void drawArrowLine(double x1, double y1, 
        double x2, double y2) {
      double radius = Vertex.RADIUS;
      double d = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
      double x11 = x1 - radius * (x1 - x2) / d;
      double y11 = y1 - radius * (y1 - y2) / d;
      double x21 = x2 + radius * (x1 - x2) / d;
      double y21 = y2 + radius * (y1 - y2) / d;
      drawArrowLine(x11, y11, x21, y21, this);
    }
    
    public void drawArrowLine(double x1, double y1, 
        double x2, double y2, Pane pane) {
      Line line = new Line(x1, y1, x2, y2);
      line.setStroke(Color.RED);
      pane.getChildren().add(line);
      
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
          y2 + (Math.sin(arctan + set45) * arrlen));
      line2.setStroke(Color.RED);    
      pane.getChildren().add(line2);

      Line line3 = new Line(x2, y2, x2 + Math.cos(arctan - set45) * arrlen,
          y2 + Math.sin(arctan - set45) * arrlen);
      line3.setStroke(Color.RED);    
      pane.getChildren().add(line3);
    }
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
    
    public Vertex(Point2D p) {
      this(p.getX(), p.getY());
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
      Vertex c = (Vertex)o;
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
    
    public boolean contains(Point2D p) {
      return getDistance(x, y, p.getX(), p.getY()) <= RADIUS;
    }
  }
  
  class Edge1 {
    Vertex u, v;
    
    public Edge1(Vertex u, Vertex v) {
      this.u = u;
      this.v = v;
    }
  }
}
