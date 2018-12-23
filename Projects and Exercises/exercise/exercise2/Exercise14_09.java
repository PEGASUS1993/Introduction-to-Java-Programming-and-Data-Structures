import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise14_09 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    GridPane pane = new GridPane();
            
    pane.add(new MyFan(), 0, 0);
    pane.add(new MyFan(), 1, 0);
    pane.add(new MyFan(), 0, 1);
    pane.add(new MyFan(), 1, 1);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("Exercise14_09"); // Set the stage title
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

class MyFan extends Pane { 
  public MyFan() {
    this.setPrefSize(1500, 1500); 
  }
  
  private void paint() {
    double radius = Math.min(getWidth(),  getHeight()) * 0.8 / 2;
    double fanRadius = radius * 0.9; 
    double centerX = getWidth() / 2;
    double centerY = getHeight() / 2;
    
    Circle circle = new Circle(centerX, centerY, radius);
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.WHITE);
    Arc arc1 = new Arc(centerX, centerY, fanRadius, fanRadius, 30, 35);
    arc1.setFill(Color.RED); // Set fill color
    arc1.setType(ArcType.ROUND);
    Arc arc2 = new Arc(centerX, centerY, fanRadius, fanRadius, 30 + 90, 35);
    arc2.setFill(Color.RED); // Set fill color
    arc2.setType(ArcType.ROUND);
    Arc arc3 = new Arc(centerX, centerY, fanRadius, fanRadius, 30 + 180, 35);
    arc3.setFill(Color.RED); // Set fill color
    arc3.setType(ArcType.ROUND);
    Arc arc4 = new Arc(centerX, centerY, fanRadius, fanRadius, 30 + 270, 35);
    arc4.setFill(Color.RED); // Set fill color
    arc4.setType(ArcType.ROUND);

    getChildren().clear();    
    getChildren().addAll(circle, arc1, arc2, arc3, arc4);    
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