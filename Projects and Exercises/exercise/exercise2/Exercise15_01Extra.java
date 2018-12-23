import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise15_01Extra extends Application {  
  private Text[][] texts = new Text[10][10];
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Create UI
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setHgap(10);
    pane.setVgap(10);
    
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        pane.add(texts[i][j] = new Text((int)(Math.random() * 2) + ""), j, i);
      }
    }
    
    Button btRefresh = new Button("Refresh");
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(btRefresh);
    BorderPane.setAlignment(btRefresh, Pos.CENTER);
    
    btRefresh.setOnAction(e -> {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
          texts[i][j].setText((int)(Math.random() * 2) + "");
        }
      }      
    });
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 200, 350);
    primaryStage.setTitle("Exercise15_01"); // Set title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    
  }
  
  class Cell extends Text {
    Cell() {
      setFont(Font.font("Times Roman", 35));
      super.setText("H");
      this.setOnMouseClicked(e -> {
        if (getText().equals("H")) {
          setText("T");
        }
        else {
          setText("H");
        }
      });
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
