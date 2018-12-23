import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Exercise31_02 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Image imageX = new Image("image/x.gif");
    Image imageO = new Image("image/o.gif");
    
    GridPane pane = new GridPane();
    pane.getStylesheets().add("mystyle.css"); // Load the stylesheet          
    pane.getStyleClass().add("border");

    pane.setAlignment(Pos.CENTER);
    pane.setHgap(5);
    pane.setVgap(5);
    
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if ((int)(Math.random() * 3) == 0) {
          ImageView imageView1 = new ImageView(imageX);
          imageView1.getStyleClass().add("border");
          pane.add(imageView1, j, i);     
        }
        else if ((int)(Math.random() * 3) == 1) {
          ImageView imageView2 = new ImageView(imageO);
          imageView2.getStyleClass().add("border");
          pane.add(imageView2, j, i);     
        }        
      }
    }
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("Exercise31_02"); // Set the stage title
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
