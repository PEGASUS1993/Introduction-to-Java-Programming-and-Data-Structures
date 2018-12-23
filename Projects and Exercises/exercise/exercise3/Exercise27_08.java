import java.util.LinkedList;
import javafx.application.Application;
import javafx.stage.Stage;
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
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Exercise27_08 extends Application {
  private MyHashMap<Integer, Integer> map = new MyHashMap<>(11);
  private HashView view = new HashView();
  private Button btInsert = new Button("Insert");
  private Button btDelete = new Button("Delete");
  private Button btSearch = new Button("Search");
  private Button btRemoveAll = new Button("Remove All");
  private TextField tfNumber = new TextField();
  private TextField tfInitialTableSize = new TextField();
  private TextField tfLoadFactorThreshold = new TextField();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();

    HBox hBox1 = new HBox(5);
    hBox1.getChildren().addAll(new Label("Enter initial table size: "),
            tfInitialTableSize, new Label("Enter a load factor threshold: "),
            tfLoadFactorThreshold);
    hBox1.setAlignment(Pos.CENTER);

    HBox hBox2 = new HBox(5);
    hBox2.getChildren().addAll(new Label("Enter a value: "), tfNumber,
            btInsert, btDelete, btSearch);
    hBox2.setAlignment(Pos.CENTER);
    hBox2.setStyle("-fx-border-color: red; -fx-padding: 2");

    HBox hBox3 = new HBox(5);
    hBox3.getChildren().addAll(hBox2, btRemoveAll);
    hBox3.setAlignment(Pos.CENTER);

    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(hBox1, hBox3);

    Label lblStatus = new Label();
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);

    pane.setCenter(new ScrollPane(view));
    pane.setBottom(vBox);

    tfNumber.setPrefColumnCount(2);
    tfInitialTableSize.setPrefColumnCount(2);
    tfLoadFactorThreshold.setPrefColumnCount(2);
    view.repaint();

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 650, 250);
    primaryStage.setTitle("Exercise27_08: Quardrtic Probing Hashing Animation"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btInsert.setOnAction(e -> {
      try {
        int key = Integer.parseInt(tfNumber.getText());

        if (map.containsKey(key)) {
          lblStatus.setText(key
                  + " is already in the table");
        } else {
          map.put(Integer.parseInt(tfNumber.getText()), 1);
          lblStatus.setText(key
                  + " is inserted in the table");
        }
        view.repaint();
      } catch (NumberFormatException ex) {
        lblStatus.setText("Incorrect number in the text field");
      }
    });

    btDelete.setOnAction(e -> {
      map.remove(Integer.parseInt(tfNumber.getText()));
      lblStatus.setText(tfNumber.getText()
              + " is deleted from the table");
      view.repaint();
    });

    btRemoveAll.setOnAction(e -> {
      map.clear();
      lblStatus.setText("All entried are removed from the table");
      view.repaint();
    });

    tfInitialTableSize.setOnAction(e -> {
      try {
        map = new MyHashMap<Integer, Integer>(Integer.parseInt(tfInitialTableSize.getText()));
        view.repaint();
        lblStatus.setText("Table size is changed");
      } catch (NumberFormatException ex) {
        lblStatus.setText("Incorrect number in the text field");
      }
    });

    btSearch.setOnAction(e -> {
      try {
        int key = Integer.parseInt(tfNumber.getText());

        if (map.containsKey(key)) {
          lblStatus.setText(key + " is in the table");
        } else {
          lblStatus.setText(key + " is not in the table");
        }
      } catch (NumberFormatException ex) {
        lblStatus.setText("Incorrect number in the text field");
      }
    });

    tfLoadFactorThreshold.setOnAction(e -> {
      try {
        double loadFactorThreshold = Double.parseDouble(tfLoadFactorThreshold.getText());
        map.setLoadFactorThreshold((float) loadFactorThreshold);
        view.repaint();
        lblStatus.setText("Table threshold is changed");
      } catch (NumberFormatException ex) {
        lblStatus.setText(
                "Incorrect load factor threshold. in the text field");
      }
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

  private class HashView extends Pane {

    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 40;
    private int boxHeight = 20;

    protected void repaint() {
      getChildren().clear();

      getChildren().add(new Text(
              startingX, startingY, "Table size = " + map.capacity
              + ". Number of keys = " + map.size));
      getChildren().add(new Text(
              startingX, startingY + 20, "Load factor = " + 1.0 * map.size / map.capacity
              + ". Load factor threshold = " + map.loadFactorThreshold + "."));

      int x = startingX;
      int y = startingY + 55;

      for (int i = 0; i < map.capacity; i++) {
        getChildren().add(new Text(x, y, "[" + i + "]"));
        Rectangle rectangle = new Rectangle(startingX + 35, y - 15, boxWidth, boxHeight);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        getChildren().add(rectangle);
        y += 20;
      }

      x += 50;
      y = startingY + 55;
      for (int i = 0; i < map.capacity; i++) {
        if (map.table[i] != null && map.table[i].key != null) {
          getChildren().add(new Text(x, y, map.table[i].key + ""));
        } else if (map.table[i] != null && map.table[i].key == null) {
          getChildren().add(new Text(x, y, "X"));
        }

        y += 20;
      }
    }
  }

  static class MyHashMap<K, V> implements MyMap<K, V> {
    // Define the default hash table size. 
    private static int DEFAULT_INITIAL_CAPACITY = 4;
        
    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30; 
    
    // Current hash table capacity. 
    private int capacity;
    
    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f; 

    // Specify a load factor used in the hash table
    private float loadFactorThreshold; 
    
    public void setLoadFactorThreshold(float loadFactorThreshold) {
      this.loadFactorThreshold = loadFactorThreshold;
    }       
    // The number of entries in the map
    private int size = 0; 
    
    // Hash table is an array with each cell that is a linked list
    MyMap.Entry<K,V>[] table;

    /** Construct a map with the default capacity and load factor */
    public MyHashMap() {  
      this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);    
    }
    
    /** Construct a map with the specified initial capacity and 
     * default load factor */
    public MyHashMap(int initialCapacity) { 
      this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);    
    }
    
    /** Construct a map with the specified initial capacity 
     * and load factor */
    public MyHashMap(int initialCapacity, float loadFactorThreshold) { 
      this.capacity = initialCapacity;     
      this.loadFactorThreshold = loadFactorThreshold;    
      table = new MyMap.Entry[capacity];
    }
    
    /** Remove all of the entries from this map */ 
    public void clear() {
      size = 0;
      removeEntries();
    }

    /** Return true if the specified key is in the map */
    public boolean containsKey(K key) {
      if (get(key) != null)
        return true;
      else
        return false;
    }
    
    /** Return true if this map contains the specified value */ 
    public boolean containsValue(V value) {
      for (int i = 0; i < table.length; i++)
        if (table[i] != null && table[i].value.equals(value)) 
          return true;
      
      return false;
    }
    
    /** Return a set of entries in the map */
    public java.util.Set<MyMap.Entry<K,V>> entrySet() {
      java.util.Set<MyMap.Entry<K, V>> set = 
        new java.util.HashSet<MyMap.Entry<K, V>>();
      
      for (int i = 0; i < capacity; i++) 
        if (table[i] != null) 
          set.add(table[i]); 
      
      return set;
    }

    /** Return the first value that matches the specified key */
    public V get(K key) {
      // Perform linear probing
      int k = hash(key.hashCode());
      int starting = k;
      int i = k;
      int j = 1;
      while (table[i] != null) {
        if (table[i].key != null && table[i].key.equals(key)) 
          return table[i].value;
        i = Math.abs((k + j * j) % table.length);
        if (i == starting) return null;
        j++;
      }
      
      return null;
    }
    
    /** Return all values for the specified key in this map */
    public java.util.Set<V> getAll(K key) {
      java.util.Set<V> set = new java.util.HashSet<V>();
      
      for (int i = 0; i < capacity; i++) 
        if (table[i] != null && table[i].key.equals(key)) 
          set.add(table[i].value); 
      
      return set;
    }

    /** Return true if this map contains no entries */
    public boolean isEmpty() {
      return size == 0;
    }  
    
    /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet() {
      java.util.Set<K> set = new java.util.HashSet<K>();
      
      for (int i = 0; i < capacity; i++) 
        if (table[i] != null) 
          set.add(table[i].key); 
      
      return set;
    }
        
    /** Add an entry (key, value) into the map */
    public V put(K key, V value) {
      if (this.containsKey(key)) // Duplicate element not stored
    	  return value;
      
      if (size + 1 > capacity * loadFactorThreshold) {
        if (capacity == MAXIMUM_CAPACITY)
          throw new RuntimeException("Exceeding maximum capacity");
        
        rehash();
      }
      
      int k = hash(key.hashCode());      
      int j = 1;
      int i = k;
      System.out.println("i is " + i);
      while (table[i] != null && table[i].key != null) {
        i = Math.abs((k + j * j) % table.length);
        j++;
      }
      
      // Add an entry (key, value) to the table
      table[i] = new MyMap.Entry<K, V>(key, value);

      size++; // Increase size
      
      return value;  
    } 
   
    /** Remove the element for the specified key */
    public void remove(K key) {
      int k = hash(key.hashCode());
      int j = 1;
      int i = k;
      while (table[i] != null && (table[i].key == null || !table[i].key.equals(key))) {
        i = Math.abs((k + j * j) % table.length);
        j++;
      }

      if (table[i] != null && table[i].key.equals(key)) {
        // A special marker Entry(null, null) is placed for the deleted entry
        table[i] = new MyMap.Entry<K, V>(null, null);
        size--;
      }
    }
    
    /** Return the number of mappings in this map */
    public int size() {
      return size;
    }
    
    /** Return a set consisting of the values in this map */
    public java.util.Set<V> values() {
      java.util.Set<V> set = new java.util.HashSet<V>();
      
      for (int i = 0; i < capacity; i++) 
        if (table[i] != null) 
          set.add(table[i].value); 
      
      return set;
    }
    
    /** Hash function */
    private int hash(int hashCode) {
      return hashCode % capacity;
//      return supplementalHash(hashCode) & (capacity - 1);
    }
    
    /** Ensure the hashing is evenly distributed */
    private static int supplementalHash(int h) {
      h ^= (h >>> 20) ^ (h >>> 12);
      return h ^ (h >>> 7) ^ (h >>> 4);
    }
    
    /** Remove all entries from each bucket */
    private void removeEntries() {
      for (int i = 0; i < capacity; i++) 
        table[i] = null;
    }
    
    /** Rehash the map */
    private void rehash() {
      java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
      capacity <<= 1; // Double capacity    
      table = new Entry[capacity]; // Create a new hash table
      
      size = 0; // Clear size
      
      for (Entry<K, V> entry: set) {
        put(entry.getKey(), entry.getValue()); // Store to new table
      }
    }

    @Override /** Return a string representation for this map */
    public String toString() {
      StringBuilder builder = new StringBuilder("[");
      
      for (int i = 0; i < capacity; i++) {
        if (table[i] != null && table[i].key != null) 
          builder.append(table[i].toString());
      }
      
      return builder.append("]").toString();
    }
  }

  interface MyMap<K, V> {
    /** Remove all of the entries from this map */ 
    public void clear();
    
    /** Return true if the specified key is in the map */
    public boolean containsKey(K key);
    
    /** Return true if this map contains the specified value */ 
    public boolean containsValue(V value);

    /** Return a set of entries in the map */
    public java.util.Set<Entry<K, V>> entrySet();

    /** Return the first value that matches the specified key */
    public V get(K key);
    
    /** Return all values for the specified key in this map */
    public java.util.Set<V> getAll(K key);
    
    /** Return true if this map contains no entries */
    public boolean isEmpty();

    /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet();
    
    /** Add an entry (key, value) into the map */
    public V put(K key, V value);

    /** Remove the entries for the specified key */
    public void remove(K key);

    /** Return the number of mappings in this map */
    public int size();

    /** Return a set consisting of the values in this map */
    public java.util.Set<V> values();
    
    /** Define inner class for Entry */
    public static class Entry<K, V> {
      K key;
      V value;
      
      public Entry(K key, V value) {
        this.key = key;
        this.value = value;
      }
      
      public K getKey() {
        return key;
      }
      
      public V getValue() {
        return value;
      }
      
      @Override
      public String toString() {
        return "[" + key + ", " + value + "]";
      }
    }
  }    
}
