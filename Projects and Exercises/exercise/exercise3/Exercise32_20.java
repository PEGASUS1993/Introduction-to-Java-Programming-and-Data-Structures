import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise32_20 extends Application {
  public static final int SIZE = 9;
  private TextField[][] cells = new TextField[SIZE][SIZE];
  private Button btSolve = new Button("Solve");
  private Button btClear = new Button("Clear");
  private Label lblStatus = new Label();
    
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    GridPane[][] panels = new GridPane[3][3];
    GridPane p1 = new GridPane();
    p1.setStyle("-fx-border-color: red");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        p1.add(panels[i][j] = new GridPane(), j, i);
        panels[i][j].setStyle("-fx-border-color: red");
      }
    }
    
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        panels[i / 3][j / 3].add(cells[i][j] = new TextField(), j % 3, i % 3);
        cells[i][j].setPrefColumnCount(1);
      }
    }
    
    btSolve.setStyle("-fx-border-color: green; -fx-border-width: 4px");
    btClear.setStyle("-fx-border-color: green; -fx-border-width: 4px");
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btSolve, btClear);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(p1);
    pane.setBottom(hBox);
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 360, 580);
    primaryStage.setTitle("Exercise32_20"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btSolve.setOnAction(e -> solve());
    btClear.setOnAction(e -> {
      for (int i = 0; i < 9; i++)
        for (int j = 0; j < 9; j++) {
          cells[i][j].setText("");
          cells[i][j].setStyle("-fx-text-fill: black");
        }
    });
  }
  
  int[][] grid = new int[9][9];

  private void solve() {
    lblStatus.setText("");
    
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (cells[i][j].getText().trim().length() == 0) {
          grid[i][j] = 0;
        } else {
          grid[i][j] = Integer.parseInt(cells[i][j].getText());
          cells[i][j].setStyle("-fx-text-fill: gray");
        }
      }
    }

    Thread thread = new Thread(new RunOnSeparateThread());
    thread.start();
  }
  
  /** Obtain a list of free cells from the puzzle */
  public static int[][] getFreeCellList(int[][] grid) {
    // Determine the number of free cells
    int numberOfFreeCells = 0;
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (grid[i][j] == 0)
          numberOfFreeCells++;

    // Store free cell positions into freeCellList
    int[][] freeCellList = new int[numberOfFreeCells][2];
    int count = 0;
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (grid[i][j] == 0) {
          freeCellList[count][0] = i;
          freeCellList[count++][1] = j;
        }

    return freeCellList;
  }


  /** Search for a solution */
  public boolean search(int[][] grid) {
    int[][] freeCellList = getFreeCellList(grid); // Free cells
    int k = 0; // Start from the first free cell
    boolean found = false; // Solution found?

    while (!found) {
      int i = freeCellList[k][0];
      int j = freeCellList[k][1];
      if (grid[i][j] == 0)
        grid[i][j] = 1; // Start with 1
      
      // Redisplay the grid
      setANewValue(grid, i, j);

      if (isValid(i, j, grid)) {
        if (k + 1 == freeCellList.length) { // No more free cells
          found = true; // A solution is found
        } else { // Move to the next free cell
          k++;
        }
      } else if (grid[i][j] < 9) {
        grid[i][j] = grid[i][j] + 1; // Check the next possible value
      } else { // grid[i][j] is 9, backtrack
        while (grid[i][j] == 9) {
          grid[i][j] = 0; // Reset to free cell
          if (k == 0) {
            return false; // No possible value
          }
          k--; // Backtrack
          i = freeCellList[k][0];
          j = freeCellList[k][1];
        }

        grid[i][j] = grid[i][j] + 1; // Check the next possible value
      }
    }

    return true; // A solution is found
  }

  /** Check whether grid[i][j] is valid in the grid */
  public static boolean isValid(int i, int j, int[][] grid) {
    // Check whether grid[i][j] is valid at the i's row
    for (int column = 0; column < 9; column++)
      if (column != j && grid[i][column] == grid[i][j])
        return false;

    // Check whether grid[i][j] is valid at the j's column
    for (int row = 0; row < 9; row++)
      if (row != i && grid[row][j] == grid[i][j])
        return false;

    // Check whether grid[i][j] is valid in the 3 by 3 box
    for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
      for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
        if (row != i && col != j && grid[row][col] == grid[i][j])
          return false;

    return true; // The current value at grid[i][j] is valid
  }

  /** Check whether the fixed cells are valid in the grid */
  public static boolean isValid(int[][] grid) {
    for (int i = 0; i < 9; i++)
      for (int j = 0; j < 9; j++)
        if (grid[i][j] < 0 || grid[i][j] > 9
            || (grid[i][j] != 0 && !isValid(i, j, grid)))
          return false;

    return true; // The fixed cells are valid
  }

  /** Check whether the fixed cells are valid in the grid */
  public void setANewValue(int[][] grid, int i1, int j1) {    
    Platform.runLater(() -> {
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            cells[i][j].setStyle("-fx-text-fill: black");
        }
      }; 
    
      cells[i1][j1].setStyle("-fx-text-fill: red");
      displayGrid(grid);   
    });
    
    try {
      Thread.sleep(5000);
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }

  public void displayGrid(int[][] grid) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (grid[i][j] == 0)
          cells[i][j].setText(null);
        else
          cells[i][j].setText(grid[i][j] + "");
      }
    }
  }

  class RunOnSeparateThread implements Runnable {
    public void run() {
      if (!isValid(grid)) {
        Platform.runLater(() -> lblStatus.setText("Invalid Input"));
      } else if (search(grid)) {
        displayGrid(grid);
      } else {
        Platform.runLater(() -> lblStatus.setText("No solution"));
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
