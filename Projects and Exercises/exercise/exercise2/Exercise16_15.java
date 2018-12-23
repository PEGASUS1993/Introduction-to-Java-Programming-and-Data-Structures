import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise16_15 extends Application {
  private double paneWidth = 480;
  private double paneHeight = 250;
  private ImageView imageView = new ImageView("image/grapes.gif");
  private Label label = new Label("Grapes", imageView);
  private ComboBox<String> cboContentDisplay = new ComboBox<>();
  private TextField tfGraphicTextGap = new TextField();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
    pane.setCenter(label);
      
    label.setContentDisplay(ContentDisplay.TOP);

    cboContentDisplay.getItems().addAll("TOP", "BOTTOM", "LEFT", "RIGHT");
    cboContentDisplay.setValue("TOP");
    
    tfGraphicTextGap.setPrefColumnCount(3);
    
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(new Label("contentDisplay:"),
      cboContentDisplay, new Label("graphicTextGap: "), 
      tfGraphicTextGap);
    hBox.setAlignment(Pos.CENTER);
    pane.setTop(hBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_15"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    cboContentDisplay.setOnAction(e -> {
      if (cboContentDisplay.getValue().equals("TOP")) {
        label.setContentDisplay(ContentDisplay.TOP);
      } 
      else if (cboContentDisplay.getValue().equals("BOTTOM")) {
        label.setContentDisplay(ContentDisplay.BOTTOM);
      }
      else if (cboContentDisplay.getValue().equals("LEFT")) {
        label.setContentDisplay(ContentDisplay.LEFT);
      }
      else if (cboContentDisplay.getValue().equals("RIGHT")) {
        label.setContentDisplay(ContentDisplay.RIGHT);
      }
    });
   
    tfGraphicTextGap.setOnAction(e -> {
      label.setGraphicTextGap(Double.parseDouble(
        tfGraphicTextGap.getText()));
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
