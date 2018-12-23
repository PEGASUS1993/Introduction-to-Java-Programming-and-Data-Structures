import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_15Extra extends Application {
  private Cell[][] cells = new Cell[6][7];

  private Timeline flashAnimation = new Timeline(
          new KeyFrame(Duration.millis(400), e -> {
            setFourCellColor();
          }));

  private Label lblStatus = new Label();
      
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    flashAnimation.setCycleCount(Timeline.INDEFINITE);

    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setHgap(5);
    gridPane.setVgap(5);
    gridPane.setStyle("-fx-background-color: green");

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        cells[i][j] = new Cell(i, j);
        gridPane.add(cells[i][j], j, i);
      }
    }

    BorderPane pane = new BorderPane();
    pane.setBottom(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    pane.setCenter(gridPane);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("Exercise16_15"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  private void startOver() {
    nextDisc = 'R';
    result = null;
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        cells[i][j].token = ' ';
      }
    }
  }

  private char nextDisc = 'R';
  private int[][] result;
  private Button btStartOver = new Button("Start Over");

  class Cell extends Circle {

    char token = ' ';
    int i, j;
    boolean isFlashing;

    private boolean available() {
      return (token == ' ' && (i == 5 || cells[i + 1][j].token != ' '));
    }

    public Cell(int i, int j) {
      super(10, 10, 20);
      this.setFill(Color.WHITE);
      this.setStroke(Color.BLACK);
      this.i = i;
      this.j = j;
      this.setOnMousePressed(e -> {
        if (result != null) {
          return; // Game is over
        }
        if (available()) {
          token = nextDisc;

          if (token == 'Y') {
            this.setFill(Color.YELLOW);
          } else if (token == 'R') {
            this.setFill(Color.RED);
          }

          result = isConsecutiveFour(cells);
          if (result != null) {
            flashAnimation.play();
          } else if (nextDisc == 'R') {
            nextDisc = 'Y';
          } else {
            nextDisc = 'R';
          }

          // Check if there is a draw?
          if (isDraw()) {
              lblStatus.setText("A draw, no winner, start over");
              startOver();
          }
        }
      });
    }
  }

  private boolean isDraw() {
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        if (cells[i][j].token == ' ') {
          return false;
        }
      }
    }

    return true;
  }

  public static int[][] isConsecutiveFour(Cell[][] cells) {
    char[][] values = new char[cells.length][cells[0].length];
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        values[i][j] = cells[i][j].token;
      }
    }
    return isConsecutiveFour(values);
  }

  /**
   * Return null if no consecutive four; otherwise, return the locations of the
   * four consecutive cells. The locations are in (result[0][0], result[0][1]),
   * (result[1][0], result[1][1]), (result[2][0], result[2][1]), and
   * (result[3][0], result[3][1])
   */
  public static int[][] isConsecutiveFour(char[][] values) {
    int numberOfRows = values.length;
    int numberOfColumns = values[0].length;

    // Check rows
    for (int i = 0; i < numberOfRows; i++) {
      if (isConsecutiveFour(values[i]) != null) {
        int[][] result = new int[4][2];
        result[0][0] = result[1][0] = result[2][0] = result[3][0] = i;
        int k = isConsecutiveFour(values[i]);

        result[0][1] = k;
        result[1][1] = k + 1;
        result[2][1] = k + 2;
        result[3][1] = k + 3;

        return result;
      }
    }

    // Check columns
    for (int j = 0; j < numberOfColumns; j++) {
      char[] column = new char[numberOfRows];
      // Get a column into an array
      for (int i = 0; i < numberOfRows; i++) {
        column[i] = values[i][j];
      }

      if (isConsecutiveFour(column) != null) {
        int[][] result = new int[4][2];
        result[0][1] = result[1][1] = result[2][1] = result[3][1] = j;
        int k = isConsecutiveFour(column);

        result[0][0] = k;
        result[1][0] = k + 1;
        result[2][0] = k + 2;
        result[3][0] = k + 3;

        return result;
      }
    }

    // Check major diagonal (lower part)   
    for (int i = 0; i < numberOfRows - 3; i++) {
      int numberOfElementsInDiagonal = Math.min(numberOfRows - i, numberOfColumns);
      char[] diagonal = new char[numberOfElementsInDiagonal];
      for (int k = 0; k < numberOfElementsInDiagonal; k++) {
        diagonal[k] = values[k + i][k];
      }

      if (isConsecutiveFour(diagonal) != null) {
        int[][] result = new int[4][2];
        int k = isConsecutiveFour(diagonal);
        result[0][0] = k + i;
        result[1][0] = k + 1 + i;
        result[2][0] = k + 2 + i;
        result[3][0] = k + 3 + i;
        result[0][1] = k;
        result[1][1] = k + 1;
        result[2][1] = k + 2;
        result[3][1] = k + 3;
        return result;
      }
    }

    // Check major diagonal (upper part)
    for (int j = 1; j < numberOfColumns - 3; j++) {
      int numberOfElementsInDiagonal = Math.min(numberOfColumns - j, numberOfRows);
      char[] diagonal = new char[numberOfElementsInDiagonal];
      for (int k = 0; k < numberOfElementsInDiagonal; k++) {
        diagonal[k] = values[k][k + j];
      }

      if (isConsecutiveFour(diagonal) != null) {
        int[][] result = new int[4][2];
        int k = isConsecutiveFour(diagonal);
        result[0][0] = k;
        result[1][0] = k + 1;
        result[2][0] = k + 2;
        result[3][0] = k + 3;
        result[0][1] = k + j;
        result[1][1] = k + 1 + j;
        result[2][1] = k + 2 + j;
        result[3][1] = k + 3 + j;
        return result;
      }
    }

    // Check sub-diagonal (left part)
    for (int j = 3; j < numberOfColumns; j++) {
      int numberOfElementsInDiagonal = Math.min(j + 1, numberOfRows);
      char[] diagonal = new char[numberOfElementsInDiagonal];

      for (int k = 0; k < numberOfElementsInDiagonal; k++) {
        diagonal[k] = values[k][j - k];
      }

      if (isConsecutiveFour(diagonal) != null) {
        int[][] result = new int[4][2];
        int k = isConsecutiveFour(diagonal);
        result[0][0] = k;
        result[1][0] = k + 1;
        result[2][0] = k + 2;
        result[3][0] = k + 3;
        result[0][1] = j - k;
        result[1][1] = j - k - 1;
        result[2][1] = j - k - 2;
        result[3][1] = j - k - 3;
        return result;
      }
    }

    // Check sub-diagonal (right part)
    for (int i = 1; i < numberOfRows - 3; i++) {
      int numberOfElementsInDiagonal = Math.min(numberOfRows - i, numberOfColumns);
      char[] diagonal = new char[numberOfElementsInDiagonal];

      for (int k = 0; k < numberOfElementsInDiagonal; k++) {
        diagonal[k] = values[k + i][numberOfColumns - k - 1];
      }

      if (isConsecutiveFour(diagonal) != null) {
        int[][] result = new int[4][2];
        int k = isConsecutiveFour(diagonal);
        result[0][0] = k + i;
        result[1][0] = k + i + 1;
        result[2][0] = k + i + 2;
        result[3][0] = k + i + 3;
        result[0][1] = numberOfColumns - k - 1;
        result[1][1] = numberOfColumns - (k + 1) - 1;
        result[2][1] = numberOfColumns - (k + 2) - 1;
        result[3][1] = numberOfColumns - (k + 3) - 1;
        return result;
      }
    }

    return null;
  }

  public static Integer isConsecutiveFour(char[] values) {
    for (int i = 0; i < values.length - 3; i++) {
      boolean isEqual = true;
      for (int j = i; j < i + 3; j++) {
        if (values[j] == ' ' || values[j] != values[j + 1]) {
          isEqual = false;
          break;
        }
      }

      if (isEqual) {
        return new Integer(i);
      }
    }

    return null;
  }

  private void setFourCellColor() {
    if (cells[result[0][0]][result[0][1]].token == ' ') {
      cells[result[0][0]][result[0][1]].token = nextDisc;
      cells[result[1][0]][result[1][1]].token = nextDisc;
      cells[result[2][0]][result[2][1]].token = nextDisc;
      cells[result[3][0]][result[3][1]].token = nextDisc;
      if (nextDisc == 'Y') {
        cells[result[0][0]][result[0][1]].setFill(Color.YELLOW);
        cells[result[1][0]][result[1][1]].setFill(Color.YELLOW);
        cells[result[2][0]][result[2][1]].setFill(Color.YELLOW);
        cells[result[3][0]][result[3][1]].setFill(Color.YELLOW);
      } else {
        cells[result[0][0]][result[0][1]].setFill(Color.RED);
        cells[result[1][0]][result[1][1]].setFill(Color.RED);
        cells[result[2][0]][result[2][1]].setFill(Color.RED);
        cells[result[3][0]][result[3][1]].setFill(Color.RED);
      }
    } else {
      cells[result[0][0]][result[0][1]].token = ' ';
      cells[result[1][0]][result[1][1]].token = ' ';
      cells[result[2][0]][result[2][1]].token = ' ';
      cells[result[3][0]][result[3][1]].token = ' ';
      cells[result[0][0]][result[0][1]].setFill(Color.WHITE);
      cells[result[1][0]][result[1][1]].setFill(Color.WHITE);
      cells[result[2][0]][result[2][1]].setFill(Color.WHITE);
      cells[result[3][0]][result[3][1]].setFill(Color.WHITE);
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
