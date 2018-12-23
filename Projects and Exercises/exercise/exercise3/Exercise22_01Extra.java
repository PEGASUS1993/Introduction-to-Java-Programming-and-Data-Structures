import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Exercise22_01Extra extends Application {
  public static final int SIZE = 8; // The size of the chess board
  // queens are placed at (i, queens[i])
  // -1 indicates that no queen is currently placed in the ith row
  // Initially, place a queen at (0, 0) in the 0th row
  private int[] queens = {-1, -1, -1, -1, -1, -1, -1, -1}; 
  private Label lblStatus = new Label();
  // Display chess board
  ChessBoard chessBoard = new ChessBoard();
  int k = 0;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
    Label lblMessage = new Label("Click the mouse to see the next move");
    pane.setBottom(lblMessage);
    BorderPane.setAlignment(lblMessage, Pos.CENTER);
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    pane.setCenter(chessBoard);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 50 * SIZE, 55 * SIZE);
    primaryStage.setTitle("Exercise22_01"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    chessBoard.setOnMouseClicked(e -> {
      search();   
      if (k == -1) {
        lblStatus.setText("No solution found");
        chessBoard.setOnMouseClicked(null);
      }
      else if (k == 8) {
        lblStatus.setText("A solution found");
        chessBoard.setOnMouseClicked(null);
      }
      
      chessBoard.repaint();
    });
    
    chessBoard.repaint();
  }
 
  /** Search for a solution */
  private boolean search() {
    // k - 1 indicates the number of queens placed so far
    // We are looking for a position in the kth row to place a queen
    if (k >= 0 && k < SIZE) {
      // Find a position to place a queen in the kth row
      int j = findPosition(k);
      if (j < 0) {
        queens[k] = -1;
        k--; // back track to the previous row
      } else {
        queens[k] = j;
        k++;
      }
    }
    
    if (k == -1)
      return false; // No solution
    else
      return true; // A solution is found
  }

  public int findPosition(int k) {
    int start = queens[k] + 1; // Search for a new placement

    for (int j = start; j < SIZE; j++) {
      if (isValid(k, j))
        return j; // (k, j) is the place to put the queen now
    }

    return -1;
  }
  
  /** Return true if a queen can be placed at (row, column) */
  public boolean isValid(int row, int column) {
    for (int i = 1; i <= row; i++)
      if (queens[row - i] == column // Check column
        || queens[row - i] == column - i // Check upleft diagonal
        || queens[row - i] == column + i) // Check upright diagonal
        return false; // There is a conflict
    return true; // No conflict
  }

  class ChessBoard extends Pane {
    private Image queenImage = new Image("image/queen.jpg");     
      
    ChessBoard() {
      setStyle("-fx-border-color: black");
    }
    
    double w = 400;
    double h = 400;
    
    public void repaint() {
      getChildren().clear();

      // Highlight the current row 
      Rectangle rectangle = new Rectangle(0, k * h / SIZE, 
          w, h / SIZE);
      rectangle.setFill(Color.YELLOW);
      getChildren().add(rectangle);
      
      // Paint the queens
      for (int i = 0; i < SIZE; i++) {
        int j = queens[i]; // The position of the queen in row i
        System.out.println(j);
        System.out.println("getWidth " + w);
        ImageView imageView = new ImageView(queenImage);
        imageView.setX(j * w / SIZE);
        imageView.setY(i * h / SIZE);
        imageView.setFitWidth(w / SIZE); 
        imageView.setFitHeight(h / SIZE);
        getChildren().add(imageView);
      }

      // Draw the horizontal and vertical lines
      for (int i = 1; i < SIZE; i++) {
        getChildren().addAll(new Line(0, i * h / SIZE, 
          w, i * h / SIZE), new Line(i * w / SIZE, 0, 
          i * w / SIZE, h));
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
}
