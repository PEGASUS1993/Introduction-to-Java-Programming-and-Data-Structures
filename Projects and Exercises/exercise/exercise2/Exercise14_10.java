import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise14_10 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    // Create a scene and place it in the stage
    Scene scene = new Scene(new MyCylinder(), 200, 200);
    primaryStage.setTitle("Exercise14_10"); // Set the stage title
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

class MyCylinder extends Pane {
  public void paint() {       
    double centerX = getWidth() / 2;
    double radiusX = getWidth() * 0.8 / 2;
    
    Ellipse ellipse = new Ellipse(centerX, 40, radiusX, 20);
    ellipse.setFill(Color.WHITE);
    ellipse.setStroke(Color.BLACK);

    Arc arc1 = new Arc(centerX, 140, radiusX, 20, 0, 180);
    arc1.setFill(Color.WHITE);
    arc1.setStroke(Color.BLACK);
    arc1.getStrokeDashArray().addAll(6.0, 21.0);
    
    Arc arc2 = new Arc(centerX, 140, radiusX, 20, 180, 180);
    arc2.setFill(Color.WHITE);
    arc2.setStroke(Color.BLACK);
   
    getChildren().clear();
    getChildren().addAll(ellipse, arc1, arc2, 
      new Line(centerX - radiusX, 40, centerX - radiusX, 140), 
      new Line(centerX + radiusX, 40, centerX + radiusX, 140));
  }
  
  @Override
  public void setWidth(double width) {
    super.setWidth(width);
    paint();
  }
  
  @Override
  public void setHeight(double height) {
    super.setHeight(height);
    paint();
  }
}