import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise18_38 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    TreePane pane = new TreePane(); 
    TextField tfOrder = new TextField(); 
    tfOrder.setOnAction(
      e -> pane.setDepth(Integer.parseInt(tfOrder.getText())));
    tfOrder.setPrefColumnCount(4);
    tfOrder.setAlignment(Pos.BOTTOM_RIGHT);

    // Pane to hold label, text field, and a button
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);
        
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 200, 210);
    primaryStage.setTitle("Exercise18_38"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    scene.widthProperty().addListener(ov -> pane.paint());
    scene.heightProperty().addListener(ov -> pane.paint());
  }

  /** Pane for displaying triangles */
  static class TreePane extends Pane {
    private int depth = 0;
    private double angleFactor = Math.PI / 5;
    private double sizeFactor = 0.58;

    public void setDepth(int depth) {
      this.depth = depth;
      paint();
    }

    public void paint() {
      getChildren().clear();
      
      paintBranch(depth, getWidth() / 2, getHeight() - 10,
        getHeight() / 3, Math.PI / 2);
    }

    public void paintBranch(int depth, double x1, double y1,
        double length, double angle) {
        if (depth >= 0) {
          double x2 = x1 + Math.cos(angle) * length;
          double y2 = y1 - Math.sin(angle) * length;

          // Draw the line
          getChildren().add(new Line(x1, y1, x2, y2));

          // Draw the left branch
          paintBranch(depth - 1, x2, y2, length * sizeFactor, angle
                          + angleFactor);
          // Draw the right branch
          paintBranch(depth - 1, x2, y2, length * sizeFactor, angle
                          - angleFactor);
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
