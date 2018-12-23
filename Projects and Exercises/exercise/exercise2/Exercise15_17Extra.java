import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;    

public class Exercise15_17Extra extends Application {
  private Circle points[] = {new Circle(), new Circle(), new Circle(), new Circle()};
  private Circle intersectingPoint = new Circle(5);
  private Text text1 = new Text();
  private Text text2 = new Text();
  private Text text3 = new Text();
  private Text text4 = new Text();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {         
    double paneWidth = 470;
    double paneHeight = 410;  
    
    for (Circle p: points) {
      p.setRadius(4);
      p.setFill(Color.BLACK);
    }
    points[0].setCenterX(20);
    points[0].setCenterY(20);
    points[1].setCenterX(256);
    points[1].setCenterY(330);
    points[2].setCenterX(300);
    points[2].setCenterY(20);
    points[3].setCenterX(16);
    points[3].setCenterY(130);
    
    Line edge1 = new Line(points[0].getCenterX(), 
        points[0].getCenterY(), points[2].getCenterX(), points[2].getCenterY());
    Line edge2 = new Line(points[2].getCenterX(), 
        points[2].getCenterY(), points[1].getCenterX(), points[1].getCenterY());
    Line edge3 = new Line(points[1].getCenterX(), 
        points[1].getCenterY(), points[3].getCenterX(), points[3].getCenterY());
    Line edge4 = new Line(points[3].getCenterX(), 
        points[3].getCenterY(), points[0].getCenterX(), points[0].getCenterY());
    
    Line line1 = new Line(points[0].getCenterX(), 
      points[0].getCenterY(), points[1].getCenterX(), points[1].getCenterY());
    Line line2 = new Line(points[2].getCenterX(), 
      points[2].getCenterY(), points[3].getCenterX(), points[3].getCenterY());
    
    intersectingPoint.setFill(Color.RED);
    redisplayArea();
    
    Pane pane = new Pane();
    pane.getChildren().addAll(points[0], points[1], points[2], points[3],
      edge1, edge2, edge3, edge4, line1, line2, intersectingPoint, text1, text2, text3, text4); 
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise15_17"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
      
    points[0].setOnMouseDragged(e -> {
      points[0].setCenterX(e.getX());
      points[0].setCenterY(e.getY());
      
      edge1.setStartX(e.getX());
      edge1.setStartY(e.getY());
      edge4.setEndX(e.getX());
      edge4.setEndY(e.getY());
     
      line1.setStartX(e.getX());
      line1.setStartY(e.getY());
      redisplayArea();   
    });
    
    points[1].setOnMouseDragged(e -> {
      points[1].setCenterX(e.getX());
      points[1].setCenterY(e.getY());
      
      edge3.setStartX(e.getX());
      edge3.setStartY(e.getY());
      edge2.setEndX(e.getX());
      edge2.setEndY(e.getY());
      
      line1.setEndX(e.getX());
      line1.setEndY(e.getY());
      double[] temp1 = getIntersectingPoint(points);
      intersectingPoint.setCenterX(temp1[0]);
      intersectingPoint.setCenterY(temp1[1]);      
      
      redisplayArea();
    });

    points[2].setOnMouseDragged(e -> {
      points[2].setCenterX(e.getX());
      points[2].setCenterY(e.getY());
      
      edge2.setStartX(e.getX());
      edge2.setStartY(e.getY());
      edge1.setEndX(e.getX());
      edge1.setEndY(e.getY());
      
      line2.setStartX(e.getX());
      line2.setStartY(e.getY());
      redisplayArea();    
    });

    points[3].setOnMouseDragged(e -> {
      points[3].setCenterX(e.getX());
      points[3].setCenterY(e.getY());
      
      edge4.setStartX(e.getX());
      edge4.setStartY(e.getY());
      edge3.setEndX(e.getX());
      edge3.setEndY(e.getY());
      
      line2.setEndX(e.getX());
      line2.setEndY(e.getY());    
      redisplayArea();
    });
  }
  
  private void redisplayArea() {
    double[] temp3 = getIntersectingPoint(points);
    intersectingPoint.setCenterX(temp3[0]);
    intersectingPoint.setCenterY(temp3[1]);   
    
    Point2D p1 = new Point2D(points[0].getCenterX(), points[0].getCenterY());
    Point2D p3 = new Point2D(points[1].getCenterX(), points[1].getCenterY());
    Point2D p2 = new Point2D(points[2].getCenterX(), points[2].getCenterY());
    Point2D p4 = new Point2D(points[3].getCenterX(), points[3].getCenterY());
    Point2D p5 = new Point2D(intersectingPoint.getCenterX(), intersectingPoint.getCenterY());

    // Compute the areas of three triangles
    double area1 = Exercise09_03Extra.getTriangleArea(p1, p2, p5);
    double area2 = Exercise09_03Extra.getTriangleArea(p1, p2, p5);
    double area3 = Exercise09_03Extra.getTriangleArea(p1, p2, p5);
    double area4 = Exercise09_03Extra.getTriangleArea(p1, p2, p5);
    
    Point2D center1 = Exercise09_02Extra.getCenterPoint(p1, p2, p5);
    if (center1 != null) {
      text1.setX(center1.getX() - 10);
      text1.setY(center1.getY());
      text1.setText(String.format("%.2f", area1));
    }
    
    Point2D center2 = Exercise09_02Extra.getCenterPoint(p2, p3, p5);
    if (center1 != null) {
      text2.setX(center2.getX() - 10);
      text2.setY(center2.getY());
      text2.setText(String.format("%.2f", area2));
    }
    
    Point2D center3 = Exercise09_02Extra.getCenterPoint(p3, p4, p5);
    if (center1 != null) {
      text3.setX(center3.getX() - 20);
      text3.setY(center3.getY());
      text3.setText(String.format("%.2f", area3));
    }
    
    Point2D center4 = Exercise09_02Extra.getCenterPoint(p4, p1, p5);
    if (center4 != null) {
      text4.setX(center4.getX() - 10);
      text4.setY(center4.getY());
      text4.setText(String.format("%.2f", area4));
    }
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
}
