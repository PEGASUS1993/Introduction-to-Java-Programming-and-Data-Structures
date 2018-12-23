import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_04 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {    
    HBox pane = new HBox();
    pane.setAlignment(Pos.CENTER);
    
    Font font = Font.font("Times New Roman", FontWeight.BOLD, 
        FontPosture.ITALIC, 22);

    for (int i = 0; i < 5; i++) {
      Text txt = new Text("Java");
      txt.setRotate(90);
      txt.setFont(font);
      txt.setFill(new Color(Math.random(), Math.random(), Math.random(), Math.random()));
      pane.getChildren().add(txt);
    }
        
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 100);
    primaryStage.setTitle("Exercise14_04"); // Set the stage title
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
