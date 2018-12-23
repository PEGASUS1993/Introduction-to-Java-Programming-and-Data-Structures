import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class Exercise16_02Extra extends Application {
  final static double PANEL_WIDTH = 400;
  final static double PANEL_HEIGHT = 140;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    GridPane paneForOriginal = new GridPane();
    GridPane paneForInversed = new GridPane();
    
    TextField[][] tfA = new TextField[3][3];
    TextField[] tfB = new TextField[3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        tfA[i][j] = new TextField();
        tfA[i][j].setPrefColumnCount(4);
      }
      tfB[i] = new TextField();
      tfB[i].setPrefColumnCount(4);
    }
    
    HBox[] hBoxes = new HBox[3];
    for (int i = 0; i < 3; i++) {
      hBoxes[i] = new HBox(5);
      hBoxes[i].getChildren().addAll(tfA[i][0], new Label("x"), tfA[i][1], new Label("y"), tfA[i][2], new Label("z = "), tfB[i]);
    }
    
    VBox vBox = new VBox(5);
    vBox.getChildren().addAll(hBoxes[0], hBoxes[1], hBoxes[2]);
    
    HBox hBox = new HBox(5);
    Label lblStatus = new Label();
    Button btSolveEquation = new Button("Solve Equation");
    hBox.getChildren().addAll(btSolveEquation, lblStatus);
        
    BorderPane pane = new BorderPane();
    pane.setCenter(vBox);
    pane.setBottom(hBox);
    
    Scene scene = new Scene(pane, PANEL_WIDTH, PANEL_HEIGHT);
    primaryStage.setTitle("Exercise16_02"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btSolveEquation.setOnAction(e -> {
      double[][] A = new double[3][3];
      double[] B = new double[3];
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          A[i][j] = Double.parseDouble(tfA[i][j].getText());
        }
        B[i] = Double.parseDouble(tfB[i].getText());
      }
      
      double[] result = Exercise08_02Extra.getSolution(A, B);
      if (result == null)
        lblStatus.setText("No solutions");
      else 
        lblStatus.setText("Solution is x = " + result[0] + ", y is = " + result[1] + ", and z is = " + result[2]);
    });
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
