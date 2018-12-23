import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Exercise16_14 extends Application {
  private double paneWidth = 480;
  private double paneHeight = 250;
  private Label label = new Label("Programming is fun");
  private ComboBox<String> cboFontName = new ComboBox<>();
  private ComboBox<Integer> cboFontSize = new ComboBox<>();
  private CheckBox chkBold = new CheckBox("Bold");
  private CheckBox chkItalic = new CheckBox("Italic");
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
    pane.setCenter(label);
      
    // Populate font name into combo box
    List<String> fontNames = Font.getFontNames(); // .getFamilies();
    cboFontName.getItems().addAll(fontNames);
    cboFontName.setValue(fontNames.get(0));

    // Populate font size into combo box
    for (int i = 1; i <= 100; i++) {
      cboFontSize.getItems().add(i);
    }
    cboFontSize.setValue(1);
    
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(new Label("Font Name"),
      cboFontName, new Label("Font Size"), cboFontSize);
    hBox.setAlignment(Pos.CENTER);
    pane.setTop(hBox);

    HBox hBox2 = new HBox(10);
    hBox2.getChildren().addAll(chkBold, chkItalic);
    hBox2.setAlignment(Pos.CENTER);
    pane.setBottom(hBox2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_14"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    cboFontName.setOnAction(e -> {
      setFont();
    });
   
    cboFontSize.setOnAction(e -> {
      setFont();
    });
    
    chkBold.setOnAction(e -> {
      setFont();
    });
        
    chkItalic.setOnAction(e -> {
      setFont();
    });        
  }
  
  private void setFont() {
    FontWeight weight;
    if (chkBold.isSelected()) {
      weight = FontWeight.BOLD;
    }
    else {
      weight = FontWeight.NORMAL;
    }
    
    FontPosture posture;
    if (chkItalic.isSelected()) {
      posture = FontPosture.ITALIC;
    }
    else {
      posture = FontPosture.REGULAR;
    }
        
    label.setFont(Font.font(cboFontName.getValue(), weight, posture,
            cboFontSize.getValue()));    
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
