import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_20 extends Application {  
  private double radius = 10;
  private Circle[] circle = {new Circle(40, 40, 10),
    new Circle(140, 40, 10), new Circle(60, 140, 10)};
  private Line line1 = new Line();
  private Line line2 = new Line();
  private Line line3 = new Line();  
  private Text[] text = {new Text(), new Text(), new Text()};
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {  
    Pane pane = new Pane();    
    setLines();
    pane.getChildren().addAll(circle[0], circle[1], circle[2],
      line1, line2, line3, text[0], text[1], text[2]);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_20"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage 

    circle[0].setOnMouseDragged(e -> { 
      if (circle[0].contains(e.getX(), e.getY())) {
        // Recompute and display angles
        circle[0].setCenterX(e.getX());
        circle[0].setCenterY(e.getY());
        setLines();
      }
    });

    circle[1].setOnMouseDragged(e -> { 
      if (circle[1].contains(e.getX(), e.getY())) {
        // Recompute and display angles
        circle[1].setCenterX(e.getX());
        circle[1].setCenterY(e.getY());
        setLines();
      }
    });
    
    circle[2].setOnMouseDragged(e -> { 
      if (circle[2].contains(e.getX(), e.getY())) {
        // Recompute and display angles
        circle[2].setCenterX(e.getX());
        circle[2].setCenterY(e.getY());
        setLines();
      }
    });
  }

  private void setLines() {
    line1.setStartX(circle[0].getCenterX());
    line1.setStartY(circle[0].getCenterY());
    line1.setEndX(circle[1].getCenterX());
    line1.setEndY(circle[1].getCenterY());
    line2.setStartX(circle[0].getCenterX());
    line2.setStartY(circle[0].getCenterY());
    line2.setEndX(circle[2].getCenterX());
    line2.setEndY(circle[2].getCenterY());
    line3.setStartX(circle[1].getCenterX());
    line3.setStartY(circle[1].getCenterY());
    line3.setEndX(circle[2].getCenterX());
    line3.setEndY(circle[2].getCenterY());
    
    // Compute angles
    double a = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
            distance(circle[1].getCenterX(), circle[1].getCenterY());
    double b = new Point2D(circle[2].getCenterX(), circle[2].getCenterY()).
            distance(circle[0].getCenterX(), circle[0].getCenterY());
    double c = new Point2D(circle[1].getCenterX(), circle[1].getCenterY()).
            distance(circle[0].getCenterX(), circle[0].getCenterY());     
    double[] angle = new double[3];
    angle[0] = Math.acos((a * a - b * b - c * c) / (-2 * b * c));
    angle[1] = Math.acos((b * b - a * a - c * c) / (-2 * a * c));
    angle[2] = Math.acos((c * c - b * b - a * a) / (-2 * a * b));

    for (int i = 0; i < 3; i++) {
      text[i].setX(circle[i].getCenterX());
      text[i].setY(circle[i].getCenterY() - radius);
      text[i].setText(String.format("%.2f", Math.toDegrees(angle[i])));
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
