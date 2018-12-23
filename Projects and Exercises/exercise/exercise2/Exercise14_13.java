import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_13 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Arc arc1 = new Arc(100, 100, 80, 80, 0, 360 * 0.2);
    arc1.setFill(Color.RED);
    arc1.setType(ArcType.ROUND);
    Text text1 = new Text(110, 80, "Project -- 20%");
    
    Arc arc2 = new Arc(100, 100, 80, 80, 360 * 0.2, 360 * 0.1);
    arc2.setFill(Color.BLUE);
    arc2.setType(ArcType.ROUND);
    Text text2 = new Text(80, 15, "Quiz -- 10%");

    Arc arc3 = new Arc(100, 100, 80, 80, 360 * 0.2 + 360 * 0.1, 360 * 0.3);
    arc3.setFill(Color.GREEN);
    arc3.setType(ArcType.ROUND);
    Text text3 = new Text(5, 100, "Midterm -- 30%");

    Arc arc4 = new Arc(100, 100, 80, 80, 360 * 0.2 + 360 * 0.1 + 360 * 0.3, 360 * 0.4);
    arc4.setFill(Color.ORANGE);
    arc4.setType(ArcType.ROUND);
    Text text4 = new Text(100, 180, "Final -- 40%");

    Group group = new Group();
    group.getChildren().addAll(arc1, text1, arc2, text2, arc3, text3, arc4, text4);

    // Create a scene and place it in the stage
    Scene scene = new Scene(new BorderPane(group), 500, 500);
    primaryStage.setTitle("Exercise14_13"); // Set the stage title
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
