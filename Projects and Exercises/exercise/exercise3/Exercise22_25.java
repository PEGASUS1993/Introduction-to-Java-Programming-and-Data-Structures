import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise22_25 extends Application {  
  public static final int SIZE = 10;
  private TextField[][] cells = new TextField[SIZE][SIZE];
  private Button btSolve = new Button("Solve");
  private Button btClear = new Button("Clear");
  private Button btNext = new Button("Next");
  private Label lblStatus = new Label();
    
  private static ArrayList<int[][]> list = new ArrayList<>();
  private int currentSolutionIndex = 0;
  
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
    
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btSolve, btClear, btNext);
    hBox.setAlignment(Pos.CENTER);
    btNext.setVisible(false);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(p1);
    pane.setBottom(hBox);
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 240, 280);
    primaryStage.setTitle("Exercise22_25"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btSolve.setOnAction(e -> solve());
    btClear.setOnAction(e -> {
      lblStatus.setText("");
      btNext.setVisible(false);
      for (int i = 0; i < 9; i++)
        for (int j = 0; j < 9; j++) {
          cells[i][j].setText("");
          cells[i][j].setStyle("-fx-text-fill: black");
        }
    });
    btNext.setOnAction(e -> {
      currentSolutionIndex = ++currentSolutionIndex % list.size() ;
      lblStatus.setText(currentSolutionIndex + 1 + "/" + list.size());
      
      int[][] solutionGrid = list.get(currentSolutionIndex);
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          cells[i][j].setText(solutionGrid[i][j] + "");
        }
      }
    });
  }

  private void solve() {
    list.clear();
    currentSolutionIndex = 0;
      
    int[][] grid = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (cells[i][j].getText().trim().length() == 0) {
          grid[i][j] = 0;
        }
        else {
          grid[i][j] = Integer.parseInt(cells[i][j].getText());
          cells[i][j].setStyle("-fx-text-fill: gray");
        }
      }
    }

    if (!Sudoku.isValid(grid)) {
      lblStatus.setText("Invalid Input");
    }
    else {
      int count = Sudoku.search(grid);

      if (count == 0) {
        lblStatus.setText("No solution");
      }
      else {
        // Display the first solution
        int[][] solutionGrid = list.get(0);
        for (int i = 0; i < 9; i++) {
          for (int j = 0; j < 9; j++) {
            cells[i][j].setText(solutionGrid[i][j] + "");
          }
        }
      }
    }

    if (list.size() > 1) {
      btNext.setVisible(true);
      lblStatus.setText("1/" + list.size() + " solutions");
    }
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }   

  public static class Sudoku {
    /** Obtain a list of free cells from the puzzle */
    public static int[][] getFreeCellList(int[][] grid) {
      // 81 is the maximum number of free cells
      int[][] tempList = new int[81][2];
      int numberOfFreeCells = 0;

      for (int i = 0; i < 9; i++)
        for (int j = 0; j < 9; j++)
          if (grid[i][j] == 0) {
            tempList[numberOfFreeCells][0] = i;
            tempList[numberOfFreeCells][1] = j;
            numberOfFreeCells++;
          }

      // Trim freeCellList
      int[][] freeCellList = new int[numberOfFreeCells][2];
      for (int i = 0; i < numberOfFreeCells; i++) {
        freeCellList[i][0] = tempList[i][0];
        freeCellList[i][1] = tempList[i][1];
      }

      return freeCellList;
    }

    /** Search for up to 3 solutions. 
     * This method returns the number of solutions found.
     * Possible return values are 0, 1, 2, and 3.  */
    
    public static int search(int[][] grid) {
      int[][] freeCellList = getFreeCellList(grid); // Free cells
      int k = 0; // Start from the first free cell
      int count = 0; // Multiple solutions: Count for 3 solutions

      boolean done = false;
      while (!done) {
        int i = freeCellList[k][0];
        int j = freeCellList[k][1];
        if (grid[i][j] == 0)
          grid[i][j] = 1; // Start with 1

        if (isValid(i, j, grid)) {
          if (k + 1 == freeCellList.length) { // No more free cells
            // A solution is found
            count++;
            
            int[][] solutionGrid = new int[9][9];
            for (int i1 = 0; i1 < 9; i1++)
              for (int j1 = 0; j1 < 9; j1++)
                solutionGrid[i1][j1] = grid[i1][j1];
            
            list.add(solutionGrid);
            
            // Now search for the next possible solution
            if (grid[i][j] < 9) {
              grid[i][j] = grid[i][j] + 1; // Check the next possible value
            } 
            else { // grid[i][j] is 9, backtrack
              while (grid[i][j] == 9) {
                grid[i][j] = 0; // Reset to free cell
                if (k == 0) {
                  done = true; // No possible value any more, done!
                  return count; 
                }
                k--; // Backtrack
                i = freeCellList[k][0];
                j = freeCellList[k][1];
              }

              grid[i][j] = grid[i][j] + 1; // Check the next possible value
            }
          } 
          else { // Move to the next free cell
            k++;
          }
        }
        else if (grid[i][j] < 9) {
          grid[i][j] = grid[i][j] + 1; // Check the next possible value
        } 
        else { // grid[i][j] is 9, backtrack
          while (grid[i][j] == 9) {
            grid[i][j] = 0; // Reset to free cell
            if (k == 0) {
              return count; // No possible value
            }
            k--; // Backtrack
            i = freeCellList[k][0];
            j = freeCellList[k][1];
          }

          grid[i][j] = grid[i][j] + 1; // Check the next possible value
        }
      }

      return count; // A solution is found
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
          if (grid[i][j] < 0 || grid[i][j] > 9 ||
             (grid[i][j] != 0 && !isValid(i, j, grid))) {
            System.out.println("grid[i][j] = " + grid[i][j]);
            System.out.println("i = " + i + " j " + j);
            return false;
          }

      return true; // The fixed cells are valid
    }
  }
}
