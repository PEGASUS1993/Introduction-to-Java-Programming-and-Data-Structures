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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Exercise25_13 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BST<Integer> tree = new BST<>(); // Create a tree

    BorderPane pane = new BorderPane();
    TreeView view = new TreeView(tree); // Create a TreeView
    pane.setCenter(view);

    TextField tfKey = new TextField();
    tfKey.setPrefColumnCount(3);
    tfKey.setAlignment(Pos.BASELINE_RIGHT);
    Button btInsert = new Button("Insert");
    Button btDelete = new Button("Delete");
    Button btShowInorder = new Button("Show Inorder");
    Button btPreorder = new Button("Show Preorder");
    Button btPostOrder = new Button("Show Postorder");
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Enter a key: "),
          tfKey, btInsert, btDelete, btShowInorder, btPreorder,
          btPostOrder);
    hBox.setAlignment(Pos.CENTER);
    pane.setBottom(hBox);

    Label lblStatus = new Label();
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    btInsert.setOnAction(e -> {
      int key = Integer.parseInt(tfKey.getText());
      if (tree.search(key)) { // key is in the tree already
        view.displayTree();
        view.setStatus(key + " is already in the tree");
      } else {
        tree.insert(key); // Insert a new key
        view.displayTree();
        view.setStatus(key + " is inserted in the tree");
      }
    });

    btDelete.setOnAction(e -> {
      int key = Integer.parseInt(tfKey.getText());
      if (!tree.search(key)) { // key is not in the tree
        view.displayTree();
        view.setStatus(key + " is not in the tree");
      } else {
        tree.delete(key); // Delete a key
        view.displayTree();
        view.setStatus(key + " is deleted from the tree");
      }
    });

    btShowInorder.setOnAction(e -> {
      lblStatus.setText("Inorder: " + tree.inorderList().toString());
    });

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 650, 250);
    primaryStage.setTitle("Exercise25_13: BSTAnimation"); // Set the stage title
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

  class TreeView extends Pane {

    private BST<Integer> tree = new BST<>();
    private double radius = 15; // Tree node radius
    private double vGap = 50; // Gap between two levels in a tree

    TreeView(BST<Integer> tree) {
      this.tree = tree;
      setStatus("Tree is empty");
    }

    public void setStatus(String msg) {
      getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree() {
      this.getChildren().clear(); // Clear the pane
      if (tree.getRoot() != null) {
        // Display tree recursively    
        displayTree(tree.getRoot(), getWidth() / 2, vGap,
                getWidth() / 4);
      }
    }

    /**
     * Display a subtree rooted at position (x, y)
     */
    private void displayTree(BST.TreeNode<Integer> root,
            double x, double y, double hGap) {
      if (root.left != null) {
        // Draw a line to the left node
        getChildren().add(new Line(x - hGap, y + vGap, x, y));
        // Draw the left subtree recursively
        displayTree(root.left, x - hGap, y + vGap, hGap / 2);
      }

      if (root.right != null) {
        // Draw a line to the right node
        getChildren().add(new Line(x + hGap, y + vGap, x, y));
        // Draw the right subtree recursively
        displayTree(root.right, x + hGap, y + vGap, hGap / 2);
      }

      // Display a node
      Circle circle = new Circle(x, y, radius);
      circle.setFill(Color.WHITE);
      circle.setStroke(Color.BLACK);
      getChildren().addAll(circle,
              new Text(x - 4, y + 4, root.element + ""));
    }
  }

  public static class BST<E extends Comparable<E>>
          extends AbstractTree<E> {

    protected TreeNode<E> root;
    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BST() {
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BST(E[] objects) {
      for (int i = 0; i < objects.length; i++) {
        insert(objects[i]);
      }
    }

    @Override
    /**
     * Returns true if the element is in the tree
     */
    public boolean search(E e) {
      TreeNode<E> current = root; // Start from the root

      while (current != null) {
        if (e.compareTo(current.element) < 0) {
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          current = current.right;
        } else // element matches current.element
        {
          return true; // Element is found
        }
      }

      return false;
    }

    @Override
    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully
     */
    public boolean insert(E e) {
      if (root == null) {
        root = createNewNode(e); // Create a new root
      } else {
        // Locate the parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
          if (e.compareTo(current.element) < 0) {
            parent = current;
            current = current.left;
          } else if (e.compareTo(current.element) > 0) {
            parent = current;
            current = current.right;
          } else {
            return false; // Duplicate node not inserted
          }
        }
        // Create the new node and attach it to the parent node
        if (e.compareTo(parent.element) < 0) {
          parent.left = createNewNode(e);
        } else {
          parent.right = createNewNode(e);
        }
      }

      size++;
      return true; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
      return new TreeNode<E>(e);
    }

    @Override
    /**
     * Inorder traversal from the root
     */
    public void inorder() {
      inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     */
    protected void inorder(TreeNode<E> root) {
      if (root == null) {
        return;
      }
      inorder(root.left);
      System.out.print(root.element + " ");
      inorder(root.right);
    }

    @Override
    /**
     * Postorder traversal from the root
     */
    public void postorder() {
      postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     */
    protected void postorder(TreeNode<E> root) {
      if (root == null) {
        return;
      }
      postorder(root.left);
      postorder(root.right);
      System.out.print(root.element + " ");
    }

    @Override
    /**
     * Preorder traversal from the root
     */
    public void preorder() {
      preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     */
    protected void preorder(TreeNode<E> root) {
      if (root == null) {
        return;
      }
      System.out.print(root.element + " ");
      preorder(root.left);
      preorder(root.right);
    }

    /**
     * This inner class is static, because it does not access any instance
     * members defined in its outer class
     */
    public static class TreeNode<E extends Comparable<E>> {

      protected E element;
      protected TreeNode<E> left;
      protected TreeNode<E> right;

      public TreeNode(E e) {
        element = e;
      }
    }

    @Override
    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
      return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode<E> getRoot() {
      return root;
    }

    /**
     * Returns a path from the root leading to the specified element
     */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
      java.util.ArrayList<TreeNode<E>> list
              = new java.util.ArrayList<>();
      TreeNode<E> current = root; // Start from the root

      while (current != null) {
        list.add(current); // Add the node to the list
        if (e.compareTo(current.element) < 0) {
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          current = current.right;
        } else {
          break;
        }
      }

      return list; // Return an array list of nodes
    }

    @Override
    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     */
    public boolean delete(E e) {
      // Locate the node to be deleted and also locate its parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null) {
        if (e.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        } else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        } else {
          break; // Element is in the tree pointed at by current
        }
      }

      if (current == null) {
        return false; // Element is not in the tree
      }
      // Case 1: current has no left child
      if (current.left == null) {
        // Connect the parent with the right child of the current node
        if (parent == null) {
          root = current.right;
        } else {
          if (e.compareTo(parent.element) < 0) {
            parent.left = current.right;
          } else {
            parent.right = current.right;
          }
        }
      } else {
        // Case 2: The current node has a left child
        // Locate the rightmost node in the left subtree of
        // the current node and also its parent
        TreeNode<E> parentOfRightMost = current;
        TreeNode<E> rightMost = current.left;

        while (rightMost.right != null) {
          parentOfRightMost = rightMost;
          rightMost = rightMost.right; // Keep going to the right
        }

        // Replace the element in current by the element in rightMost
        current.element = rightMost.element;

        // Eliminate rightmost node
        if (parentOfRightMost.right == rightMost) {
          parentOfRightMost.right = rightMost.left;
        } else // Special case: parentOfRightMost == current
        {
          parentOfRightMost.left = rightMost.left;
        }
      }

      size--;
      return true; // Element deleted successfully
    }

    @Override
    /**
     * Obtain an iterator. Use inorder.
     */
    public java.util.Iterator<E> iterator() {
      return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
      // Store the elements in a list

      private java.util.ArrayList<E> list
              = new java.util.ArrayList<E>();
      private int current = 0; // Point to the current element in list

      public InorderIterator() {
        inorder(); // Traverse binary tree and store elements in list
      }

      /**
       * Inorder traversal from the root
       */
      private void inorder() {
        inorder(root);
      }

      /**
       * Inorder traversal from a subtree
       */
      private void inorder(TreeNode<E> root) {
        if (root == null) {
          return;
        }
        inorder(root.left);
        list.add(root.element);
        inorder(root.right);
      }

      @Override
      /**
       * More elements for traversing?
       */
      public boolean hasNext() {
        if (current < list.size()) {
          return true;
        }

        return false;
      }

      @Override
      /**
       * Get the current element and move to the next
       */
      public E next() {
        return list.get(current++);
      }

      @Override
      /**
       * Remove the current element
       */
      public void remove() {
        delete(list.get(current)); // Delete the current element
        list.clear(); // Clear the list
        inorder(); // Rebuild the list
      }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
      root = null;
      size = 0;
    }
    
    /** Inorder traversal from the root */
    public java.util.List<E> inorderList() {
      java.util.List<E> list = new java.util.ArrayList<E>();
      inorderList(root, list);
      return list;
    }

    /** Inorder traversal from a subtree */
    protected void inorderList(TreeNode<E> root, java.util.List<E> list) {
      if (root == null)
        return;
      inorderList(root.left, list);
      list.add(root.element);
      inorderList(root.right, list);
    }
    
    /** Postorder traversal from the root */
    public java.util.List<E> postorderList() {
      java.util.List<E> list = new java.util.ArrayList<E>();
      postorderList(root, list);
      return list;
    }

    /** Postorder traversal from a subtree */
    protected void postorderList(TreeNode<E> root, java.util.List<E> list) {
      if (root == null)
        return;
      postorderList(root.left, list);
      postorderList(root.right, list);
      list.add(root.element);
    }
    
    /** Preorder traversal from the root */
    public java.util.List<E> preorderList() {
      java.util.List<E> list = new java.util.ArrayList<E>();
      preorderList(root, list);
      return list;     
    }

    /** Preorder traversal from a subtree */
    protected void preorderList(TreeNode<E> root, java.util.List<E> list) {
      if (root == null)
        return;
      list.add(root.element);    
      preorderList(root.left, list);
      preorderList(root.right, list);
    }
  }
}
