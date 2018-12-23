import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.*;

public class Exercise28_02Extra extends Application {
  double paneWidth = 400;
  double paneHeight = 400;
  
  private Cell[][] board = new Cell[8][8];
  private Button btFindPath = new Button("Find Path");
  private Button btClearPath = new Button("Clear Path");
  private Label lblStatus = new Label();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {        
    GridPane gridPane = new GridPane();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        gridPane.add(board[i][j] = new Cell(i, j), j, i);
      }
    }
    
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(btFindPath, btClearPath);
    
    BorderPane pane = new BorderPane();
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    pane.setCenter(gridPane);
    pane.setBottom(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight + 60);
    primaryStage.setTitle("Exercise28_02"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
       
    btFindPath.setOnAction(e -> findPath());
    btClearPath.setOnAction(e -> clearPath());
  }
  
  public void findPath() {
    // Create vertices
    List<Cell> vertices = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        vertices.add(board[i][j]);
      }
    }
    
    // Create edges
    List<Edge> edges = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        int u = i * 8 + j;
        if (!board[i][j].marked) {
          if (i > 0 && !board[i - 1][j].marked) {
            int v = (i - 1) * 8 + j;
            edges.add(new Edge(u, v));
          }
          if (i < 7 && !board[i + 1][j].marked) {
            int v = (i + 1) * 8 + j;
            edges.add(new Edge(u, v));
          }
          if (j > 0 && !board[i][j - 1].marked) {
            int v = i * 8 + j - 1;
            edges.add(new Edge(u, v));
          }
          if (j < 7 && !board[i][j + 1].marked) {
            int v = i * 8 + j + 1;
            edges.add(new Edge(u, v));
          }
        }
      }
    }

    // Create a graph    
    UnweightedGraph<Cell> graph = new UnweightedGraph<>(vertices, edges);
    UnweightedGraph<Cell>.SearchTree tree = graph.bfs(0);
    List<Cell> path = tree.getPath(63);
    
    System.out.println(path);
    
    if (path.size() >= 15) {
      lblStatus.setText("path found");      
      for (Cell cell: path) {
        cell.selectCell();
      }
    }
    else {
      lblStatus.setText("No path exists");
    }
  }

  public void clearPath() {
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[row].length; col++) {
        board[row][col].deselectCell();
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
  
  // Inner class
  class Cell extends StackPane {
    int i, j;
    private boolean marked = false;
    private boolean visited = false;
    private boolean blocked = false;
    
    double width = paneWidth / 8;
    double height = paneHeight / 8;
    private Rectangle rectangle = new Rectangle(0, 0, width, height);
    
    Line line1 = new Line(0, 0, width, height);
    Line line2 = new Line(width, 0, 0, height);        
    
    public Cell(int i, int j) {
      this.i = i; this.j = j;
      this.getChildren().add(rectangle);
      rectangle.setFill(Color.WHITE);
      rectangle.setStroke(Color.BLACK);
      
      this.setOnMousePressed( e -> {
        marked = !marked;
        if (marked) {
          mark();
        }
        else {
          unmark();          
        }
      });
    }

    public void mark() {
      this.getChildren().addAll(line1, line2);
    }
       
    public void unmark() {
      this.getChildren().remove(line1);
      this.getChildren().remove(line2);
    }
        
    public boolean marked() {
      return marked;
    }

    public void visit() {
      visited = true;
    }

    public boolean visited() {
      return visited;
    }

    public boolean blocked() {
      return blocked;
    }

    public void block() {
      blocked = true;
    }

    public void unblock() {
      blocked = false;
    }

    public void selectCell() {
      rectangle.setFill(Color.RED);
    }

    public void deselectCell() {
      rectangle.setFill(Color.WHITE);
      blocked = false;
      visited = false;
    }
    
    public String toString() {
      return "(" + i + ", " + j + ")";
    }
  }
}
