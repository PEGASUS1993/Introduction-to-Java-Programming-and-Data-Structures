import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_02Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {           
    Pane pane = new Pane();
    double paneWidth = 200;
    double paneHeight = 200;  
    
    double RADIUS = 10;    
    Circle[] circles = new Circle[4];
    for (int i = 0; i < 4; i++) {
      circles[i] = new Circle(Math.random() * (paneWidth - 20) + 10,
        Math.random() * (paneHeight - 20) + 10, RADIUS);
      circles[i].setFill(Color.WHITE);
      circles[i].setStroke(Color.BLACK);
    }
    
    for (int i = 0; i < 4; i++) {   
      for (int j = i; j < 4; j++) { 
        pane.getChildren().add(
          new Line(circles[i].getCenterX(), circles[i].getCenterY(), 
            circles[j].getCenterX(), circles[j].getCenterY()));
      }
    }
 
    for (int i = 0; i < 4; i++) {  
      pane.getChildren().addAll(circles[i], 
        new Text(circles[i].getCenterX() - 6, 
            circles[i].getCenterY() + 4, "" + (i + 1)));
    }

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_02"); // Set the stage title
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
