import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise15_02Extra extends Application {
  private static Pane pane = new Pane();
  private static double paneWidth = 400; 
  private static double paneHeight = 250;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    drawARandomArrowLine();
    
    Button btDrawArrowLine = new Button("Draw a random arrow line");
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(btDrawArrowLine);
    BorderPane.setAlignment(btDrawArrowLine, Pos.CENTER);
    
    btDrawArrowLine.setOnAction(e -> drawARandomArrowLine());
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 400, 250);
    primaryStage.setTitle("Exercise15_02"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  private static void drawARandomArrowLine() { 
    pane.getChildren().clear();
    
    double x1 = Math.random() * (paneWidth - 12);
    double y1 = Math.random() * (paneHeight - 12);
    double x2 = Math.random() * (paneWidth - 12);
    double y2 = Math.random() * (paneHeight - 12);
        
    drawArrowLine(x1, y1, x2, y2, pane);
  }
  
  public static void drawArrowLine(double x1, double y1, 
      double x2, double y2, Pane pane) {
    pane.getChildren().add(new Line(x1, y1, x2, y2));
    
    // find slope of this line
    double slope = ((((double) y1) - (double) y2))
      / (((double) x1) - (((double) x2)));

    double arctan = Math.atan(slope);

    // This will flip the arrow 45 off of a
    // perpendicular line at pt x2
    double set45 = 1.57 / 2;
    
    // arrows should always point towards i, not i+1
    if (x1 < x2) {
      // add 90 degrees to arrow lines
      set45 = -1.57 * 1.5;
    }

    // set length of arrows
    int arrlen = 15;

    // draw arrows on line
    pane.getChildren().add(new Line(x2, y2, (x2 + (Math.cos(arctan + set45) * arrlen)),
      ((y2)) + (Math.sin(arctan + set45) * arrlen)));

    pane.getChildren().add(new Line(x2, y2, (x2 + (Math.cos(arctan - set45) * arrlen)),
      ((y2)) + (Math.sin(arctan - set45) * arrlen)));
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
