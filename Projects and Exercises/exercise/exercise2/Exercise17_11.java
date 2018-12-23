import java.io.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise17_11 extends Application {
  private TextField tfInputFile = new TextField();
  private TextField tfNumberOfFiles = new TextField();
  private Button btBrowse = new Button("Browse");
  private Button btStart = new Button("Start");

  @Override
  public void start(Stage primaryStage) {
    GridPane gridPane = new GridPane();
    gridPane.add(new Label("Enter a file: "), 0, 0);
    gridPane.add(tfInputFile, 1, 0);
    gridPane.add(new Label("Specify the number of smaller files: "), 0, 1);
    gridPane.add( tfNumberOfFiles, 1, 1);

    HBox hBox = new HBox(5);
    hBox.getChildren().add(btStart);
    hBox.setAlignment(Pos.CENTER);
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(new Label(
      "If you split a file named temp.txt into 3 smaller files, \nthe three smaller files are temp.txt.1, temp.txt.2, and temp.txt.3."),
      gridPane, hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 400, 120);
    primaryStage.setTitle("Exercise17_11"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btStart.setOnAction(e -> {
      splitFile(tfInputFile.getText(), Integer.parseInt(tfNumberOfFiles.getText()));
    });
  }

  public void splitFile(String filename, int numberOfPieces) {
    try (
      BufferedInputStream input = new BufferedInputStream(
        new FileInputStream(new File(filename)));
    ) {
      System.out.println("File size: " + input.available() + " bytes");
      long fileSize = input.available();
      int splitFileSize = (int) Math.ceil(1.0 * fileSize / numberOfPieces);

      for (int i = 1; i <= numberOfPieces; i++) {
        try (
          BufferedOutputStream output = new BufferedOutputStream(
              new FileOutputStream(new File(filename + "." + i)));
        ) {
          int value;
          int count = 0;
          // What is wrong if these two conditions are placed in a different
          // order?
          while (count++ < splitFileSize && (value = input.read()) != -1) {
            output.write(value);
          }
        }
      }
    } catch (IOException ex) {
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
