import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise16_02 extends Application {
  private double paneWidth = 500;
  private double paneHeight = 150;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    StackPane pane = new StackPane();
    Circle circle = new Circle(20, 20, 50);
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.WHITE);
    Rectangle rectangle = new Rectangle(20, 20, 80, 50);
    rectangle.setStroke(Color.BLACK);
    Ellipse ellipse = new Ellipse(20, 20, 50, 70);
    ellipse.setStroke(Color.BLACK);
    pane.setStyle("-fx-border-color: gray");
    pane.getChildren().add(circle);
    
    RadioButton rbCircle = new RadioButton("Circle");
    RadioButton rbRectangle = new RadioButton("Rectangle");
    RadioButton rbEllipse = new RadioButton("Ellipse");
    CheckBox chkFill = new CheckBox("Fill");
    
    ToggleGroup group = new ToggleGroup();
    rbCircle.setToggleGroup(group);
    rbCircle.setSelected(true);
    rbRectangle.setToggleGroup(group);
    rbEllipse.setToggleGroup(group);

    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(rbCircle, rbRectangle, rbEllipse, chkFill);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, paneWidth, paneHeight + 40);
    primaryStage.setTitle("Exercise16_02"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    rbCircle.setOnAction(e -> {
      pane.getChildren().clear();
      pane.getChildren().add(circle);
      if (chkFill.isSelected()) {
        circle.setFill(Color.BLACK);
      }
      else {
        circle.setFill(Color.WHITE); 
      }
    });

    rbRectangle.setOnAction(e -> {
      pane.getChildren().clear();
      pane.getChildren().add(rectangle);
      if (chkFill.isSelected()) {
        rectangle.setFill(Color.BLACK);
      }
      else {
        rectangle.setFill(Color.WHITE); 
      }
    });
    
    rbEllipse.setOnAction(e -> {
      pane.getChildren().clear();
      pane.getChildren().add(ellipse);
      if (chkFill.isSelected()) {
        ellipse.setFill(Color.BLACK);
      }
      else {
        ellipse.setFill(Color.WHITE); 
      }
    });
    
    chkFill.setOnAction(e -> {
      if (chkFill.isSelected()) {
        circle.setFill(Color.BLACK);
        rectangle.setFill(Color.BLACK);
        ellipse.setFill(Color.BLACK);
      }
      else {
        circle.setFill(Color.WHITE); 
        rectangle.setFill(Color.WHITE); 
        ellipse.setFill(Color.WHITE); 
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
