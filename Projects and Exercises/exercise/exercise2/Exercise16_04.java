import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise16_04 extends Application {
  private double paneWidth = 250;
  private double paneHeight = 60;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    TextField tfMile = new TextField();
    TextField tfKilometer = new TextField();
    tfMile.setAlignment(Pos.BOTTOM_RIGHT);
    tfKilometer.setAlignment(Pos.BOTTOM_RIGHT);

    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.add(new Label("Mile"), 0, 0);
    pane.add(tfMile, 1, 0);
    pane.add(new Label("Kilometer"), 0, 1);
    pane.add(tfKilometer, 1, 1);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_04"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    tfMile.setOnAction(e -> {
      double mile = new Double(tfMile.getText().trim()).doubleValue();
      double kilometer = mile / 0.6241;
      tfKilometer.setText(new Double(kilometer).toString());
    });

    tfKilometer.setOnAction(e -> {
      double kilometer = new Double(tfKilometer.getText().trim()).doubleValue();
      double mile = 0.6241 * kilometer;
      tfMile.setText(new Double(mile).toString());
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
