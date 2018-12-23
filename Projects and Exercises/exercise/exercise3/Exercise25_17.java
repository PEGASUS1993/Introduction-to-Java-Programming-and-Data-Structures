import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Exercise25_17 extends Application {
  private TreeView treeView = new TreeView();
  private Tree tree;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();

    TextField tfText = new TextField();
    Button btHuffmanTree = new Button("Show Huffman Tree");   
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Enter a text: "), tfText,
      btHuffmanTree);
    
    TextField tfBitString = new TextField();
    Button btDecodeToText = new Button("Decode to Text");   
    HBox hBox2 = new HBox(5);
    hBox2.getChildren().addAll(new Label("Enter a bit string: "), 
      tfBitString, btDecodeToText);   
    hBox2.setAlignment(Pos.CENTER);
        
    hBox.setAlignment(Pos.CENTER);
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(hBox, hBox2);
    pane.setTop(vBox);
    pane.setCenter(treeView);

    Label lblStatus = new Label();
    pane.setBottom(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    btHuffmanTree.setOnAction(e -> {
      String text = tfText.getText();
      int[] counts = getCharacterFrequency(text);
      tree = getHuffmanTree(counts);
      treeView.setTree(tree);

      if (text.length() == 0) {
        lblStatus.setText("No text");          
      }
      else {
        String[] codes = getCode(tree.root);
        lblStatus.setText(
          text + " is encoded to " + encode(text, codes));
      }
    });

    btDecodeToText.setOnAction(e -> {
      String bits = tfBitString.getText();

      if (tree == null) {
        lblStatus.setText("No tree");          
      }
      else if (bits.length() == 0) {
        lblStatus.setText("No codes");
      }
      else {
        String text = decode(bits);
        if (text == null) {
          lblStatus.setText("Incorrect bits");            
        }
        else
          lblStatus.setText(bits + " is decoded to " + text);
      }
    });

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 650, 250);
    primaryStage.setTitle("Exercise25_17: Huffman Coding Animation"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /** Decode the bit string into a text */
  public String decode(String bits) {
    String result = "";
    
    Tree.Node p = tree.root; // Start from the root
    for (int i = 0; i < bits.length(); i++) {
      if (bits.charAt(i) == '0') 
        p = p.left;
      else if (bits.charAt(i) == '1')
        p = p.right;
      else
        return null; // Wrong bits
      
      if (p.left == null) { // A leaf detected
        result += p.element;
        p = tree.root; // Restart from the root
      }
    }
    
    return result;
  }
  
  /** Encode the text using the codes */
  public static String encode(String text, String[] codes) {
    String result = "";
    for (int i = 0; i < text.length(); i++) 
      result += codes[text.charAt(i)];    
    return result;   
  }
  
  /** Get Huffman codes for the characters 
   * This method is called once after a Huffman tree is built
   */
  public static String[] getCode(Tree.Node root) {
    if (root == null) return null;    
    String[] codes = new String[2 * 128];
    assignCode(root, codes);
    return codes;
  }
  
  /* Recursively get codes to the leaf node */
  private static void assignCode(Tree.Node root, String[] codes) {
    if (root.left != null) {
      root.left.code = root.code + "0";
      assignCode(root.left, codes);
      
      root.right.code = root.code + "1";
      assignCode(root.right, codes);
    }
    else {
      codes[(int)root.element] = root.code;
    }
  }
  
  /** Get a Huffman tree from the codes */  
  public static Tree getHuffmanTree(int[] counts) {
    // Create a heap to hold trees
    Heap<Tree> heap = new Heap<>();
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > 0)
        heap.add(new Tree(counts[i], (char)i)); // A leaf node tree
    }
    
    while (heap.getSize() > 1) { 
      Tree t1 = heap.remove(); // Remove the smallest weight tree
      Tree t2 = heap.remove(); // Remove the next smallest weight 
      heap.add(new Tree(t1, t2)); // Combine two trees
    }

    return heap.remove(); // The final tree
  }
  
  /** Get the frequency of the characters */
  public static int[] getCharacterFrequency(String text) {
    int[] counts = new int[256]; // 256 ASCII characters
    
    for (int i = 0; i < text.length(); i++)
      counts[(int)text.charAt(i)]++; // Count the character in text
    
    return counts;
  }
  
  // Inner class TreeView for displaying a tree on a panel
  class TreeView extends Pane {   
    private double radius = 20; // Tree node radius
    private double vGap = 50; // Gap between two levels in a tree
    Tree tree;
    
    public TreeView() { 
    }
    
    public TreeView(Tree tree) {
      this.tree = tree;  
    }
    
    public void setTree(Tree tree) {
      this.tree = tree;
      repaint();
    }
    
    protected void repaint() {
      getChildren().clear();
   
      if (tree == null) return;
      
      if (tree.root != null) {
        // Display tree recursively    
        displayTree(tree.root, getWidth() / 2, 30, getWidth() / 4); 
      }
    }
        
    /** Display a subtree rooted at position (x, y) */
    private void displayTree(Tree.Node root, 
        double x, double y, double hGap) {            
      if (root.left == null) { // Display the character for leaf node
        getChildren().add(new Text(x - 6, y + 34, root.element + ""));
      }
      if (root.left != null) {
        // Draw a line to the left node
        getChildren().addAll(new Line(x - hGap, y + vGap, x, y),
          new Text((x - hGap + x) / 2 - 5, (y + vGap + y) / 2, "0"));
              
        // Draw the left subtree recursively
        displayTree(root.left, x - hGap, y + vGap, hGap / 2);
      }
          
      if (root.right != null) {
        // Draw a line to the right node
        getChildren().addAll(new Line(x + hGap, y + vGap, x, y),
          new Text((x + hGap + x) / 2 + 5, (y + vGap + y) / 2, "1"));
        
        // Draw the right subtree recursively
        displayTree(root.right, x + hGap, y + vGap, hGap / 2);  
      }
      
      // Display the root
      Circle circle = new Circle(x, y, radius);
      circle.setFill(Color.WHITE);
      circle.setStroke(Color.BLACK);
      getChildren().addAll(circle);
      getChildren().addAll(new Text(x - 6, y + 4, root.weight + ""));
    }   
  }

  /** Define a Huffman coding tree */
  public static class Tree implements Comparable<Tree> {
    Node root; // The root of the tree

    /** Create a tree with two subtrees */
    public Tree(Tree t1, Tree t2) {
      root = new Node();
      root.left = t1.root;
      root.right = t2.root;
      root.weight = t1.root.weight + t2.root.weight;
    }
    
    /** Create a tree containing a leaf node */
    public Tree(double weight, char element) {
      root = new Node(weight, element);
    }
    
    @Override /** Compare trees based on their weights */
    public int compareTo(Tree o) {
      if (root.weight < o.root.weight) // Purposely reverse the order
        return 1;
      else if (root.weight == o.root.weight)
        return 0;
      else
        return -1;
    }

    public class Node {
      char element; // Stores the character for a leaf node
      double weight; // weight of the subtree rooted at this node
      Node left; // Reference to the left subtree
      Node right; // Reference to the right subtree
      String code = ""; // The code of this node from the root

      /** Create an empty node */
      public Node() {
      }
      
      /** Create a node with the specified weight and character */
      public Node(double weight, char element) {
        this.weight = weight;
        this.element = element;
      }
    }
  }  
  
  public static class Heap<E extends Comparable<E>> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();

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
    public int getSize() {
      return list.size();
    }
  }
}
