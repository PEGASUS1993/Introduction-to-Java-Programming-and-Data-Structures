import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise36_11Reader extends Application {
  private TextArea ta = new TextArea();
  private TextField tfFilename = new TextField();
  private TextField tfEncoding = new TextField();
  private Button btViewFile = new Button("View File");

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Enter a file: "), 
      tfFilename, new Label("Enter the encoding scheme: "), 
      tfEncoding, btViewFile);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(ta);
    pane.setTop(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 450, 200);
    primaryStage.setTitle("Exercise36_11Reader"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    tfFilename.setPrefColumnCount(10);
    tfEncoding.setPrefColumnCount(5);
    btViewFile.setOnAction(e -> process());

  }

  private void process() {
    String filename = tfFilename.getText().trim();
    String encoding = tfEncoding.getText().trim();

    try (BufferedReader in = new BufferedReader(
           new InputStreamReader(new FileInputStream(filename), encoding));) {
      String line;
      while ((line = in.readLine()) != null) {
        ta.appendText(line + "\n");
      }
    }
    catch (IOException ex) {
      ex.printStackTrace();
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