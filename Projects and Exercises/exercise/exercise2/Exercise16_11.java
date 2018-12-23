import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise16_11 extends Application {
  private double paneWidth = 400;
  private double paneHeight = 250;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane p = new BorderPane();
    p.setLeft(new Label("Filename"));
    TextField tfFilename = new TextField();
    p.setCenter(tfFilename);
    Button btView = new Button("View");
    p.setRight(btView);

    BorderPane pane = new BorderPane();
    Histogram histogram = new Histogram();
    pane.setCenter(histogram);
    pane.setBottom(p);
    p.setStyle("-fx-border-color: black");

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_11"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    int[] counts = new int[26];
    
    btView.setOnAction(e -> {
      // Get file name from the text field
      String filename = tfFilename.getText().trim();

      try {
        // Create a buffered stream
        Scanner input = new Scanner(new File(filename));

        // Read a line and append the line to the text area
        while (input.hasNext()) {
          String s = input.nextLine().toUpperCase();
          for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
              counts[s.charAt(i) - 'A']++;
            }
          }
        }
        
        histogram.setCounts(counts);
      } 
      catch (FileNotFoundException ex) {
        System.out.println("File not found: " + filename);
      } 
      catch (IOException ex) {
        System.out.println(ex.getMessage());
      }
    });
  }
  
  class Histogram extends Pane {
    double w = 400;
    double h = 200;
    
    Integer[] counts = new Integer[26];
    
    public Histogram() {
    }
    
    public void setCounts(int[] counts) {
      for (int i = 0; i < counts.length; i++) {
        this.counts[i] = counts[i];
      }
      
      repaint();
    }
    
    private void repaint() {
      getChildren().clear();
      
      Line line = new Line(10, h + 10 - 20, w - 10, h + 10 - 20);      
      getChildren().addAll(line);
 
      int maxCounts = java.util.Collections.max(new ArrayList<>(Arrays.asList(counts)));            
      for (int i = 0; i < 26; i++) {
        if (maxCounts > 0) {
          Rectangle r = new Rectangle(10 + i * (w - 20) / 26, h + 10 - 20 - counts[i] * (h - 20) / maxCounts, 
            (w - 20) / 26 - 3, counts[i] * (h - 20) / maxCounts);
          r.setFill(Color.WHITE);
          r.setStroke(Color.BLACK);
          getChildren().add(r);
        }
        getChildren().add(new Text(10 + i * (w - 20) / 26, h - 5 + 10, (char)(i + 'A') + ""));
      }
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
