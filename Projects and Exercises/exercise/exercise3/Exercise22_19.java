import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise22_19 extends Application {  
  private Button btRefresh = new Button("Refresh");
  private Button btLargestBlock = new Button("Find Largest Block");
  public static final int SIZE = 170;
  private TextField[][] tfNumbers = new TextField[SIZE][SIZE];
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {     
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        gridPane.add(tfNumbers[i][j] = new TextField(), j, i);
        tfNumbers[i][j].setPrefColumnCount(1);
        // Set a large preferred width and height to occupy the entire grid pane
        tfNumbers[i][j].setPrefWidth(400); 
        tfNumbers[i][j].setPrefHeight(400);
        tfNumbers[i][j].setAlignment(Pos.CENTER);
      }
    }
    
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btRefresh, btLargestBlock);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(gridPane);
    pane.setBottom(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 850, 790);
    primaryStage.setTitle("Exercise22_19"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    refresh();
    
    btRefresh.setOnAction(e -> refresh());
    
    btLargestBlock.setOnAction(e -> {
      int[][] m = new int[SIZE][SIZE];
      for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
          m[i][j] = Integer.parseInt(tfNumbers[i][j].getText());
          tfNumbers[i][j].setStyle("-fx-background-color: white"); 
        }
      }

      int[] result = findLargestBlock(m);

      for (int i = result[0]; i < result[0] + result[2]; i++) {
        for (int j = result[1]; j < result[1] + result[2]; j++) {
          tfNumbers[i][j].setStyle("-fx-background-color: red");
        }
      }
    });
  }

  private void refresh() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        tfNumbers[i][j].setText("" + (int)(Math.random() * 2));
        tfNumbers[i][j].setStyle("-fx-background-color: white"); 
      }
    }
  }
  
  public static int[] findLargestBlock(int[][] m) {
    int max = 0;
    int maxOfx = 0, maxOfy = 0;

    int[][] count = new int[m.length][m.length];

    for (int i = m.length - 1; i >= 0; i--) {
      for (int j = m[i].length - 1; j >= 0; j--) {
        if (m[i][j] == 1) {
          if (i == m.length - 1 || j == m[i].length - 1) {
            count[i][j] = 1;
          }
          
          // We reduce the overall complexity to O(n^2) by this clever approach
          if (i < m.length - 1 && j < m[i].length - 1) {
            count[i][j] = 1 + Math
                .min(Math.min(count[i + 1][j + 1], count[i + 1][j]),
                    count[i][j + 1]);
          }
          
          if (count[i][j] > max) {
            max = count[i][j];
            maxOfx = i;
            maxOfy = j;
          }
        }
      }
    }

    int[] result = new int[3];
    result[0] = maxOfx;
    result[1] = maxOfy;
    result[2] = max;

    return result;
  }
    
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
