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

public class Exercise18_26 extends Application {
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
        gridPane.add(board[i][j] = new Cell(), j, i);
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
    primaryStage.setTitle("Exercise18_26"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btFindPath.setOnAction(e -> findPath());
    btClearPath.setOnAction(e -> clearPath());
  }
  
    public void findPath() {
    if (findPath(0, 0)) {
      lblStatus.setText("path found");
    }
    else {
      lblStatus.setText("No path exists");
    }
  }

  public boolean findPath(int row, int col) {
    board[row][col].visit();

    if ((col == 7) && (row == 7)) {
      board[row][col].selectCell();
      return true;
    }

    if ((row > 0) && !board[row - 1][col].marked() &&
        !board[row - 1][col].blocked() && !board[row - 1][col].visited()) {
      block(row, col);

      if (findPath(row - 1, col)) {
        board[row][col].selectCell();
        return true;
      }

      unblock(row, col);
    }

    if ((row < 7) && !board[row + 1][col].marked() &&
        !board[row + 1][col].blocked() && !board[row + 1][col].visited()) {
      block(row, col);

      if (findPath(row + 1, col)) {
        board[row][col].selectCell();
        return true;
      }

      unblock(row, col);
    }

    if ((col > 0) && !board[row][col - 1].marked() &&
        !board[row][col - 1].blocked() && !board[row][col - 1].visited()) {
      block(row,col);
      if (findPath(row, col - 1)) {
        board[row][col].selectCell();
        return true;
      }

      unblock(row,col);
    }

    if ((col < 7) && !board[row][col + 1].marked() &&
        !board[row][col + 1].blocked() && !board[row][col + 1].visited()) {
      block(row,col);
      if (findPath(row, col + 1)) {
        board[row][col].selectCell();
        return true;
      }

      unblock(row,col);
    }

    return false;
  }

  // Temporary block the neighbor to prevent neighboring path
  public void block(int row, int col) {
    if (row > 0) {
      board[row - 1][col].block();
    }

    if (row < 7) {
      board[row + 1][col].block();
    }

    if (col > 0) {
      board[row][col - 1].block();
    }

    if (col < 7) {
      board[row][col + 1].block();
    }
  }

  // Remove the temporary block
  public void unblock(int row, int col) {
    if (row > 0) {
      board[row - 1][col].unblock();
    }

    if (row < 7) {
      board[row + 1][col].unblock();
    }

    if (col > 0) {
      board[row][col - 1].unblock();
    }

    if (col < 7) {
      board[row][col + 1].unblock();
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
    private boolean marked = false;
    private boolean visited = false;
    private boolean blocked = false;
    
    double width = paneWidth / 8;
    double height = paneHeight / 8;
    private Rectangle rectangle = new Rectangle(0, 0, width, height);
    
    Line line1 = new Line(0, 0, width, height);
    Line line2 = new Line(width, 0, 0, height);        
    
    public Cell() {
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
  }
}
