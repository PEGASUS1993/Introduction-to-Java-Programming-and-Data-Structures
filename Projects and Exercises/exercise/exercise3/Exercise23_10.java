import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise23_10 extends Application {  
  private Heap<Integer> heap = new Heap<Integer>();
  private TextField tfKey = new TextField();
  private HeapView heapView = new HeapView(); 
  private Button btInsert = new Button("Insert");
  private Button btDelete = new Button("Remove the root");

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    HBox hBox = new HBox(5);
    tfKey.setPrefColumnCount(2);
    hBox.getChildren().addAll(new Label("Enter a key:"), tfKey, btInsert);
    hBox.setStyle("-fx-border-color: yellow");
    
    HBox hBox1 = new HBox(5);
    hBox1.getChildren().addAll(hBox, btDelete);
    hBox1.setAlignment(Pos.CENTER);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(heapView);
    pane.setBottom(hBox1);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 340, 180);
    primaryStage.setTitle("Exercise23_10"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btInsert.setOnAction(e -> {
      int key = Integer.parseInt(tfKey.getText());
      heap.add(key); // Insert a new key
      heapView.repaint(); // Redisplay the tree
    });

    btDelete.setOnAction(e -> {
      heap.remove(); // Delete t
      heapView.repaint(); // Redisplay the tree
    });
  }
    
  class Heap<E extends Comparable<E>> {
    java.util.ArrayList<E> list = new java.util.ArrayList<E>();

    /** Create a default heap */
    public Heap() {
    }

    /** Create a heap from an array of objects */
    public Heap(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]);
    }

    /** Add a new object into the heap */
    public void add(E newObject) {
      list.add(newObject); // Append to the heap
      int currentIndex = list.size() - 1; // The index of the last node

      while (currentIndex > 0) {
        int parentIndex = (currentIndex - 1) / 2;
        // Swap if the current object is greater than its parent
        if (list.get(currentIndex).compareTo(
            list.get(parentIndex)) > 0) {
          E temp = list.get(currentIndex);
          list.set(currentIndex, list.get(parentIndex));
          list.set(parentIndex, temp);
        }
        else
          break; // the tree is a heap now

        currentIndex = parentIndex;
      }
    }

    /** Remove the root from the heap */
    public E remove() {
      if (list.size() == 0) return null;

      E removedObject = list.get(0);
      list.set(0, list.get(list.size() - 1));
      list.remove(list.size() - 1);

      int currentIndex = 0;
      while (currentIndex < list.size()) {
        int leftChildIndex = 2 * currentIndex + 1;
        int rightChildIndex = 2 * currentIndex + 2;

        // Find the maximum between two children
        if (leftChildIndex >= list.size()) break; // The tree is a heap
        int maxIndex = leftChildIndex;
        if (rightChildIndex < list.size()) {
          if (list.get(maxIndex).compareTo(
              list.get(rightChildIndex)) < 0) {
            maxIndex = rightChildIndex;
          }
        }      

        // Swap if the current node is less than the maximum 
        if (list.get(currentIndex).compareTo(
            list.get(maxIndex)) < 0) {
          E temp = list.get(maxIndex);
          list.set(maxIndex, list.get(currentIndex));
          list.set(currentIndex, temp);
          currentIndex = maxIndex;
        }   
        else 
          break; // The tree is a heap
      }

      return removedObject;
    }

    /** Get the number of nodes in the tree */
    public double getSize() {
      return list.size();
    }
  }

  // Inner class PaintHeap for displaying a tree on a panel
  class HeapView extends Pane {   
    private double radius = 20; // Tree node radius
    private double vGap = 50; // Gap between two levels in a tree

    protected void repaint() {
      this.getChildren().clear();
      
      // Display the nodes in heap recursively    
      displayTree(0, getWidth() / 2, 30, getWidth() / 4); 
    }

    /** Display a subtree root at position (x, y) */
    private void displayTree(int index, double x, double y, double hGap) {
      if (index < heap.getSize()) {
        // Display root
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().add(circle);
        this.getChildren().add(new Text(x - 6, y + 4, heap.list.get(index) + ""));

        // Draw a line to the left node
        int indexOfLeft = 2 * index + 1;
        if (indexOfLeft < heap.getSize())
          connectLeftChild(x - hGap, y + vGap, x, y);

        // Draw the left subtree
        displayTree(indexOfLeft, x - hGap, y + vGap, hGap / 2);

        // Draw a line to the right node
        int indexOfRight = 2 * index + 2;        
        if (indexOfRight < heap.getSize())
          connectRightChild(x + hGap, y + vGap, x, y);

        // Draw the right subtree
        displayTree(indexOfRight, x + hGap, y + vGap, hGap / 2);      
      }
    }

    /** Connect a parent at (x2, y2) with 
     * its left child at (x1, y1) */
    private void connectLeftChild(double x1, double y1, double x2, double y2) { 
      double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
      double x11 = (int)(x1 + radius * (x2 - x1) / d);
      double y11 = (int)(y1 - radius * vGap / d);
      double x21 = (int)(x2 - radius * (x2 - x1) / d);
      double y21 = (int)(y2 + radius * vGap / d);
      getChildren().add(new Line(x11, y11, x21, y21));
    }

    /** Connect a parent at (x2, y2) with 
     * its right child at (x1, y1) */
    private void connectRightChild(double x1, double y1, double x2, double y2) {
      double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
      double x11 = (int)(x1 - radius * (x1 - x2) / d);
      double y11 = (int)(y1 - radius * vGap / d);
      double x21 = (int)(x2 + radius * (x1 - x2) / d);
      double y21 = (int)(y2 + radius * vGap / d);
      getChildren().add(new Line(x11, y11, x21, y21));
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
