import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise16_12 extends Application {
  private double paneWidth = 400;
  private double paneHeight = 250;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
    TextArea ta = new TextArea();
    // ta.setPrefColumnCount(30);
    pane.setCenter(new ScrollPane(ta));
    
    HBox hBox = new HBox(10);
    CheckBox chkEditable = new CheckBox("Editable");
    CheckBox chkWrap = new CheckBox("Wrap");
    hBox.getChildren().addAll(chkEditable, chkWrap);
    hBox.setAlignment(Pos.CENTER);
    pane.setBottom(hBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_12"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    chkEditable.setOnAction(e -> {
      ta.setEditable(chkEditable.isSelected());
    });
    
    chkWrap.setOnAction(e -> {
      ta.setWrapText(chkWrap.isSelected());
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
