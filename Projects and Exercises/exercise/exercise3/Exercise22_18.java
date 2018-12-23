import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise22_18 extends Application {  
  double radius = 2;
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    HistogramPane pane = new HistogramPane();
    pane.setStyle("-fx-border-color: black");
    
    Button btStep = new Button("Step");
    Button btReset = new Button("Reset");
    TextField tfKey = new TextField();
    
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(
      new Label("Key (in double)"), tfKey, btStep, btReset);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);
    
    Label lblStatus = new Label();
    borderPane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 400, 250);
    primaryStage.setTitle("Exercise22_18"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    StepControl control = new StepControl();
    pane.setNumbers(control.getArray());
        
    pane.widthProperty().addListener(ov -> pane.repaint()); 
    pane.heightProperty().addListener(ov -> pane.repaint());
    
    btStep.setOnAction(e -> {    
      if (tfKey.isEditable()) {
        control.setKey(Double.parseDouble(tfKey.getText()));
        tfKey.setEditable(false);
      }

      pane.setColoredBarIndex(control.low, control.high); // Continue
      int status = control.step();

      if (status == 0)
        pane.setColoredBarIndex(control.getCurrentIndex() - 1); // Continue
      else if (status == 1) {
        pane.setColoredBarIndex(control.getCurrentIndex()); // Found
        lblStatus.setText("The key is found in the array at index " +
          control.getCurrentIndex()); // Found
      }
      else if (status == -1)
        lblStatus.setText("The key is not in the array");
    });
    
    btReset.setOnAction(e -> {
      control.reset();
      tfKey.setEditable(true);
      lblStatus.setText("");
      pane.setColoredBarIndex(0, -1);
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
    
  public final static int ARRAY_SIZE = 20;
    
  class StepControl {
    private int[] list = new int[ARRAY_SIZE];
    private double key = 4.5;
    
    public int[] getArray() {
      return list;
    }
    
    public void setKey(double key) {
      this.key = key;
    }

    StepControl() {
      initializeNumbers();
    }

    public void initializeNumbers() {
      for (int i = 0; i < list.length; i++) {
        list[i] = i + 1;
      }
    }

    boolean done = false;
    
    public int getCurrentIndex() {
      return mid;
    }

    public void reset() {
      low = 0;
      high = list.length - 1;
      done = false;
    }
    
    int low = 0;
    int high = list.length - 1;
    int mid = (low + high) / 2;
    
    public int step() {
      if (done) return 1;
      
      if (low > high)
        return -1;

      mid = (low + high) / 2;
      if (key == list[mid]) {
        done = true;
        return 1;
      }
      else if (key > list[mid]) {
        low = mid + 1; // Continue to search the second half
        return 0;
      }      
      else { // key < list[mid] 
        high = mid - 1; // Continue to search the first half
        return 0;
      }      
    }
  }
    
  class HistogramPane extends Pane {
    private int[] numbers;
    private int coloredBarIndex = -1;
    private int low = 0; 
    private int high = -1; // Don't display the low and high area initially
    
    public void setNumbers(int[] numbers) {
      this.numbers = numbers;
      repaint();
    }

    public void setColoredBarIndex(int index) {
      coloredBarIndex = index;
      repaint();
    }

    public void setColoredBarIndex(int low, int high) {
      this.low = low;
      this.high = high;
      repaint();
    }
        
    public void repaint() {
      // Find maximum integer
      int max = numbers[0];
      for (int i = 1; i < numbers.length; i++) {
        if (max < numbers[i]) {
          max = numbers[i];
        }
      }

      this.getChildren().clear();
      
      double height = getHeight() * 0.88;
      double width = getWidth() - 20;
      double unitWidth = width * 1.0 / numbers.length;
      
      int mid = (low + high) / 2;
      
      for (int i = 0; i < numbers.length; i++) {
        Rectangle bar =  new Rectangle(i * unitWidth + 10, getHeight()
          - (numbers[i] * 1.0 / max) * height, unitWidth, (numbers[i] * 1.0 / max) * height);

        if (i <= high && i >= low) {
          bar.setFill(Color.GRAY);
        }
        else {
          bar.setFill(Color.WHITE);
        }
        
        if (high != -1 && i == mid) {
          bar.setFill(Color.RED);
        }
        
        bar.setStroke(Color.BLACK);
        
        this.getChildren().add(bar);
        this.getChildren().add(new Text(i * unitWidth + 10 + 10,
          getHeight() - (numbers[i] * 1.0 / max) * height - 10,
          numbers[i] + ""));
      }
    }
  }
}
