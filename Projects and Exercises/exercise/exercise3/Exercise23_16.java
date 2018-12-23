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

public class Exercise23_16 extends Application {  
  double radius = 2;
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    HistogramPane pane = new HistogramPane();
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
    primaryStage.setTitle("Exercise23_16"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    StepControl control = new StepControl();
    pane.setNumbers(control.getArray());
    
    pane.widthProperty().addListener(ov -> pane.repaint()); 
    pane.heightProperty().addListener(ov -> pane.repaint());
    
    btStep.setOnAction(e -> {    
      if (control.step())
        pane.setColoredBarIndex(control.getCurrentIndex());
      else
        lblStatus.setText("The array is already sorted");
    });
    
    btReset.setOnAction(e -> {    
      control.reset();
      pane.setColoredBarIndex(-1);
      lblStatus.setText("");
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

    public int[] getArray() {
      return list;
    }

    StepControl() {
      initializeNumbers();
    }

    public void initializeNumbers() {
      for (int i = 0; i < list.length; i++) {
        list[i] = i + 1;
      }

      // Random shuffle
      for (int i = 0; i < list.length; i++) {
        int index = (int) (Math.random() * ARRAY_SIZE);
        int temp = list[i];
        list[i] = list[index];
        list[index] = temp;
      }
    }

    private int i = 1;
    private int j = 0;
    
    public int getCurrentIndex() {
      return j;
    }

    public void reset() {
      i = 1;
      j = 0;
      initializeNumbers();
    }

    public boolean step() {

      if (i >= list.length)
        return false;

      if (j < list.length - i) {
        if (list[j] > list[j + 1]) {
          // Swap list[i] with list[i + 1]
          int temp = list[j];
          list[j] = list[j + 1];
          list[j + 1] = temp;
        }
        j++;
      }
      else {
        i++;
        j = 0;
      }

      return true;
    }
  }
    
  class HistogramPane extends Pane {
    private int[] numbers;
    private int coloredBarIndex = -1;

    public void setNumbers(int[] numbers) {
      this.numbers = numbers;
      repaint();
    }

    public void setColoredBarIndex(int index) {
      coloredBarIndex = index;
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
      for (int i = 0; i < numbers.length; i++) {
        Rectangle bar =  new Rectangle(i * unitWidth + 10, getHeight()
          - (numbers[i] * 1.0 / max) * height, unitWidth, (numbers[i] * 1.0 / max) * height);
        bar.setFill(Color.WHITE);
        bar.setStroke(Color.BLACK);
        this.getChildren().add(bar);
        this.getChildren().add(new Text(i * unitWidth + 10 + 10,
          getHeight() - (numbers[i] * 1.0 / max) * height - 10,
          numbers[i] + ""));
      }

      if (coloredBarIndex >= 0) {
        int i = coloredBarIndex;
        Rectangle filledRectangle = new Rectangle(i * unitWidth + 10, getHeight()
          - (numbers[i] * 1.0 / max) * height, unitWidth, (numbers[i] * 1.0 / max) * height);
        filledRectangle.setFill(Color.RED);
        this.getChildren().add(filledRectangle);
      }
    }
  }
}
