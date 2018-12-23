import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise31_10 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {    
    Pane pane = new Pane();    
    pane.setTranslateX(50);
    pane.setTranslateY(70);
    pane.getChildren().add(new Line(-20, 0, 420, 0));
    pane.getChildren().add(new Line(0, 55, 0, -55));

    pane.getChildren().add(new Line(400, -10, 420, 0));
    pane.getChildren().add(new Line(400, 10, 420, 0));
    pane.getChildren().add(new Line(10, -35, 0, -55));
    pane.getChildren().add(new Line(-10, -35, 0, -55));     

    pane.getChildren().add(new Text(410, 30, "x"));
    pane.getChildren().add(new Text(-20, -40, "y"));

    Polyline polyline = new Polyline();
    double x1 = 0;
    double y1 = 50 * Math.sin(Math.toRadians(x1));
    for (double x2 = 1; x2 < 720; x2++) {
      double y2 = 50 * Math.sin(Math.toRadians(x1));
      polyline.getPoints().addAll(x1, y1);
      x1 = x2;
      y1 = y2;
    }
    
    polyline.setScaleX(0.5);
    polyline.setScaleY(-1);
    polyline.setTranslateX(-180);
    polyline.setTranslateY(0);
    pane.getChildren().add(polyline);
     
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 450, 150);
    primaryStage.setTitle("Exercise31_10"); // Set the stage title
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
} 
