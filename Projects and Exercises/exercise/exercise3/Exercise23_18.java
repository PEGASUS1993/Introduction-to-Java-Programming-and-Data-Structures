import java.util.Arrays;
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

public class Exercise23_18 extends Application {  
  public final static int ARRAY_SIZE = 8;
  private int[] list1 = new int[ARRAY_SIZE];
  private int[] list2 = new int[ARRAY_SIZE];
  private int[] temp = new int[2 * ARRAY_SIZE];
  private int current1 = 0; // Current index in list1
  private int current2 = 0; // Current index in list2
  private int current3 = 0; // Current index in temp
  
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
    primaryStage.setTitle("Exercise23_18: Merge two sorted lists"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    initializeList();

    pane.repaint();
    
    btStep.setOnAction(e -> {
      if (step()) {
        pane.repaint();
        lblStatus.setText("The lists are already merged");  
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
    for (int i = 0; i < list1.length; i++) {
      list1[i] = (int)(Math.random() * 999 + 1);
    }
    
    for (int i = 0; i < list2.length; i++) {
      list2[i] = (int)(Math.random() * 999 + 1);
    }
    
    for (int i = 0; i < temp.length; i++) {
      temp[i] = 0;
    }
    
    Arrays.sort(list1);
    Arrays.sort(list2);
  }
  
  /** Start a new sort */
  public void reset() {
    current1 = 0; // Current index in list1
    current2 = 0; // Current index in list2
    current3 = 0; // Current index in temp
    initializeList();
  }

  public boolean step() {    
    if (current1 < list1.length && current2 < list2.length) {
      if (list1[current1] < list2[current2])
        temp[current3++] = list1[current1++];
      else
        temp[current3++] = list2[current2++];
    }
    else if (current1 < list1.length)
      temp[current3++] = list1[current1++];
    else if (current2 < list2.length)
      temp[current3++] = list2[current2++];
    else 
      return true;
    
    return false;
  }

  class AnimationPane extends Pane {
    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 30;
    private int boxHeight = 20;
    
    protected void repaint() {
      this.getChildren().clear();
      
      int x = startingX + 10;
      int y = startingY + 40;         
        
      // Display current1 and list1
      getChildren().add(new Text(x - 5, y + 15, "list1"));
      x += 20;
      
      getChildren().add(new Text(x + current1 * boxWidth, startingY, "current1"));
      drawArrowLine(x + 15 + current1 * boxWidth, startingY, x + 15 + current1 * boxWidth, y);
      for (int k = 0; k < list1.length; k++) {
        Rectangle rectangle = new Rectangle(x, y, boxWidth, boxHeight);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        this.getChildren().add(rectangle);
        getChildren().add(new Text(x + 5, y + 15, list1[k] + ""));
        x = x + boxWidth;
      }
      
      // Display current2 and list2
      x += 30;
      getChildren().add(new Text(x - 5, y + 15, "list2"));

      x +=20;
      getChildren().add(new Text(x + current2 * boxWidth, startingY, "current2"));
      drawArrowLine(x + 15 + current2 * boxWidth, startingY, x + 15 + current2 * boxWidth, y);
      for (int k = 0; k < list2.length; k++) {
        Rectangle rectangle = new Rectangle(x, y, boxWidth, boxHeight);
        getChildren().add(rectangle);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        
        getChildren().add(new Text(x + 5, y + 15, list2[k] + ""));
        x = x + boxWidth;
      }   
      
      // Display temp
      x = startingX + 10;
      getChildren().add(new Text(x - 9, y + 55, "temp"));
      x += 20;
      getChildren().add(new Text(x + current3 * boxWidth, y + 120, "current3"));
      drawArrowLine(x + 15 + current3 * boxWidth, y + 100, x + 15 + current3 * boxWidth, y + 40 + boxHeight);
      for (int k = 0; k < temp.length; k++) {
        Rectangle rectangle = new Rectangle(x, y + 40, boxWidth, boxHeight);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        getChildren().add(rectangle);
        if (temp[k] != 0) {
          getChildren().add(new Text(x + 5, y + 55, temp[k] + ""));
        }
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
