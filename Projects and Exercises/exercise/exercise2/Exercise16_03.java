import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise16_03 extends Application {
  private double paneWidth = 200;
  private double paneHeight = 90;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Pane pane = new Pane();
    Circle circleRed = new Circle(paneWidth / 2, 20, 10);
    Circle circleYellow = new Circle(paneWidth / 2, 50, 10);
    Circle circleGreen = new Circle(paneWidth / 2, 80, 10);
    circleRed.setStroke(Color.BLACK);
    circleYellow.setStroke(Color.BLACK);
    circleGreen.setStroke(Color.BLACK);
    circleRed.setFill(Color.WHITE);
    circleYellow.setFill(Color.WHITE);
    circleGreen.setFill(Color.WHITE);

    Rectangle rectangle = new Rectangle(paneWidth / 2 - 15, 5, 30, 90);
    rectangle.setFill(Color.WHITE);
    rectangle.setStroke(Color.BLACK);
    
    pane.getChildren().addAll(rectangle, circleRed, circleYellow, circleGreen);
    
    RadioButton rbRed = new RadioButton("Red");
    RadioButton rbYellow = new RadioButton("Yellow");
    RadioButton rbGreen = new RadioButton("Green");
    
    ToggleGroup group = new ToggleGroup();
    rbRed.setToggleGroup(group);
    rbYellow.setToggleGroup(group);
    rbGreen.setToggleGroup(group);

    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(rbRed, rbYellow, rbGreen);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, paneWidth, paneHeight + 40);
    primaryStage.setTitle("Exercise16_03"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    rbRed.setOnAction(e -> {
      circleRed.setFill(Color.RED);
      circleYellow.setFill(Color.WHITE);
      circleGreen.setFill(Color.WHITE);
    });

    rbYellow.setOnAction(e -> {
      circleYellow.setFill(Color.YELLOW);
      circleRed.setFill(Color.WHITE);
      circleGreen.setFill(Color.WHITE);
    });
 
    rbGreen.setOnAction(e -> {
      circleGreen.setFill(Color.GREEN);
      circleYellow.setFill(Color.WHITE);
      circleRed.setFill(Color.WHITE);
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
