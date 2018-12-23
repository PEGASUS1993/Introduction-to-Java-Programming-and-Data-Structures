import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise15_22 extends Application {
  private double paneWidth = 200;
  private double paneHeight = 200;
  
  Ellipse ellipse = new Ellipse(100, 40, 50, 20);
  Arc arc1 = new Arc(100, 140, 50, 20, 0, 180);
  Arc arc2 = new Arc(100, 140, 50, 20, 180, 180);
  Line line1 = new Line(50, 40, 50, 140);
  Line line2 = new Line(150, 40, 150, 140);
          
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Pane pane = new Pane();

    ellipse.setFill(Color.WHITE);
    ellipse.setStroke(Color.BLACK);

    arc1.setFill(Color.WHITE);
    arc1.setStroke(Color.BLACK);
    arc1.getStrokeDashArray().addAll(6d, 21d);
    
    arc2.setFill(Color.WHITE);
    arc2.setStroke(Color.BLACK);
   
    pane.getChildren().addAll(ellipse, arc1, arc2, line1, line2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("Exercise15_22"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    scene.widthProperty().addListener(ov -> {
      paneWidth = pane.getWidth();
      setValues(); 
    });
    
    scene.heightProperty().addListener(ov -> {
      paneHeight = pane.getHeight();
      setValues(); 
    });
  }
  
  private void setValues() {
    ellipse.setCenterX(paneWidth / 2);
    ellipse.setRadiusX(paneWidth / 4);
    
    arc1.setCenterX(paneWidth / 2);
    arc1.setCenterY(paneHeight - 40);
    arc1.setRadiusX(paneWidth / 4);
    
    arc2.setCenterX(paneWidth / 2);
    arc2.setCenterY(paneHeight - 40);
    arc2.setRadiusX(paneWidth / 4);

    line1.setStartX(paneWidth / 4);
    line1.setEndX(paneWidth / 4);
    line1.setEndY(paneHeight - 40);  

    line2.setStartX(paneWidth / 2 + paneWidth / 4);
    line2.setEndX(paneWidth / 2 + paneWidth / 4);  
    line2.setEndY(paneHeight - 40);      
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
