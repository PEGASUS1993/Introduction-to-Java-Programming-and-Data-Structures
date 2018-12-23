import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_25 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Pane pane = new Pane();
       
    Polyline polyline1 = new Polyline();
    ObservableList<Double> list1 = polyline1.getPoints();
    polyline1.setStroke(Color.RED);
    double scaleFactor = 60;    
    for (int x = -170; x <= 170; x++) {
      list1.add(x + 200.0);
      list1.add(100 - scaleFactor * Math.sin((x / 100.0) * 2 * Math.PI));
    }

    Line line1 = new Line(10, 100, 390, 100);
    Line line2 = new Line(200, 10, 200, 200);

    // Draw arrows
    Line line3 = new Line(390, 100, 370, 90);
    Line line4 = new Line(390, 100, 370, 110);
    Line line5 = new Line(200, 10, 190, 30);
    Line line6 = new Line(200, 10, 210, 30);

    // Draw x, y axis labels
    Text text1 = new Text(380, 70, "X");
    Text text2 = new Text(220, 20, "Y");
    
    // Draw -2pi
    Text text3 = new Text(140, 115, "-\u03c0");
    Text text4 = new Text(250, 115, "\u03c0");
    Text text5 = new Text(77, 115, "-2\u03c0");
    Text text6 = new Text(285, 115, "2\u03c0");
    Text text7 = new Text(200, 115, "0");

    Circle circle = new Circle(0, 0, 10);
    circle.setFill(Color.GREEN);
    
    pane.getChildren().addAll(polyline1, line1, line2, 
      line3, line4, line5, line6, text1, text2, text3,
      text4, text5, text6, text7, circle); 

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_25"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    PathTransition pt = new PathTransition();
    pt.setDuration(Duration.millis(4000));
    pt.setPath(polyline1);
    pt.setNode(circle);
    pt.setOrientation(
      PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    pt.setCycleCount(Timeline.INDEFINITE);
    pt.setAutoReverse(true);
    pt.play(); // Start animation 
    
    pane.setOnMousePressed(e -> {if (e.getButton() == MouseButton.PRIMARY) pt.pause();});
    pane.setOnMouseReleased(e -> {if (e.getButton() == MouseButton.SECONDARY) pt.play();});
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
