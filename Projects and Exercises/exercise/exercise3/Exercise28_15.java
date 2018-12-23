import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;

public class Exercise28_15 extends Application {
  public static final int DIMENSION = 4;

  // Create the initial board
  private InitialNodePane initialNodePane = new InitialNodePane();
  private Button btSolve = new Button("Solve");
  private Button btStartOver = new Button("Start Over");
  private Label lblStatus = new Label();
  private TailModel model = new TailModel();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();

    // solutionPanel holds a sequence of panels for displaying nodes
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btSolve, btStartOver);
    hBox.setAlignment(Pos.CENTER);

    HBox solutionPane = new HBox(5);
    solutionPane.getChildren().add(initialNodePane);

    pane.setCenter(new ScrollPane(solutionPane));
    pane.setBottom(hBox);
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 650, 250);
    primaryStage.setTitle("Exercise28_15: Sixteeen Tail Problem"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // Listener for the Solve button
    btSolve.setOnAction(e -> {
      solutionPane.getChildren().clear();

      // Get a shortest path
      java.util.List<Integer> list = model
              .getShortestPath(TailModel
              .getIndex(initialNodePane.getNode()));

      // Display nodes in the shortest path
      if (list == null) {
        lblStatus.setText("No solution");
      } else {
        lblStatus.setText("A solution is found");
        for (int i = 0; i < list.size(); i++) {
          if (i != 0) {
            solutionPane.getChildren().add(new NodePane(TailModel
                    .getNode(list.get(i)), TailModel
                    .getNode(list.get(i - 1))));
          } else {
            solutionPane.getChildren().add(new NodePane(TailModel
                    .getNode(list.get(i))));
          }
        }
      }
    });

    // Listener for the Start Over button
    btStartOver.setOnAction(e -> {
      lblStatus.setText("");
      solutionPane.getChildren().clear();
      solutionPane.getChildren().add(initialNodePane); // Display initial node
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * An inner class for displaying a node on a grid pane
   */
  static class NodePane extends GridPane {

    public NodePane(char[] node) {
      this.setStyle("-fx-border-color: black");
      for (int i = 0; i < DIMENSION * DIMENSION; i++) {
        add(new Cell(node[i] + ""), i % DIMENSION, i / DIMENSION);
      }
    }

    public NodePane(char[] newNode, char[] oldNode) {
      this.setStyle("-fx-border-color: black");
      for (int i = 0; i < DIMENSION * DIMENSION; i++) {
        if (newNode[i] == oldNode[i]) {
          add(new Cell(newNode[i] + "", Color.BLACK), i % DIMENSION, i / DIMENSION);
        } else {
          add(new Cell(newNode[i] + "", Color.RED), i % DIMENSION, i / DIMENSION);
        }
      }
    }
  }

  /**
   * An inner class for displaying a cell
   */
  static class Cell extends Label {

    public Cell(String s) {
      this.setFont(new Font("Times", 40));
      setText(s);
    }

    public Cell(String s, Color color) {
      this.setFont(new Font("Times", 40));
      this.setTextFill(color);
      setText(s);
    }
  }

  /**
   * An inner class for displaying the initial node
   */
  static class InitialNodePane extends GridPane {
    // Each cell represents a coin, which can be flipped

    ClickableCell[][] clickableCells = new ClickableCell[DIMENSION][DIMENSION];

    public InitialNodePane() {
      this.setStyle("-fx-border-color: black");
      for (int i = 0; i < DIMENSION; i++) {
        for (int j = 0; j < DIMENSION; j++) {
          add(clickableCells[i][j] = new ClickableCell("H"), j, i);
        }
      }
    }

    /**
     * Get an array of characters for a node from a GUI node
     */
    public char[] getNode() {
      char[] node = new char[DIMENSION * DIMENSION];
      int k = 0;
      for (int i = 0; i < DIMENSION; i++) {
        for (int j = 0; j < DIMENSION; j++) {
          node[k] = clickableCells[i][j].getText().charAt(0);
          k++;
        }
      }

      return node;
    }
  }

  /**
   * An inner class for displaying a clickable cell
   */
  static class ClickableCell extends Cell {

    public ClickableCell(String s) {
      super(s);
      this.setOnMouseClicked(e -> {
        if (getText().equals("H")) {
          setText("T"); // Flip from H to T
        } else {
          setText("H"); // Flip from T to H
        }
      });
    }
  }

  static class TailModel {

    public final static int DIMENSION = 4;
    // 1 << 9 is 512; 1 << 16 is 65536;
    public final static int NUMBER_OF_NODES = 1 << DIMENSION * DIMENSION;
    protected UnweightedGraph<Integer>.SearchTree tree; // Define a tree

    /**
     * Construct a model
     */
    public TailModel() {
      // Create edges
      List<Edge> edges = getEdges();

      // Create a graph
      UnweightedGraph<Integer> graph = new UnweightedGraph<>(
              edges, NUMBER_OF_NODES);

      // Obtain a BSF tree rooted at the target node
      tree = graph.bfs(NUMBER_OF_NODES - 1);
    }

    /**
     * Create all edges for the graph
     */
    private List<Edge> getEdges() {
      List<Edge> edges
              = new ArrayList<Edge>(); // Store edges

      for (int u = 0; u < NUMBER_OF_NODES; u++) {
        for (int k = 0; k < DIMENSION * DIMENSION; k++) {
          char[] node = getNode(u); // Get the node for vertex u
          if (node[k] == 'H') {
            int v = getFlippedNode(node, k);
            // Add edge (v, u) for a legal move from node u to node v
            edges.add(new Edge(v, u));
          }
        }
      }

      return edges;
    }

    public static int getFlippedNode(char[] node, int position) {
      int row = position / DIMENSION;
      int column = position % DIMENSION;

      flipACell(node, row, column);
      flipACell(node, row - 1, column);
      flipACell(node, row + 1, column);
      flipACell(node, row, column - 1);
      flipACell(node, row, column + 1);

      return getIndex(node);
    }

    public static void flipACell(char[] node, int row, int column) {
      if (row >= 0 && row < DIMENSION && column >= 0 && column < DIMENSION) {
        // Within the boundary
        if (node[row * DIMENSION + column] == 'H') {
          node[row * DIMENSION + column] = 'T'; // Flip from H to T
        } else {
          node[row * DIMENSION + column] = 'H'; // Flip from T to H
        }
      }
    }

    public static int getIndex(char[] node) {
      int result = 0;

      for (int i = 0; i < DIMENSION * DIMENSION; i++) {
        if (node[i] == 'T') {
          result = result * 2 + 1;
        } else {
          result = result * 2 + 0;
        }
      }

      return result;
    }

    public static char[] getNode(int index) {
      char[] result = new char[DIMENSION * DIMENSION];

      for (int i = 0; i < DIMENSION * DIMENSION; i++) {
        int digit = index % 2;
        if (digit == 0) {
          result[DIMENSION * DIMENSION - 1 - i] = 'H';
        } else {
          result[DIMENSION * DIMENSION - 1 - i] = 'T';
        }
        index = index / 2;
      }

      return result;
    }

    public List<Integer> getShortestPath(int nodeIndex) {
      List<Integer> path = tree.getPath(nodeIndex);

      if (path.size() == 1 && path.get(0) != DIMENSION * DIMENSION - 1) {
        return null;
      } else {
        return path;
      }
    }

    public static void printNode(char[] node) {
      for (int i = 0; i < DIMENSION * DIMENSION; i++) {
        if (i % DIMENSION != DIMENSION - 1) {
          System.out.print(node[i]);
        } else {
          System.out.println(node[i]);
        }
      }

      System.out.println();
    }
  }
}
