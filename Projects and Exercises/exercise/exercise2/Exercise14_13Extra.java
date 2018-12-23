import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise14_13Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    GridPane pane = new GridPane();
            
    ClockPane clock1 = new ClockPane();
    clock1.setPrefSize(1000, 1000); 
    ClockPane clock2 = new ClockPane();
    clock2.setPrefSize(1000, 1000); 
    ClockPane clock3 = new ClockPane();
    clock3.setPrefSize(1000, 1000); 
    ClockPane clock4 = new ClockPane();
    clock4.setPrefSize(1000, 1000); 
    
    pane.add(clock1, 0, 0);
    pane.add(clock2, 1, 0);
    pane.add(clock3, 0, 1);
    pane.add(clock4, 1, 1);
   
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("Test"); // Set the stage title
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