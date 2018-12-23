import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_10Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    HBox hBox = new HBox(10);
    hBox.setAlignment(Pos.CENTER);
    NumberPane[] numberPane = new NumberPane[5];
    
    for (int i = 0; i < 5; i++) {
      hBox.getChildren().addAll(numberPane[i] = new NumberPane(i));
    }

    HBox hBox1 = new HBox(5);
    hBox1.setAlignment(Pos.CENTER);
    Button btGuessBirthday = new Button("Guess Birthday");
    TextField tfBirthday = new TextField();
    tfBirthday.setPrefColumnCount(3);
    tfBirthday.setEditable(false);
    hBox1.getChildren().addAll(btGuessBirthday, tfBirthday);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(hBox);
    pane.setBottom(hBox1);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 500, 150);
    primaryStage.setTitle("Exercise16_10"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btGuessBirthday.setOnAction(e -> {
      int result = 0;
      for (int i = 0; i < 5; i++) {
        if (numberPane[i].isChecked()) {
          result += numberPane[i].getFirstNumber();
        }
      }
      tfBirthday.setText(result + "");
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
  
  int[][][] set = 
    {{{1, 3, 5, 7}, {9, 11, 13, 15}, {17, 19, 21, 23}, {25, 27, 29, 31}},
     {{2, 3, 6, 7}, {10, 11, 14, 15}, {18, 19, 22, 23}, {26, 27, 30, 31}},
     {{4, 5, 6, 7}, {12, 13, 14, 15}, {20, 21, 22, 23}, {28, 29, 30, 31}},
     {{8, 9, 10, 11}, {12, 13, 14, 15}, {24, 25, 26, 27}, {28, 29, 30, 31}},
     {{16, 17, 18, 19}, {20, 21, 22, 23}, {24, 25, 26, 27}, {28, 29, 30, 31}}};  
  
  class NumberPane extends VBox {
    private CheckBox chk = new CheckBox();
    private int setNumber;
    
    NumberPane(int setNumber) {
      this.setNumber = setNumber;
      
      GridPane pane = new GridPane();
      for (int i = 0; i < set[setNumber].length; i++) {
        for (int j = 0; j < set[setNumber][i].length; j++) {
          pane.add(new Label(set[setNumber][i][j] + " "), j, i);
        }
      }
      
      this.setSpacing(5);
      this.setStyle("-fx-border-width: 2; -fx-border-color: black");
      getChildren().addAll(pane, chk);
    }
    
    public int getFirstNumber() {
      return set[setNumber][0][0];
    }
    
    public boolean isChecked() {
      return chk.isSelected();
    }
  }
}
