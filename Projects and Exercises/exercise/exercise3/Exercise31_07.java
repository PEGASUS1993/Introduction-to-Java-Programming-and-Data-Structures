import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise31_07 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {    
    Pane pane = new Pane();
    Rectangle rectangle = new Rectangle(40, 40, 50, 40);
    rectangle.setFill(null);
    rectangle.setStroke(Color.BLACK);
    pane.getChildren().add(rectangle);
    
    TextField tfX = new TextField();
    TextField tfY = new TextField();
    tfX.setPrefColumnCount(2);
    tfY.setPrefColumnCount(2);
    
    Button btTranslate = new Button("Translate");
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(new Label("x:"), tfX, 
      new Label("y:"), tfY, btTranslate);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);   
    BorderPane.setAlignment(hBox, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 300, 150);
    primaryStage.setTitle("Exercise31_07"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btTranslate.setOnAction(e -> {
      rectangle.setTranslateX(Double.parseDouble(tfX.getText()));
      rectangle.setTranslateY(Double.parseDouble(tfY.getText()));
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
