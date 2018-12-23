import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

public class Exercise16_08Extra extends Application {
  private double paneWidth = 250;
  private double paneHeight = 250;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Button btShowHistogram = new Button("Show Histogram");
    BorderPane pane = new BorderPane();
    TextArea ta = new TextArea();
    pane.setCenter(new ScrollPane(ta));
    pane.setBottom(btShowHistogram);
    BorderPane.setAlignment(btShowHistogram, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_10"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    int[] counts = new int[26];
    btShowHistogram.setOnAction(e -> {
      String s = ta.getText().toUpperCase();
      
      for (int i = 0; i < s.length(); i++) {
        if (Character.isLetter(s.charAt(i))) {
          counts[s.charAt(i) - 'A']++;
        }
      }
      
      Histogram histogram = new Histogram();
      histogram.setCounts(counts);
      Scene sceneForHistogram = new Scene(histogram, 300, 200);
      Stage stage = new Stage();
      stage.setScene(sceneForHistogram);
      stage.show();
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
