import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Exercise14_02 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Image imageX = new Image("image/x.gif");
    Image imageO = new Image("image/o.gif");
    
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setHgap(5);
    pane.setVgap(5);
    
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int status = (int)(Math.random() * 3);
        if (status == 0) {
          pane.add(new ImageView(imageX), j, i);     
        }
        else if (status == 1) {
          pane.add(new ImageView(imageO), j, i);     
        }        
      }
    }
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Exercise14_02"); // Set the stage title
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
