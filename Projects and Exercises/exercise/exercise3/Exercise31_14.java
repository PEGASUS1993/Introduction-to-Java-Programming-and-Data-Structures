import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise31_14 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    StackPane pane = new StackPane();

    // Create a Polygon object
    Polygon polygon = new Polygon();
    polygon.setFill(Color.RED);

    double radius = 100;
    // Add points to the polygon
    for (int i = 0; i < 8; i++) {
      polygon.getPoints().addAll(radius * Math.cos(i * 2 * Math.PI / 8
        + 2 * Math.PI / 16),
        -radius * Math.sin(i * 2 * Math.PI / 8 + 2 * Math.PI / 16));
    }

    Text word = new Text("STOP");
    word.setFill(Color.WHITE);
    word.setFont(Font.font("Times New Roman", FontWeight.BOLD,
            FontPosture.REGULAR, 38));

    pane.getChildren().addAll(polygon, word);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 300, 300);
    primaryStage.setTitle("Exercise31_14"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage    
    
    pane.requestFocus();
    pane.setOnKeyPressed(e -> {     
              System.out.println(e.getCode());
      switch (e.getCode()) {
        case DOWN: pane.setScaleX(pane.getScaleX() * 0.95);
          pane.setScaleY(pane.getScaleY() * 0.95);
        break;
        case UP: pane.setScaleX(pane.getScaleX() * 1.05);
          pane.setScaleY(pane.getScaleY() * 1.05);
          break;
        case LEFT: pane.setRotate(pane.getRotate() - 10); break;
        case RIGHT: pane.setRotate(pane.getRotate() + 10); break;
      }
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
