import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise16_17 extends Application {
  private double paneWidth = 480;
  private double paneHeight = 250;
  private Text text = new Text("Show Colors");
  private ScrollBar scbRed = new ScrollBar();
  private ScrollBar scbGreen = new ScrollBar();
  private ScrollBar scbBlue = new ScrollBar();
  private ScrollBar scbOpacity = new ScrollBar();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
      
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Red"), 0, 0);
    gridPane.add(new Label("Green"), 0, 1);
    gridPane.add(new Label("Blue"), 0, 2);
    gridPane.add(new Label("Opacity"), 0, 3);
    gridPane.add(scbRed, 1, 0);
    gridPane.add(scbGreen, 1, 1);
    gridPane.add(scbBlue, 1, 2);
    gridPane.add(scbOpacity, 1, 3);
            
    pane.setBottom(gridPane);
    pane.setCenter(text);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(5);
    gridPane.setHgap(5);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_17"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    scbRed.valueProperty().addListener(ov -> {
      updateColor();
    });
    scbGreen.valueProperty().addListener(ov -> {
      updateColor();
    });
    scbBlue.valueProperty().addListener(ov -> {
      updateColor();
    });
    scbOpacity.valueProperty().addListener(ov -> {
      updateColor();
    });
  }

  private void updateColor() {
    double red = scbRed.getValue() / scbRed.getMax();
    double green = scbGreen.getValue() / scbGreen.getMax();
    double blue = scbBlue.getValue() / scbBlue.getMax();
    double opacity = scbOpacity.getValue() / scbOpacity.getMax();
    text.setFill(Color.color(red, green, blue, opacity));
  }
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
