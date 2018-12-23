import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Exercise15_23 extends Application {
   private double paneWidth = 200, paneHeight = 200;
   private Polygon polygon = new Polygon();
   private ObservableList<Double> list = polygon.getPoints();
    
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    // Create a pane, a polygon, and place polygon to pane
    StackPane pane = new StackPane();
    
    setValues();
    
    polygon.setFill(Color.RED);

    Text text = new Text("STOP");
    text.setFill(Color.WHITE);
    text.setFont(Font.font("Times New Roman", 40));
    
    pane.getChildren().addAll(polygon, text); 
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise15_23"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    scene.widthProperty().addListener(ov -> {
      paneWidth = pane.getWidth();
      setValues(); 
    });
    
    scene.heightProperty().addListener((ov, oldVal, newVal) -> {
      paneHeight = pane.getHeight();
      setValues(); 
    });
  }
  
  private void setValues() {
    double centerX = paneWidth / 2, centerY = paneHeight / 2;
    double radius = Math.min(paneWidth, paneHeight) * 0.4;
        
    // Add points to the polygon list
    list.clear();
    for (int i = 0; i < 8; i++) {
      list.add(centerX + radius * Math.cos(2 * i * Math.PI / 8 - Math.PI / 8)); 
      list.add(centerY - radius * Math.sin(2 * i * Math.PI / 8 - Math.PI / 8));
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
