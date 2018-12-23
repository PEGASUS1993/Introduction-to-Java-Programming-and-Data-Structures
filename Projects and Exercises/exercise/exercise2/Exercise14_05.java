import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise14_05 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {    
    Pane pane = new Pane();
    
    Font font = Font.font("Times New Roman", FontWeight.BOLD, 
        FontPosture.REGULAR, 35);
    
    String s = "WELCOME TO JAVA ";
    double radius = 80;
    for (int i = 0; i < s.length(); i++) { 
      double alpha = 2 * Math.PI * (s.length() - i) / s.length();
      Text txt = new Text(radius * Math.cos(alpha) + 120, 
        120 - radius * Math.sin(alpha), s.charAt(i) + "");

      txt.setFont(font);
      txt.setRotate(360 * i / s.length() + 90);
      pane.getChildren().add(txt);
    }
        
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 240, 240);
    primaryStage.setTitle("Exercise14_05"); // Set the stage title
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
