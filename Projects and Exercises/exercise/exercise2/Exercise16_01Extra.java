import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class Exercise16_01Extra extends Application {
  final static double PANEL_WIDTH = 400;
  final static double PANEL_HEIGHT = 140;
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    GridPane paneForOriginal = new GridPane();
    GridPane paneForInversed = new GridPane();
    
    TextField[][] tfOriginal = new TextField[3][3];
    TextField[][] tfInversed = new TextField[3][3];
    
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        paneForOriginal.add(tfOriginal[i][j] = new TextField(), j, i);
        tfOriginal[i][j].setPrefColumnCount(4);
        paneForInversed.add(tfInversed[i][j] = new TextField(), j, i);
        tfInversed[i][j].setPrefColumnCount(4);
      }
    }
    
    BorderPane p1 = new BorderPane();
    p1.setCenter(paneForOriginal);
    p1.setTop(new Label("Original Matrix"));
    
    BorderPane p2 = new BorderPane();
    p2.setCenter(paneForInversed);
    final Label lblStatus = new Label("Inversed Matrix");
    p2.setTop(lblStatus);

    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(p1, p2);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(hBox);
    Button btGetInverse = new Button("Get Inverse");
    pane.setBottom(btGetInverse);
    BorderPane.setAlignment(btGetInverse, Pos.CENTER);
    
    Scene scene = new Scene(pane, PANEL_WIDTH, PANEL_HEIGHT);
    primaryStage.setTitle("Exercise16_01"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btGetInverse.setOnAction(e -> {
      double[][] A = new double[3][3];
      for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++) {
          A[i][j] = Double.parseDouble(tfOriginal[i][j].getText());
        }
      
      double[][] inverseA = Exercise08_03Extra.inverse(A);
      if (inverseA == null)
        lblStatus.setText("Invered matrix: No inverse matrix");
      else {
        for (int i = 0; i < 3; i++)
          for (int j = 0; j < 3; j++) {
            tfInversed[i][j].setText(inverseA[i][j] + "");
          }        
      }
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
