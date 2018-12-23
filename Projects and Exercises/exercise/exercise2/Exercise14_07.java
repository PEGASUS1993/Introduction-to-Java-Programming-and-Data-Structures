import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise14_07 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    GridPane pane = new GridPane();
        
    int k = 0;
    for (int i = 0; i < 6; i++) { 
      for (int j = 0; j < 9; j++) {
        pane.add(new ImageView("image/card/" + ++k + ".png"), j, i);
      }
    }
    
//    or 
//    for (int i = 0; i < 6; i++) { 
//        for (int j = 0; j < 9; j++) {
//          pane.add(new ImageView("image/card/" + (i * 9 + j + 1) + ".png"), j, i);
//        }
//      }
        
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 600, 600);
    primaryStage.setTitle("Exercise14_07"); // Set the stage title
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
