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

public class Exercise28_11 extends Application {
  // Create the initial board
  private InitialNodePane initialNodePane = new InitialNodePane();
  private Button btSolve = new Button("Solve");
  private Button btStartOver = new Button("Start Over");

  private NineTailModel model = new NineTailModel();

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

    // Create a scene and place the pane in the stage
    Scene scene = new Scene(pane, 650, 250);
    primaryStage.setTitle("Exercise28_11: Nine Tail Problem"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    // Listener for the Solve button
    btSolve.setOnAction(e -> {
      solutionPane.getChildren().clear();

      // Get a shortest path
      java.util.List<Integer> list = model
              .getShortestPath(NineTailModel
              .getIndex(initialNodePane.getNode()));

      // Display nodes in the shortest path
      for (int i = 0; i < list.size(); i++) {
        if (i != 0) {
          solutionPane.getChildren().add(new NodePane(NineTailModel
                  .getNode(list.get(i)), NineTailModel
                  .getNode(list.get(i - 1))));
        } else {
          solutionPane.getChildren().add(new NodePane(NineTailModel
                  .getNode(list.get(i))));
        }
      }
    });

    // Listener for the Start Over button
    btStartOver.setOnAction(e -> {
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
      for (int i = 0; i < 9; i++) {
        add(new Cell(node[i] + ""), i % 3, i / 3);
      }
    }

    public NodePane(char[] newNode, char[] oldNode) {
      this.setStyle("-fx-border-color: black");
      for (int i = 0; i < 9; i++) {
        if (newNode[i] == oldNode[i]) {
          add(new Cell(newNode[i] + "", Color.BLACK), i % 3, i / 3);
        } else {
          add(new Cell(newNode[i] + "", Color.RED), i % 3, i / 3);
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

    ClickableCell[][] clickableCells = new ClickableCell[3][3];

    public InitialNodePane() {
      this.setStyle("-fx-border-color: black");
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          add(clickableCells[i][j] = new ClickableCell("H"), j, i);
        }
      }
    }

    /**
     * Get an array of characters for a node from a GUI node
     */
    public char[] getNode() {
      char[] node = new char[9];
      int k = 0;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
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
}
