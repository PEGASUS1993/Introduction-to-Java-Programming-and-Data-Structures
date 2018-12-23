
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise16_08 extends Application {

  private double paneWidth = 250;
  private double paneHeight = 250;

  private Label status = new Label("Two circles intersect? No");
  private Circle circle1 = new Circle(50, 50, 50);
  private Circle circle2 = new Circle(160, 50, 50);

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("Enter circle 1 info:"));
    GridPane pane11 = new GridPane();
    pane11.setHgap(5);
    pane11.add(new Label("Center x:"), 0, 0);
    pane11.add(new Label("Center y:"), 0, 1);
    pane11.add(new Label("Radius:"), 0, 2);

    TextField tfCenterX1 = new TextField("50");
    TextField tfCenterY1 = new TextField("50");
    TextField tfRadius1 = new TextField("50");
    pane11.add(tfCenterX1, 1, 0);
    pane11.add(tfCenterY1, 1, 1);
    pane11.add(tfRadius1, 1, 2);
    tfCenterX1.setPrefColumnCount(3);
    tfCenterY1.setPrefColumnCount(3);
    tfRadius1.setPrefColumnCount(3);
    tfCenterX1.setAlignment(Pos.BOTTOM_RIGHT);
    tfCenterY1.setAlignment(Pos.BOTTOM_RIGHT);
    tfRadius1.setAlignment(Pos.BOTTOM_RIGHT);

    pane1.setStyle("-fx-border-color: black");
    pane1.setCenter(pane11);

    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("Enter circle 2 info:"));
    GridPane pane21 = new GridPane();
    pane21.setHgap(5);
    pane21.add(new Label("Center x:"), 0, 0);
    pane21.add(new Label("Center y:"), 0, 1);
    pane21.add(new Label("Radius:"), 0, 2);

    TextField tfCenterX2 = new TextField("160");
    TextField tfCenterY2 = new TextField("50");
    TextField tfRadius2 = new TextField("50");
    tfCenterX2.setPrefColumnCount(3);
    tfCenterY2.setPrefColumnCount(3);
    tfRadius2.setPrefColumnCount(3);
    tfCenterX2.setAlignment(Pos.BOTTOM_RIGHT);
    tfCenterY2.setAlignment(Pos.BOTTOM_RIGHT);
    tfRadius2.setAlignment(Pos.BOTTOM_RIGHT);

    pane21.add(tfCenterX2, 1, 0);
    pane21.add(tfCenterY2, 1, 1);
    pane21.add(tfRadius2, 1, 2);

    pane2.setStyle("-fx-border-color: black");
    pane2.setCenter(pane21);

    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(pane1, pane2);

    BorderPane pane = new BorderPane();
    pane.setTop(status);
    BorderPane.setAlignment(status, Pos.CENTER);
    Pane paneForCircles = new Pane();
    circle1.setFill(new Color(1, 1, 1, 0));
    circle1.setStroke(Color.BLACK);
    circle2.setFill(new Color(1, 1, 1, 0));
    circle2.setStroke(Color.BLACK);

    paneForCircles.getChildren().addAll(circle1, circle2);
    pane.setCenter(paneForCircles);
    pane.setBottom(hBox);

    BorderPane bigPane = new BorderPane();
    bigPane.setCenter(pane);
    Button btRedraw = new Button("Redraw Circles");
    bigPane.setBottom(btRedraw);
    BorderPane.setAlignment(btRedraw, Pos.CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(bigPane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_08"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btRedraw.setOnAction(e -> {
      circle1.setCenterX(Double.parseDouble(tfCenterX1.getText()));
      circle1.setCenterY(Double.parseDouble(tfCenterY1.getText()));
      circle1.setRadius(Double.parseDouble(tfRadius1.getText()));
      circle2.setCenterX(Double.parseDouble(tfCenterX2.getText()));
      circle2.setCenterY(Double.parseDouble(tfCenterY2.getText()));
      circle2.setRadius(Double.parseDouble(tfRadius2.getText()));

      updateStatus();
    });

    circle1.setOnMouseDragged(e -> {
      if (circle1.contains(e.getX(), e.getY())) {
        circle1.setCenterX(e.getX());
        circle1.setCenterY(e.getY());
        tfCenterX1.setText(e.getX() + "");
        tfCenterY1.setText(e.getY() + "");
        updateStatus();
      } 
    });
            
    circle2.setOnMouseDragged(e -> {
      if (circle2.contains(e.getX(), e.getY())) {
        circle2.setCenterX(e.getX());
        circle2.setCenterY(e.getY());
        tfCenterX2.setText(e.getX() + "");
        tfCenterY2.setText(e.getY() + "");
        updateStatus();
      }
    });
  }

  private void updateStatus() {
    double d = new Point2D(circle1.getCenterX(),
            circle1.getCenterY()).distance(circle2.getCenterX(),
            circle2.getCenterY());

    if (d <= circle1.getRadius() + circle2.getRadius()) {
      status.setText("Two circles intersect? Yes");
    } else {
      status.setText("Two circles intersect? No");
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
