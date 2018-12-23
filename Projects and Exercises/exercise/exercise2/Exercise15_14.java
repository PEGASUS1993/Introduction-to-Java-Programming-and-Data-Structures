import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_14 extends Application {  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    Pane pane = new Pane();
    Polygon polygon = new Polygon();
    polygon.getPoints().addAll(40.0, 20.0, 70.0, 40.0, 
      60.0, 80.0, 45.0, 45.0, 20.0, 60.0);
    polygon.setFill(Color.WHITE);
    polygon.setStroke(Color.BLACK);
    Text text = new Text();
    pane.getChildren().addAll(polygon, text);
  
    pane.setOnMouseMoved(e -> { 
      if (polygon.contains(e.getX(), e.getY())) {
        text.setText("Mouse point is inside the polygon"); 
      }
      else {
        text.setText("Mouse point is outside the polygon"); 
      }
      
      text.setX(e.getX());
      text.setY(e.getY());
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 250);
    primaryStage.setTitle("Exercise15_14"); // Set the stage title
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
