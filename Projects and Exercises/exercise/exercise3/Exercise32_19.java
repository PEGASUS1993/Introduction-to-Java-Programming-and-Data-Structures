import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise32_19 extends Application {
  private HistogramPane displayArrayPane1 = new
    HistogramPane();
  private HistogramPane displayArrayPane2 = new
    HistogramPane();
  private HistogramPane displayArrayPane3 = new
    HistogramPane();

  private int[] numbers1 = initializeNumbers();
  private int[] numbers2 = numbers1.clone();
  private int[] numbers3 = numbers1.clone();

  public final static int PAUSE_TIME = 450;
  public final static int ARRAY_SIZE = 50;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    displayArrayPane1.setNumbers(numbers1);
    displayArrayPane2.setNumbers(numbers2);
    displayArrayPane3.setNumbers(numbers3);
    
    BorderPane p1 = new BorderPane();
    BorderPane p2 = new BorderPane();
    BorderPane p3 = new BorderPane();
    
    p1.setCenter(displayArrayPane1);
    Label lbl1 = new Label("Selection Sort");
    p1.setTop(lbl1);
    BorderPane.setAlignment(lbl1, Pos.CENTER);
    
    p2.setCenter(displayArrayPane2);
    Label lbl2 = new Label("Insertion Sort");
    p2.setTop(lbl2);
    BorderPane.setAlignment(lbl2, Pos.CENTER);

    p3.setCenter(displayArrayPane3);
    Label lbl3 = new Label("Bubble Sort");
    p3.setTop(lbl3);
    BorderPane.setAlignment(lbl3, Pos.CENTER);

    HBox hBox = new HBox(15);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(p1, p2, p3);
       
    // Create a scene and place it in the stage
    Scene scene = new Scene(hBox, 980, 350);
    primaryStage.setTitle("Exercise32_19"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    new Thread(new Runnable() {
      public void run() {
        selectionSort(numbers1);
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        insertionSort(numbers2);
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        bubbleSort(numbers3);
      }
    }).start();
  }

  int i2 = 0;
  public void selectionSort(int[] list) {
    for (i2 = 0; i2 < list.length - 1; i2++) {
      // Find the minimum in the list[i..list.length-1]
      int currentMin = list[i2];
      int currentMinIndex = i2;

      for (int j = i2 + 1; j < list.length; j++) {
        if (currentMin > list[j]) {
          currentMin = list[j];
          currentMinIndex = j;
        }
      }

      // Swap list[i] with list[currentMinIndex] if necessary;
      if (currentMinIndex != i2) {
        list[currentMinIndex] = list[i2];
        list[i2] = currentMin;
      }
 
      try {
        Thread.sleep(PAUSE_TIME);
      }
      catch (InterruptedException e) {}
      
      Platform.runLater(() -> 
        displayArrayPane1.setColoredBarIndex(i2));
    }
  }

  /** The method for sorting the numbers */
  int i1;
  public void insertionSort(int[] list) {
    for (i1 = 1; i1 < list.length; i1++) {
      /** insert list[i] into a sorted sublist list[0..i-1] so that
           list[0..i] is sorted. */
      int currentElement = list[i1];
      int k;
      for (k = i1 - 1; k >= 0 && list[k] > currentElement; k--) {
        list[k + 1] = list[k];
      }

      // Insert the current element into list[k+1]
      list[k + 1] = currentElement;
      try {
        Thread.sleep(PAUSE_TIME);
      }
      catch (InterruptedException e) {}

      Platform.runLater(() -> 
        displayArrayPane2.setColoredBarIndex(i1));
    }
  }

  /** The method for sorting the numbers */
  int i3;
  public void bubbleSort(int[] list) {
    boolean needNextPass = true;

    for (i3 = 1; i3 < list.length && needNextPass; i3++) {
      // Array may be sorted and next pass not needed
      needNextPass = false;
      for (int i = 0; i < list.length - i3; i++) {
        if (list[i] > list[i + 1]) {
          // Swap list[i] with list[i + 1]
          int temp = list[i];
          list[i] = list[i + 1];
          list[i + 1] = temp;

          needNextPass = true; // Next pass still needed
        }
      }  

      try {
        Thread.sleep(PAUSE_TIME);
      }
      catch (InterruptedException e) {}

      Platform.runLater(() -> 
        displayArrayPane3.setColoredBarIndex(list.length - i3)); // setNumbers(numbers);
    }
  }

  public int[] initializeNumbers() {
    int[] numbers = new int[ARRAY_SIZE];
    for (int i = 0; i < numbers.length; i++) {
      numbers[i] = i + 1;
    }

    // Random shuffle
    for (int i = 0; i < numbers.length; i++) {
      int index = (int)(Math.random() * ARRAY_SIZE);
      int temp = numbers[i];
      numbers[i] = numbers[index];
      numbers[index] = temp;
    }

    return numbers;
  }

  class HistogramPane extends Pane {
    private int[] numbers;
    private int coloredBarIndex;
    
    public void setNumbers(int[] numbers) {
      this.numbers = numbers;
      repaint();
    }
  
    public void setColoredBarIndex(int index) {
      coloredBarIndex = index;
      repaint();
    }

    protected void repaint() {
      getChildren().clear();
      
      // Find maximum integer
      int max = numbers[0];
      for (int i = 1; i < numbers.length; i++) {
        if (max < numbers[i]) {
          max = numbers[i];
        }
      }
  
      double h = 200;
      double w = 200;
      double height = h * 0.98;
      double width = w;
      double unitWidth = width / numbers.length;
      for (int i = 0; i < numbers.length; i++) {
        Rectangle rectangle = new Rectangle(
          i * unitWidth, h - (numbers[i] * 1.0 / max) * height,
          unitWidth, (numbers[i] * 1.0 / max) * height);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        getChildren().add(rectangle);
      }
      
      if (coloredBarIndex < ARRAY_SIZE && coloredBarIndex > 0) {
        int i = coloredBarIndex;
        Rectangle rectangle = new Rectangle(
          i * unitWidth, h - (numbers[i] * 1.0 / max) * height,
          unitWidth, (numbers[i] * 1.0 / max) * height);
        getChildren().add(rectangle);
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
