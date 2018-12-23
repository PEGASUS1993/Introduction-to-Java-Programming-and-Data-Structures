import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.text.*;
import java.util.*;

public class Exercise15_16Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    // Create a scene and place it in the stage
    Scene scene = new Scene(new View(), 650, 350);
    primaryStage.setTitle("Exercise15_16"); // Set the stage title
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
  
  class View extends Pane {
    private ArrayList<Vertex> list = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();    
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
            edges.add(new Edge(startV, c));    
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
      
      drawVertices();
      drawEdges();
      
    }
    
    void drawVertices() {
      for (int i = 0; i < list.size(); i++) {
        getChildren().add(new Circle(list.get(i).getX(), list.get(i).getY(), Vertex.RADIUS));
      }
    }
 
    void drawEdges() {
      if (isLineOn) {
        getChildren().add(new Line(startV.getX(), startV.getY(), endOfLineX, endOfLineY));
      }
      
      for (int i = 0; i < edges.size(); i++) {
        getChildren().add(new Line(edges.get(i).u.getX(), edges.get(i).u.getY(), edges.get(i).v.getX(), edges.get(i).v.getY()));
      }
    }

    void drawInstruction(double x, double y) {
      getChildren().add(new Text(x, y + 20, instructions[0]));
      for (int i = 1; i < instructions.length; i = i + 2) {
        getChildren().add(new Text(x, y + 20 + (i + 1) * 10, instructions[i]));
        getChildren().add(new Text(x + 130, y + 20 + (i + 1) * 10, instructions[i + 1]));
      }
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
  
  class Edge {
    Vertex u, v;
    
    public Edge(Vertex u, Vertex v) {
      this.u = u;
      this.v = v;
    }
  }
}
