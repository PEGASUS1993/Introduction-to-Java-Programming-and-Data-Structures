import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise36_01 extends Application {
  private CalendarPane calendarPane = new CalendarPane();
  private TextField tfUnicode = new TextField();
  private TextArea taUnicodeCharacters = new TextArea();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(
      new Label("Specify a Unicode"), tfUnicode);
    hBox.setAlignment(Pos.CENTER);
    tfUnicode.setPrefColumnCount(4);

    BorderPane pane = new BorderPane();
    pane.setCenter(taUnicodeCharacters);
    pane.setTop(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 300);
    primaryStage.setTitle("Exercise36_01"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    tfUnicode.setOnAction(e -> {
      int code = Integer.parseInt(tfUnicode.getText().toString(), 16);
      System.out.println(code);
      displayUnicode(code);
    });
  }

  private void displayUnicode(int code) {
    taUnicodeCharacters.setText(null);
    for (int i = 0; i < 20; i++) {
      taUnicodeCharacters.appendText(
        Integer.toHexString(code) + "  ");
      for (int j = 0; j < 16; j++) {
        taUnicodeCharacters.appendText((char)code + " ");
        code++;
      }
      taUnicodeCharacters.appendText("\n");
    }
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}