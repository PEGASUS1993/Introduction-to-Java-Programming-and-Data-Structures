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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise24_08 extends Application {  
  private MyArrayList<Integer> list = new MyArrayList<>();
  private ListView view = new ListView();
  private Button btSearch = new Button("Search");
  private Button btInsert = new Button("Insert");
  private Button btDelete = new Button("Delete");
  private Button btTrimToSize = new Button("TrimToSize");
  private TextField tfNumber = new TextField();
  private TextField tfIndex = new TextField();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {         
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Enter a value: "),
      tfNumber, new Label("Enter an index: "), tfIndex, btSearch, 
      btInsert, btDelete, btTrimToSize);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(view);
    borderPane.setBottom(hBox);
    
    Label lblStatus = new Label();
    borderPane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 500, 250);
    primaryStage.setTitle("Exercise24_08: ArrayList Animation"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    view.repaint();
    tfNumber.setPrefColumnCount(2); 
    tfIndex.setPrefColumnCount(2); 
  
    btSearch.setOnAction(e -> {
      lblStatus.setText("");
      if (!list.contains(Integer.parseInt(tfNumber.getText()))) {
        lblStatus.setText("key is not in the list");
      }   
      else {
        lblStatus.setText("key is in the list");
      }
      view.repaint();
    });

    btInsert.setOnAction(e -> {
      lblStatus.setText("");
      if (tfIndex.getText().trim().length() > 0)
        list.add(Integer.parseInt(tfIndex.getText()), Integer.parseInt(tfNumber.getText()));
      else
        list.add(Integer.parseInt(tfNumber.getText()));
      view.repaint();
    });
    
    btDelete.setOnAction(e -> {
      lblStatus.setText("");
      if (!list.contains(Integer.parseInt(tfNumber.getText()))) {
        lblStatus.setText("key is not in the list");
      }       
      else {
        lblStatus.setText("key is deleted from the list");
        list.remove(new Integer(Integer.parseInt(tfNumber.getText())));
        view.repaint();
      }
    });
    
    btTrimToSize.setOnAction(e -> {
      list.trimToSize();
      view.repaint();
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

  public class ListView extends Pane {
    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 30;
    private int boxHeight = 20;
    
    protected void repaint() {
      getChildren().clear();
      
      int x = startingX + 10;
      int y = startingY + 10;        

      getChildren().add(new Text(
          startingX + 80, startingY, "size = " + list.size() 
          + " and capacity = " + list.getCapacity()));
      if (list.size() == 0) {
        getChildren().add(new Text(startingX, startingY, "list is empty."));
      }
      else {
        getChildren().add(new Text(startingX, startingY, "array list"));        
        
        for (int i = 0; i < list.size(); i++) {
          Rectangle rectangle = new Rectangle(x, y, boxWidth, boxHeight);
          rectangle.setFill(Color.WHITE);
          rectangle.setStroke(Color.BLACK);
          getChildren().add(rectangle);
          getChildren().add(new Text(x + 10, y + 15, list.get(i) + ""));
          x = x + boxWidth;
        }
      }
      
      for (int i = list.size(); i < list.getCapacity(); i++) {
        Rectangle rectangle = new Rectangle(x, y, boxWidth, boxHeight);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        getChildren().add(rectangle);
        getChildren().add(new Line(x + boxWidth, y, x, y + boxHeight));
        x = x + boxWidth;
      }
    }
  }
  
  public class MyArrayList<E> extends MyAbstractList<E> {
    public static final int INITIAL_CAPACITY = 4;
    private E[] data = (E[])new Object[INITIAL_CAPACITY];

    public int getCapacity() {
      return data.length;
    }
    
    /** Create a default list */
    public MyArrayList() {
    }

    /** Create a list from an array of objects */
    public MyArrayList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]);
    }

    /** Add a new element at the specified index in this list */
    public void add(int index, E e) {
      ensureCapacity();

      // Move the elements to the right after the specified index
      for (int i = size - 1; i >= index; i--)
        data[i + 1] = data[i];

      // Insert new element to data[index]
      data[index] = e;

      // Increase size by 1
      size++;
    }

    /** Create a new larger array, double the current size + 1 */
    private void ensureCapacity() {
      if (size >= data.length) {
        E[] newData = (E[])(new Object[size * 2 + 1]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      }
    }

    /** Clear the list */
    public void clear() {
      data = (E[])new Object[INITIAL_CAPACITY];
      size = 0;
    }

    /** Return true if this list contains the element */
    public boolean contains(E e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return true;

      return false;
    }

    /** Return the element from this list at the specified index */
    public E get(int index) {
      return data[index];
    }

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(E e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return i;

      return -1;
    }

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e) {
      for (int i = size - 1; i >= 0; i--)
        if (e.equals(data[i])) return i;

      return -1;
    }

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index) {
      E e = data[index];

      // Shift data to the left
      for (int j = index; j < size - 1; j++)
        data[j] = data[j + 1];

      data[size - 1] = null; // This element is now null
      
      // Decrement size
      size--;

      return e;
    }

    /** Replace the element at the specified position in this list
     *  with the specified element. */
    public E set(int index, E e) {
      E old = data[index];
      data[index] = e;
      return old;
    }

    @Override /** Override toString() to return elements in the list */
    public String toString() {
      StringBuilder result = new StringBuilder("[");

      for (int i = 0; i < size; i++) {
        result.append(data[i]);
        if (i < size - 1) result.append(", ");
      }

      return result.toString() + "]";
    }
    
    /** Trims the capacity to current size */
    public void trimToSize() {
      if (size != data.length) { // If size == capacity, no need to trim
        E[] newData = (E[])(new Object[size]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      }
    }
  }
  
  public abstract class MyAbstractList<E> implements MyList<E> {
    protected int size = 0; // The size of the list

    /** Create a default list */
    protected MyAbstractList() {
    }

    /** Create a list from an array of objects */
    protected MyAbstractList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]);
    }

    /** Add a new element at the end of this list */
    public void add(E e) {
      add(size, e);
    }

    /** Return true if this list contains no elements */
    public boolean isEmpty() {
      return size == 0;
    }

    /** Return the number of elements in this list */
    public int size() {
      return size;
    }

    /** Remove the first occurrence of the element o from this list.
     *  Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public boolean remove(E e) {
      if (indexOf(e) >= 0) {
        remove(indexOf(e));
        return true;
      }
      else
        return false;
    }
  }

  public interface MyList<E> {
    /** Add a new element at the end of this list */
    public void add(E e);

    /** Add a new element at the specified index in this list */
    public void add(int index, E e);

    /** Clear the list */
    public void clear();

    /** Return true if this list contains the element */
    public boolean contains(E e);

    /** Return the element from this list at the specified index */
    public E get(int index);

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(E e);

    /** Return true if this list contains no elements */
    public boolean isEmpty();

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e);

    /** Remove the first occurrence of the element o from this list.
     *  Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public boolean remove(E e);

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index);

    /** Replace the element at the specified position in this list
     *  with the specified element and returns the new set. */
    public Object set(int index, E e);

    /** Return the number of elements in this list */
    public int size();
  }

}
