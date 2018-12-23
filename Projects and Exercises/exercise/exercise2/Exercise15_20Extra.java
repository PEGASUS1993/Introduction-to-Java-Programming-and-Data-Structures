import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise15_20Extra extends Application {
  private Label lbl1 = new Label();
  private Label lbl2 = new Label();
  private Label lbl3 = new Label();
  private Label lbl4 = new Label();

  @Override
  // Override the start method in the Application class
  public void start(Stage primaryStage) {

    int[] list = new int[52];
    for (int i = 0; i < 52; i++) {
      list[i] = i;
    }
    shuffle(list);
    
    lbl1.setText("" + list[0]);
    lbl2.setText("" + list[1]);
    lbl3.setText("" + list[2]);
    lbl4.setText("" + list[3]);
    lbl1.setStyle("-fx-border-color: red;");
    lbl2.setStyle("-fx-border-color: red;");
    lbl3.setStyle("-fx-border-color: red;");
    lbl4.setStyle("-fx-border-color: red;");
    
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(lbl1, lbl2, lbl3, lbl4); 
    
    // Create a button
    Button btRefresh = new Button("Refresh");
    btRefresh.setOnAction(e -> {
      shuffle(list);
      lbl1.setText("" + list[0]);
      lbl2.setText("" + list[1]);
      lbl3.setText("" + list[2]);
      lbl4.setText("" + list[3]);
    });
   
    BorderPane pane = new BorderPane();
    pane.setCenter(hBox);
    pane.setBottom(btRefresh);
    BorderPane.setAlignment(btRefresh, Pos.TOP_CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 250, 150);
    primaryStage.setTitle("Test"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  private static void shuffle(int[] list) {
     for (int i = 0; i < list.length; i++) {
       // Generate an index randomly
       int index = (int)(Math.random() * list.length);
       int temp = list[i];
       list[i] = list[index]; 
       list[index] = temp;
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
