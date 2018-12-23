import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise16_07 extends Application {
  private double paneWidth = 350;
  private double paneHeight = 250;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    TextField tfHour = new TextField();
    TextField tfMinute = new TextField();
    TextField tfSecond = new TextField();
    tfHour.setAlignment(Pos.BOTTOM_RIGHT);
    tfMinute.setAlignment(Pos.BOTTOM_RIGHT);
    tfSecond.setAlignment(Pos.BOTTOM_RIGHT);
    tfMinute.setAlignment(Pos.BOTTOM_RIGHT);
    tfHour.setPrefColumnCount(3);
    tfMinute.setPrefColumnCount(3);
    tfSecond.setPrefColumnCount(3);

    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(new Label("Hour"),
      tfHour, new Label("Minute"), tfMinute,
      new Label("Second"), tfSecond);
    
    BorderPane pane = new BorderPane();
    ClockPane clock = new ClockPane();
    pane.setCenter(clock);
    pane.setBottom(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_07"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    tfHour.setOnAction(e -> {
      clock.setHour(Integer.parseInt(tfHour.getText()));
    });

    tfMinute.setOnAction(e -> {
      clock.setMinute(Integer.parseInt(tfMinute.getText()));    
    });
    
    tfSecond.setOnAction(e -> {
      clock.setSecond(Integer.parseInt(tfSecond.getText()));     
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
