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

public class Exercise22_21 extends Application {  
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
        cells[i][j].setPrefWidth(400); 
        cells[i][j].setPrefHeight(400);
        cells[i][j].setAlignment(Pos.CENTER);
      }
    }
    
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btSolve, btClear);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(p1);
    pane.setBottom(hBox);
    pane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 240, 280);
    primaryStage.setTitle("Exercise22_21"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btSolve.setOnAction(e -> {lblStatus.setText(""); solve();});
    btClear.setOnAction(e -> {lblStatus.setText("");
      for (int i = 0; i < 9; i++)
        for (int j = 0; j < 9; j++) {
          cells[i][j].setText("");
          cells[i][j].setStyle("-fx-text-fill: black");
        }
    });
  }

  private void solve() {
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
    else if (Sudoku.search(grid)) {
      lblStatus.setText("A solution found");
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          cells[i][j].setText(grid[i][j] + "");
        }
      }
    }
    else {
      lblStatus.setText("No solution");
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
