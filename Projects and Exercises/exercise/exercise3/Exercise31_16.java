import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise31_16 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Group group = new Group();
        
    Ellipse ellipse = new Ellipse(100, 40, 50, 20);
    ellipse.setFill(Color.WHITE);
    ellipse.setStroke(Color.BLACK);

    Arc arc1 = new Arc(100, 140, 50, 20, 0, 180);
    arc1.setFill(Color.WHITE);
    arc1.setStroke(Color.BLACK);
    arc1.getStrokeDashArray().addAll(6d, 21d);
    
    Arc arc2 = new Arc(100, 140, 50, 20, 180, 180);
    arc2.setFill(Color.WHITE);
    arc2.setStroke(Color.BLACK);
   
    group.getChildren().addAll(ellipse, arc1, arc2, 
      new Line(50, 40, 50, 140), new Line(150, 40, 150, 140));

    // Create a scene and place it in the stage
    Scene scene = new Scene(new BorderPane(group), 200, 200);
    primaryStage.setTitle("Exercise31_16"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /**
   * The main method is only needed for the IDE 
   * with limited JavaFX support. Not needed for NetBeans or for
   * running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
