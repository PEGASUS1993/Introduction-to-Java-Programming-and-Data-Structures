import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise14_16 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Pane pane = new Pane();
       
    Line line1 = new Line(0, 0, 0, 0);    
    line1.startYProperty().bind(pane.heightProperty().divide(3));
    line1.endXProperty().bind(pane.widthProperty());
    line1.endYProperty().bind(pane.heightProperty().divide(3));
    line1.setStroke(Color.BLUE);
    
    Line line2 = new Line(0, 0, 0, 0);    
    line2.startYProperty().bind(pane.heightProperty().multiply(2).divide(3));
    line2.endXProperty().bind(pane.widthProperty());
    line2.endYProperty().bind(pane.heightProperty().multiply(2).divide(3));
    line2.setStroke(Color.BLUE);    
    
    Line line3 = new Line(0, 0, 0, 0);    
    line3.startXProperty().bind(pane.widthProperty().divide(3));
    line3.endXProperty().bind(pane.widthProperty().divide(3));
    line3.endYProperty().bind(pane.heightProperty());
    line3.setStroke(Color.RED);

    Line line4 = new Line(0, 0, 0, 0);    
    line4.startXProperty().bind(pane.widthProperty().multiply(2).divide(3));
    line4.endXProperty().bind(pane.widthProperty().multiply(2).divide(3));
    line4.endYProperty().bind(pane.heightProperty());
    line4.setStroke(Color.RED);    
    
    pane.getChildren().addAll(line1, line2, line3, line4);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("Exercise14_16"); // Set the stage title
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
