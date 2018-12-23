import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.*;

public class Exercise25_01Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BST<Integer> tree = new BST<>(); // Create a tree
    tree.insert(50);
    tree.insert(25);
    tree.insert(75);
    tree.insert(12);
    tree.insert(30);
    tree.insert(70);
    tree.insert(80);
    tree.insert(110);
    tree.insert(10);   
    
    BorderPane pane = new BorderPane();
    BTView view = new BTView(tree); // Create a View
    pane.setCenter(view);

    TextField tfKey = new TextField();
    tfKey.setPrefColumnCount(3);
    tfKey.setAlignment(Pos.BASELINE_RIGHT);
    Button btSearch = new Button("Search");
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Enter a key: "),
            tfKey, btSearch);
    hBox.setAlignment(Pos.CENTER);
    pane.setBottom(hBox);

    view.displayTree();
    
    btSearch.setOnAction(e -> {
      int key = Integer.parseInt(tfKey.getText());
      
      view.paths = tree.path(key);
      view.displayTree();      
      if (tree.search(key)) { // key is in the tree already
        view.setStatus(key + " is in the tree");
      } else {
        view.setStatus(key + " is not in the tree");
      }
    });

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 450, 250);
    primaryStage.setTitle("Exercise25_01"); // Set the stage title
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
  
  public class BTView extends Pane {
    protected ArrayList<BST.TreeNode<Integer>> paths = 
        new ArrayList<>();
    private BST<Integer> tree = new BST<>();
    private double radius = 15; // Tree node radius
    private double vGap = 50; // Gap between two levels in a tree

    BTView(BST<Integer> tree) {
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
        displayTree(tree.getRoot(), (getWidth() == 0? 450 : getWidth()) / 2, vGap,
          (getWidth() == 0? 200 : getWidth()) / 4);
      }
    }

    /** Display a subtree rooted at position (x, y) */
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
      if (paths.contains(root)) {
        circle.setFill(Color.RED);
      }
      else {
        circle.setFill(Color.WHITE);
      }
      circle.setStroke(Color.BLACK);
      getChildren().addAll(circle,
        new Text(x - 4, y + 4, root.element + ""));
    }
  }
}
