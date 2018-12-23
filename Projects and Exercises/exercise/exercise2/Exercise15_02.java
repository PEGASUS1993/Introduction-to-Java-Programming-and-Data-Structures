import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise15_02 extends Application {
  private double angle = 0;
  
  @Override
  // Override the start method in the Application class
  public void start(Stage primaryStage) {
    StackPane stackPane = new StackPane();
    Rectangle rectangle = new Rectangle(30, 30, 30, 60);
    rectangle.setFill(Color.WHITE);
    rectangle.setStroke(Color.BLACK);
    stackPane.getChildren().add(rectangle);
    
    // Create a button
    Button btRotate = new Button("Rotate");
    btRotate.setOnAction(e -> {
      angle += 15;
      rectangle.setRotate(angle);
    });
   
    BorderPane pane = new BorderPane();
    pane.setCenter(stackPane);
    pane.setBottom(btRotate);
    BorderPane.setAlignment(btRotate, Pos.TOP_CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 150);
    primaryStage.setTitle("Exercise15_02"); // Set the stage title
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
