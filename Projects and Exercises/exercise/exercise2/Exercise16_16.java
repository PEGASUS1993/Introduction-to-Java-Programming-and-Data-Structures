import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise16_16 extends Application {
  private double paneWidth = 480;
  private double paneHeight = 250;
  private Label label = new Label("No items selected");
  private ComboBox<String> cboSelectionMode = new ComboBox<>();
  private ListView<String> lv = new ListView();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
      
    lv.getItems().addAll("China", "Japan", "Korea", "India",
       "Malaysia", "Vietnam");
            
    cboSelectionMode.getItems().addAll("SINGLE", "MULTIPLE");
    cboSelectionMode.setValue("SINGLE");
    
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(new Label("Choose Selection Mode:"),
      cboSelectionMode);
    hBox.setAlignment(Pos.CENTER);
    pane.setTop(hBox);
    pane.setCenter(new ScrollPane(lv));
    pane.setBottom(label);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_16"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    cboSelectionMode.setOnAction(e -> {
      if (cboSelectionMode.getValue().equals("SINGLE")) {
        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      } 
      else {
        lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      }
    });
    
    lv.getSelectionModel().selectedItemProperty().addListener(ov -> {
      String items = "";
      for (String s: lv.getSelectionModel().getSelectedItems()) {
        items += s + " ";
      }
      label.setText("Selected items are " + items);
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
