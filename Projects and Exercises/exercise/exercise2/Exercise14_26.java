import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise14_26 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    double paneWidth = 200;
    double paneHeight = 200;  
    
    ClockPane clock1 = new ClockPane(4, 20, 45);
    ClockPane clock2 = new ClockPane(22, 46, 15);
    
    GridPane pane = new GridPane();
    pane.add(clock1, 0, 0);
    pane.add(clock2, 1, 0);
    
    clock1.setWidth(250);
    clock1.setHeight(250);
    clock2.setWidth(250);
    clock2.setHeight(250);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_26"); // Set the stage title
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
