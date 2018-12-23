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

public class Exercise31_08 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {    
    Pane pane = new StackPane();
    Ellipse ellipse = new Ellipse(0, 0, 60, 40);
    ellipse.setFill(null);
    ellipse.setStroke(Color.BLACK);
    pane.getChildren().add(ellipse);
    
    TextField tfAngle = new TextField();
    tfAngle.setPrefColumnCount(2);
    
    Button btRotate = new Button("Rotate");
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(new Label("Angle:"), tfAngle, btRotate);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);   
    BorderPane.setAlignment(hBox, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 300, 150);
    primaryStage.setTitle("Exercise31_08"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btRotate.setOnAction(e -> {
      ellipse.setRotate(Double.parseDouble(tfAngle.getText()));
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
