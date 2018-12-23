import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class Exercise14_01 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setHgap(5);
    pane.setVgap(5);
    ImageView imageView1 = new ImageView("image/germany.gif");
    ImageView imageView2 = new ImageView("image/china.gif");
    ImageView imageView3 = new ImageView("image/fr.gif");
    ImageView imageView4 = new ImageView("image/us.gif");
    pane.add(imageView1, 0, 0);
    pane.add(imageView2, 1, 0);
    pane.add(imageView3, 0, 1);
    pane.add(imageView4, 1, 1);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Exercise14_01"); // Set the stage title
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
