import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise16_01 extends Application {
  private double paneWidth = 500;
  private double paneHeight = 150;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Text text = new Text(20, 40, "Programming is fun");
    text.setFont(new Font("Times", 20));
    Pane paneForText = new Pane();
    paneForText.getChildren().add(text);
    paneForText.setStyle("-fx-border-color: gray");
    
    RadioButton rbRed = new RadioButton("Red");
    RadioButton rbYellow = new RadioButton("Yellow");
    RadioButton rbBlack = new RadioButton("Black");
    RadioButton rbOrange = new RadioButton("Orange");
    RadioButton rbGreen = new RadioButton("Green");
    
    ToggleGroup group = new ToggleGroup();
    rbRed.setToggleGroup(group);
    rbYellow.setToggleGroup(group);
    rbBlack.setToggleGroup(group);
    rbBlack.setSelected(true);
    rbOrange.setToggleGroup(group);
    rbGreen.setToggleGroup(group);

    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(rbRed, rbYellow, rbBlack, rbOrange, rbGreen);
    hBox.setAlignment(Pos.CENTER);
  
    Button btLeft = new Button("<=");
    Button btRight = new Button("=>");
    HBox hBoxForButtons = new HBox(5);
    hBoxForButtons.getChildren().addAll(btLeft, btRight);
    hBoxForButtons.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setTop(hBox);
    borderPane.setCenter(paneForText);
    borderPane.setBottom(hBoxForButtons);

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, paneWidth, paneHeight + 40);
    primaryStage.setTitle("Exercise16_01"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    rbRed.setOnAction(e -> text.setStroke(Color.RED));
    rbYellow.setOnAction(e -> text.setStroke(Color.YELLOW));
    rbBlack.setOnAction(e -> text.setStroke(Color.BLACK));
    rbOrange.setOnAction(e -> text.setStroke(Color.ORANGE));
    rbGreen.setOnAction(e -> text.setStroke(Color.GREEN));
            
    btLeft.setOnAction(e -> text.setX(text.getX() - 1));
    btRight.setOnAction(e -> text.setX(text.getX() + 1));
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
