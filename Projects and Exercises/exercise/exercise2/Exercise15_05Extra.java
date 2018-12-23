import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Exercise15_05Extra extends Application {
  private Circle points[] = {new Circle(), new Circle(), new Circle(), new Circle()};

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {         
    double paneWidth = 270;
    double paneHeight = 210;  
    
    for (Circle p: points) {
      p.setRadius(4);
      p.setFill(Color.BLACK);
    }
    points[0].setCenterX(20);
    points[0].setCenterY(20);
    points[1].setCenterX(56);
    points[1].setCenterY(130);
    points[2].setCenterX(100);
    points[2].setCenterY(20);
    points[3].setCenterX(16);
    points[3].setCenterY(130);
    
    Line line1 = new Line(points[0].getCenterX(), 
      points[0].getCenterY(), points[1].getCenterX(), points[1].getCenterY());
    Line line2 = new Line(points[2].getCenterX(), 
        points[2].getCenterY(), points[3].getCenterX(), points[3].getCenterY());
    
    double[] p = getIntersectingPoint(points);
    Circle intersectingPoint = new Circle(5);
    intersectingPoint.setFill(Color.RED);
    intersectingPoint.setCenterX(p[0]);
    intersectingPoint.setCenterY(p[1]);

    Pane pane = new Pane();
    pane.getChildren().addAll(points[0], points[1], points[2], points[3],
      line1, line2, intersectingPoint); 
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise15_05"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
      
    points[0].setOnMouseDragged(e -> {
      points[0].setCenterX(e.getX());
      points[0].setCenterY(e.getY());
      line1.setStartX(e.getX());
      line1.setStartY(e.getY());
      double[] temp0 = getIntersectingPoint(points);
      intersectingPoint.setCenterX(temp0[0]);
      intersectingPoint.setCenterY(temp0[1]);      
    });
    
    points[1].setOnMouseDragged(e -> {
      points[1].setCenterX(e.getX());
      points[1].setCenterY(e.getY());
      line1.setEndX(e.getX());
      line1.setEndY(e.getY());
      double[] temp1 = getIntersectingPoint(points);
      intersectingPoint.setCenterX(temp1[0]);
      intersectingPoint.setCenterY(temp1[1]);      
    });

    points[2].setOnMouseDragged(e -> {
      points[2].setCenterX(e.getX());
      points[2].setCenterY(e.getY());
      line2.setStartX(e.getX());
      line2.setStartY(e.getY());
      double[] temp2 = getIntersectingPoint(points);
      intersectingPoint.setCenterX(temp2[0]);
      intersectingPoint.setCenterY(temp2[1]);      
    });

    points[3].setOnMouseDragged(e -> {
      points[3].setCenterX(e.getX());
      points[3].setCenterY(e.getY());
      line2.setEndX(e.getX());
      line2.setEndY(e.getY());
      double[] temp3 = getIntersectingPoint(points);
      intersectingPoint.setCenterX(temp3[0]);
      intersectingPoint.setCenterY(temp3[1]);      
    });
  }
  
  public static double[] getIntersectingPoint(Circle[] points) {  
    double[][] pts = new double[4][2];
    pts[0][0] = points[0].getCenterX();
    pts[0][1] = points[0].getCenterY();
    pts[1][0] = points[1].getCenterX();
    pts[1][1] = points[1].getCenterY();
    pts[2][0] = points[2].getCenterX();
    pts[2][1] = points[2].getCenterY();
    pts[3][0] = points[3].getCenterX();
    pts[3][1] = points[3].getCenterY();
    
    return Exercise08_31.getIntersectingPoint(pts);
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
//
//    
//  class View extends JPanel {
//    private Vertex[] vertices = new Vertex[4]; 
//
//    View() {
//      // Create three initial vertices for the triangle
//      vertices[0] = new Vertex(20, 20);
//      vertices[1] = new Vertex(56, 130);
//      vertices[2] = new Vertex(100, 20);
//      vertices[3] = new Vertex(16, 130);
//      
//      addMouseMotionListener(new MouseMotionAdapter() {
//        public void mouseDragged(MouseEvent e) {
//          Vertex v = getContainingVertex(e.getPoint());
//          
//          if (v != null) {
//            // Add a vertex
//            v.x = e.getX(); v.y = e.getY();
//            repaint();
//          }         
//        }
//        
//        public void mouseMoved(MouseEvent e) {
//          Vertex v = getContainingVertex(e.getPoint());
//          
//          if (v != null) {
//            // Set a cross-hair cursor
//            Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
//            setCursor(cursor);
//          } 
//          else {
//            // Set the default cursor
//            Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
//            setCursor(cursor);            
//          }
//        }
//      });
//    }
//    
//    Vertex getContainingVertex(Point p) {
//      for (int i = 0; i < vertices.length; i++)
//        if (vertices[i].contains(p))
//          return vertices[i];
//      
//      return null;
//    }
//    
//    @Override
//    protected void paintComponent(Graphics g) {
//      super.paintComponent(g);
//      
//      double[][] points = new double[4][2];
//      for (int i = 0; i < points.length; i++) {
//        points[i][0] = vertices[i].x;
//        points[i][1] = vertices[i].y;
//      }
//      
//      double[] intersectingPoint = getIntersectingPoint(points);
//                 
//      // Display vertices
//      for (int i = 0; i < vertices.length; i++) {
//        g.fillOval(vertices[i].x - Vertex.RADIUS / 4, 
//          vertices[i].y - Vertex.RADIUS / 4, 2 * Vertex.RADIUS / 4, 2 * Vertex.RADIUS / 4);
//      }    
//        
//      // Draw intersecting point
//      g.setColor(Color.RED);
//      if (intersectingPoint != null)
//        g.fillOval((int)intersectingPoint[0] - Vertex.RADIUS / 4, (int)intersectingPoint[1] - Vertex.RADIUS / 4,
//          2 * Vertex.RADIUS / 4, 2 * Vertex.RADIUS / 4);
//      g.setColor(Color.BLACK);
//      
//      // Draw lines 
//      g.drawLine(vertices[0].x, vertices[0].y, vertices[1].x, vertices[1].y);
//      g.drawLine(vertices[2].x, vertices[2].y, vertices[3].x, vertices[3].y);
//    }
//  }
//  
//  public static double[] getIntersectingPoint(double[][] points) {
//    double[] p = Exercise07_31.getIntersectingPoint(points);
//    
//    if (Exercise05_39.onTheLineSegment(points[0][0], points[0][1], points[1][0], points[1][1], p[0], p[1])
//        && Exercise05_39.onTheLineSegment(points[2][0], points[2][1], points[3][0], points[3][1], p[0], p[1]))
//      return p;
//    
//    return p;
//  }
//
//  
//  static class Vertex {
//    final static int RADIUS = 10;
//    int x, y;
//    
//    public Vertex() {
//    }
//    
//    public Vertex(int x, int y) {
//      this.x = x;
//      this.y = y;
//    }
//    
//    public Vertex(Point p) {
//      this(p.x, p.y);
//    }
//
//    public int getX() {
//      return x;
//    }
//    
//    public void setX(int x) {
//      this.x = x;
//    }
//    
//    public int getY() {
//      return y;
//    }
//    
//    public void setY(int y) {
//      this.y = y;
//    }
//    
//    public boolean equals(Object o) {
//      Vertex c = (Vertex)o;
//      return c.getX() == x && c.getY() == y; 
//    }
//    
//    public double getDistance(Vertex c) {
//      return getDistance(x, y, c.x, c.y);
//    }
//    
//    public static double getDistance(Vertex c1, Vertex c2) {
//      return getDistance(c1.x, c1.y, c2.x, c2.y);
//    }
//    
//    public static double getDistance(double x1, double y1, double x2, double y2) {
//      return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
//    }
//    
//    public boolean contains(Point p) {
//      return getDistance(x, y, p.x, p.y) <= RADIUS;
//    }
//  }
}
