import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Exercise16_12Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    HBox hBox = new HBox();
    TextField tf = new TextField();   
    hBox.getChildren().addAll(new Label("Enter a string: "), tf);
    
    ListView<String> lv = new ListView<>();
    BorderPane pane = new BorderPane();
    pane.setCenter(lv);
    pane.setTop(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 350, 250);
    primaryStage.setTitle("Exercise16_12"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    tf.setOnAction(e -> {
      lv.getItems().add(tf.getText());
    });
    
    lv.requestFocus();
    lv.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.DELETE) {
        lv.getItems().remove(
          lv.getSelectionModel().getSelectedItem());
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
