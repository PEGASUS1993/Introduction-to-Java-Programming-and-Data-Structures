import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise23_19 extends Application {  
  public final static int ARRAY_SIZE = 20;
  private int[] list = new int[ARRAY_SIZE];
  private int pivot; // Choose the first element as the pivot
  private int pivotIndex = 0; // Choose the first element as the pivot
  private int low = 1; // Index for forward search
  private int high = list.length - 1; // Index for backward search
  private boolean done = false;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    AnimationPane pane = new AnimationPane();
    pane.setStyle("-fx-border-color: black");
    
    Button btStep = new Button("Step");
    Button btReset = new Button("Reset");
    
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btStep, btReset);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);
    
    Label lblStatus = new Label();
    borderPane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 400, 250);
    primaryStage.setTitle("Exercise23_19: Partition of List for Quick Sort"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    initializeList();

    pane.repaint();
    
    btStep.setOnAction(e -> {
      if (step()) {
        pane.repaint();
        lblStatus.setText("The list is already partitioned");  
      }
      else {
        pane.repaint();
      }
    });

    btReset.setOnAction(e -> {
      reset();
      lblStatus.setText("");
      pane.repaint();
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /** Initialize the list with random numbers */
  public void initializeList() {
    for (int i = 0; i < list.length; i++) {
      list[i] = (int)(Math.random() * 999 + 1);
    }
    pivot = list[0]; 
  }
  
  /** Start a new sort */
  public void reset() {
    initializeList();
    pivot = list[0];
    pivotIndex = 0;
    low = 1;
    high = list.length - 1;
    done = false;
  }

  public boolean step() {    
    if (done) return true;
    
    if (high > low) {
      // Search forward from left
      if (low <= high && list[low] <= pivot) {
        low++;
        return false;
      }

      // Search backward from right
      if (low <= high && list[high] > pivot) {
        high--;
        return false;
      }

      // Swap two elements in the list
      if (high > low) {
        int temp = list[high];
        list[high] = list[low];
        list[low] = temp;
      }
    }

    if (high > 0 && list[high] >= pivot) {
      high--;
      return false;
    }

    // Swap pivot with list[high]
    if (pivot > list[high]) {
      list[0] = list[high];
      list[high] = pivot;
      pivotIndex = high;
      done = true;
      return true;
    }
    else {
      pivotIndex = 0;
      done = true;
      return true;
    }
  }

  class AnimationPane extends Pane {
    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 30;
    private int boxHeight = 20;
    
    protected void repaint() {
      getChildren().clear();
      
      int x = startingX + 10;
      int y = startingY + 40;         
      
      getChildren().add(new Text(x + low * boxWidth + 4, startingY + 25, "low"));
      getChildren().add(new Text(x + high * boxWidth, startingY, "high"));
      getChildren().add(new Text(x + pivotIndex * boxWidth, startingY + 25 + 145, "pivot"));
      drawArrowLine(x + 15 + low * boxWidth, startingY + 25 + 4, x + 15 + low * boxWidth, y + 25);
      drawArrowLine(x + 15 + high * boxWidth, startingY + 4, x + 15 + high * boxWidth, y + 25);
      drawArrowLine(x + pivotIndex * boxWidth + 15, startingY + 4 + 145, 
              x + pivotIndex * boxWidth + 15, y + 25 + boxHeight);
      
      for (int k = 0; k < list.length; k++) {
        Rectangle rectangle = new Rectangle(x, y + 25, boxWidth, boxHeight);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        getChildren().add(rectangle);
        getChildren().add(new Text(x + 5, y + 25 + 15, list[k] + ""));
        x = x + boxWidth;
      }
    }
  
    public void drawArrowLine(double x1, double y1, 
        double x2, double y2) {
      getChildren().add(new Line(x1, y1, x2, y2));

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
     getChildren().add(new Line(x2, y2, (x2 + (Math.cos(arctan + set45) * arrlen)),
        ((y2)) + (Math.sin(arctan + set45) * arrlen)));

      getChildren().add(new Line(x2, y2, (x2 + (Math.cos(arctan - set45) * arrlen)),
        ((y2)) + (Math.sin(arctan - set45) * arrlen)));
    }
  }
}
