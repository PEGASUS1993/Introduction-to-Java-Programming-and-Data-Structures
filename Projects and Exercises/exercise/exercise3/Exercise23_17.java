import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise23_17 extends Application {  
  double radius = 2;
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
    primaryStage.setTitle("Exercise23_17: Radix Sort"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    initializeList();
    initializeBuckets();
    pane.repaint();
    
    btStep.setOnAction(e -> {
      if (step()) {
        pane.repaint();
        lblStatus.setText("The array is already sorted");  
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
      list[i] = (int)(Math.random() * 1000);
    }
  }

  /** Create array list for each bucket */
  public void initializeBuckets() {
    for (int i = 0; i < bucket.length; i++) {
      bucket[i] = new java.util.ArrayList<>();
    }
  }
  
  /** Clear the bucket */
  public void clearBuckets() {
    for (int i = 0; i < bucket.length; i++) {
      bucket[i].clear(); 
    }
  }

  /** Start a new sort */
  public void reset() {
    clearBuckets();
    i = 0;
    position = 0;
    initializeList();
  }
  
  /** move the elements from the bucket back to list */
  public void bucketToList() {
    int k = 0; // k is an index for list
    for (int i = 0; i < bucket.length; i++) {
      for (int j = 0; j < bucket[i].size(); j++)
        list[k++] = bucket[i].get(j);
    }
  }

  public final static int ARRAY_SIZE = 20;
  private int position = 0;
  private int i = 0;
  private int key;
  private int[] list = new int[ARRAY_SIZE];
  private java.util.ArrayList<Integer>[] bucket = new java.util.ArrayList[10];

  public boolean step() {
    // Distribute the elements from list to bucket
    if (i < list.length) {
      key = getKey(list[i], position);
      bucket[key].add(list[i]);
      i++;
      return false;
    }
    else if (position < 2) {
      bucketToList();
      clearBuckets(); 
      i = 0; 
      position++;
      return false;
    }
    else {
      bucketToList();
      clearBuckets(); 
      return true;
    }
  }
  
  /** Return the digit at the specified position. 
   * The last digit's position is 0. */
  public static int getKey(int number, int position) {
    int result = 1;
    for (int i = 0; i < position; i++)
      result *= 10;

    return (number / result) % 10;
  }
  
  class AnimationPane extends Pane {
    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 30;
    private int boxHeight = 20;

    protected void repaint() {      
      this.getChildren().clear();
      
      int x = startingX + 10;
      int y = startingY + 10;         
        
      // Display array
      for (int k = 0; k < list.length; k++) {
        Rectangle rectangle = new Rectangle(x, y, boxWidth, boxHeight);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        this.getChildren().add(rectangle);
        Text text = new Text(x + 5, y + 15, list[k] + "");
        this.getChildren().add(text);
        if (i - 1 == k) {
          text.setFill(Color.RED);   
        }
        else {
          text.setFill(Color.BLACK);  
        }

        x = x + boxWidth;
      }
      
      // Display bucket
      x = startingX + 10;
      y += 40;
      int bucketWidth = 40;
      int bucketHeight = 130;
      int bucketDistance = 60;
      for (int i = 0; i < bucket.length; i++) {
        Rectangle rectangle = new Rectangle(x + 10, y, bucketWidth, bucketHeight);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        this.getChildren().add(rectangle);
      
        // Display elements in each bucket      
        for (int j = 0; j < bucket[i].size(); j++) {          
          this.getChildren().add(new Text(x + 10 + 5, y + (j + 1) * 20, bucket[i].get(j) + ""));
        }
        
        if (i == key && bucket[i].size() > 0) {
          Text text = new Text(x + 10 + 5, y + bucket[i].size() * 20, bucket[i].get(bucket[i].size() - 1) + "");
          text.setFill(Color.RED);
          this.getChildren().add(text);
        }

        Text text = new Text(x + 5, y + bucketHeight + 25, "bucket[" + i + "]");
        getChildren().add(text); 
        x = x + bucketDistance;
      }     
    }
  }
}
