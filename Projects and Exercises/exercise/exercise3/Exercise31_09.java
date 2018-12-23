import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class Exercise31_09 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {    
    Pane pane = new StackPane();
    Ellipse ellipse = new Ellipse(0, 0, 60, 40);
    ellipse.setFill(null);
    ellipse.setStroke(Color.BLACK);
    pane.getChildren().add(ellipse);
    
    TextField tfX = new TextField();
    TextField tfY = new TextField();
    tfX.setPrefColumnCount(2);
    tfY.setPrefColumnCount(2);
    
    Button btScale = new Button("Scale");
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(new Label("Scale factor for x:"), tfX, 
      new Label("y:"), tfY, btScale);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);   
    BorderPane.setAlignment(hBox, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 300, 150);
    primaryStage.setTitle("Exercise31_09"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btScale.setOnAction(e -> {
      ellipse.setScaleX(Double.parseDouble(tfX.getText()));
      ellipse.setScaleY(Double.parseDouble(tfY.getText()));
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
