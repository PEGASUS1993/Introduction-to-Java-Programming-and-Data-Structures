import java.util.Locale;
import java.util.TimeZone;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise36_04 extends Application {
  private Locale[] availableLocales = Locale.getAvailableLocales();
  private String[] availableTimeZones = TimeZone.getAvailableIDs();
  private TextArea taDisplay = new TextArea();
  private Button btLocale = new Button("All Locales");
  private Button btTimeZone = new Button("All Time Zones");
 
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btLocale, btTimeZone);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(taDisplay);
    pane.setBottom(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 200);
    primaryStage.setTitle("Exercise36_04"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btLocale.setOnAction(e -> {
      Locale[] availableLocales = Locale.getAvailableLocales();
      taDisplay.setText(null);
      for (int i=0; i<availableLocales.length; i++) {
        taDisplay.appendText(availableLocales[i].getDisplayName() + " "
          + availableLocales[i].toString() + '\n');
      }
    });
    
    btTimeZone.setOnAction(e -> {
      String[] availableTimeZones = TimeZone.getAvailableIDs();
      taDisplay.setText(null);
      for (int i=0; i<availableTimeZones.length; i++) {
        taDisplay.appendText(availableTimeZones[i] + '\n');
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