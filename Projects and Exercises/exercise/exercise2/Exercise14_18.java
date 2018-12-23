import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_18 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    Polyline polyline = new Polyline();
    ObservableList<Double> list = polyline.getPoints();
    
    double scaleFactor = 0.0125;
    for (int x = -100; x <= 100; x++) {
      list.add(x + 200.0);
      list.add(200 - scaleFactor * x * x);
    }

    Line line1 = new Line(10, 200, 390, 200);
    Line line2 = new Line(200, 30, 200, 390);

    // Draw arrows
    Line line3 = new Line(390, 200, 370, 190);
    Line line4 = new Line(390, 200, 370, 210);
    Line line5 = new Line(200, 30, 190, 50);
    Line line6 = new Line(200, 30, 210, 50);

    // Draw x, y axis labels
    Text text1 = new Text(380, 170, "X");
    Text text2 = new Text(220, 40, "Y");
    
    Group group = new Group();
    group.getChildren().addAll(polyline, line1, line2, 
        line3, line4, line5, line6, text1, text2); 

    // Create a scene and place it in the stage
    Scene scene = new Scene(new BorderPane(group), 400, 250);
    primaryStage.setTitle("Exercise14_18"); // Set the stage title
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
