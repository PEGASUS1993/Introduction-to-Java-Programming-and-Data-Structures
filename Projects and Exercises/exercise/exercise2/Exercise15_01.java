import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Exercise15_01 extends Application {
  @Override
  // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // There are two ways for shuffling. One is to use the hint in the book. 
    // The other way is to use the static shuffle method in the java.util.Collections class.
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 1; i <= 52; i++) {
      list.add(i);
    }
    java.util.Collections.shuffle(list);
    
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().add(new ImageView("image/card/" + list.get(0) + ".png"));
    hBox.getChildren().add(new ImageView("image/card/" + list.get(1) + ".png"));
    hBox.getChildren().add(new ImageView("image/card/" + list.get(2) + ".png"));
    hBox.getChildren().add(new ImageView("image/card/" + list.get(3) + ".png"));
    
    // Create a button
    Button btRefresh = new Button("Refresh");
    btRefresh.setOnAction(e -> {
      java.util.Collections.shuffle(list);
      hBox.getChildren().clear();
      hBox.getChildren().add(new ImageView("image/card/" + list.get(0) + ".png"));
      hBox.getChildren().add(new ImageView("image/card/" + list.get(1) + ".png"));
      hBox.getChildren().add(new ImageView("image/card/" + list.get(2) + ".png"));
      hBox.getChildren().add(new ImageView("image/card/" + list.get(3) + ".png"));
    });
   
    BorderPane pane = new BorderPane();
    pane.setCenter(hBox);
    pane.setBottom(btRefresh);
    BorderPane.setAlignment(btRefresh, Pos.TOP_CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 250, 150);
    primaryStage.setTitle("Exercise15_01"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 
