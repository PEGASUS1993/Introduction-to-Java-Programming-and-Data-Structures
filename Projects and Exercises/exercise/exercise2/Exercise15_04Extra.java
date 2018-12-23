import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Exercise15_04Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {         
    double paneWidth = 270;
    double paneHeight = 210;  
    
    BorderPane borderPane = new BorderPane();
    RegularPolygonPane regularPolygonPane = new RegularPolygonPane(6);
    borderPane.setCenter(regularPolygonPane);
    
    HBox hBox = new HBox(5);
    Button btIncrease = new Button("+");
    Button btDecrease = new Button("-");
    hBox.getChildren().addAll(btIncrease, btDecrease);
    hBox.setAlignment(Pos.CENTER);
    
    borderPane.setBottom(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise15_04"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btIncrease.setOnAction(e -> regularPolygonPane.increase());
    btDecrease.setOnAction(e -> regularPolygonPane.decrease());
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

  class RegularPolygonPane extends StackPane {
    private Polygon polygon = new Polygon();
    private int numberOfSides = 6;
    
    public RegularPolygonPane(int numberOfSides) { 
      this.numberOfSides = numberOfSides;      
      this.getChildren().add(polygon); 
      polygon.setFill(Color.WHITE);
      polygon.setStroke(Color.BLACK);
      paint();
    }
    
    private void paint() { 
      ObservableList<Double> list = polygon.getPoints();
      list.clear();
           
      final double WIDTH = 200, HEIGHT = 200;
      double centerX = WIDTH / 2, centerY = HEIGHT / 2;
      double radius = Math.min(WIDTH, HEIGHT) * 0.4;
  
      // Add points to the polygon list
      for (int i = 0; i < numberOfSides; i++) {
        list.add(centerX + radius * Math.cos(2 * i * Math.PI / numberOfSides)); 
        list.add(centerY - radius * Math.sin(2 * i * Math.PI / numberOfSides));
      }     
    }
    
    public void increase() {
      numberOfSides++;
      paint();
    }
    
    public void decrease() {
      numberOfSides--;
      paint();
    }
  }
}