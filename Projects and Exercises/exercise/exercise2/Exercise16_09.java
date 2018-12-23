import javafx.application.Application;
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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise16_09 extends Application {
  private double paneWidth = 250;
  private double paneHeight = 250;

  private Label status = new Label("Two Rectangles intersect? No");
  private Rectangle rectangle1 = new Rectangle(50, 50, 40, 50);
  private Rectangle rectangle2 = new Rectangle(160, 50, 50, 20);

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane1 = new BorderPane();
    pane1.setTop(new Label("Enter rectangle 1 info:"));
    GridPane pane11 = new GridPane();
    pane11.setHgap(5);
    pane11.add(new Label("X:"), 0, 0);
    pane11.add(new Label("Y:"), 0, 1);
    pane11.add(new Label("Width:"), 0, 2);
    pane11.add(new Label("Height:"), 0, 3);

    TextField tfX1 = new TextField(rectangle1.getX() + "");
    TextField tfY1 = new TextField(rectangle1.getY() + "");
    TextField tfWidth1 = new TextField(rectangle1.getWidth() + "");
    TextField tfHeight1 = new TextField(rectangle1.getHeight() + "");
    pane11.add(tfX1, 1, 0);
    pane11.add(tfY1, 1, 1);
    pane11.add(tfWidth1, 1, 2);
    pane11.add(tfHeight1, 1, 3);
    
    tfX1.setPrefColumnCount(3);
    tfY1.setPrefColumnCount(3);
    tfWidth1.setPrefColumnCount(3);
    tfHeight1.setPrefColumnCount(3);
    tfX1.setAlignment(Pos.BOTTOM_RIGHT);
    tfY1.setAlignment(Pos.BOTTOM_RIGHT);
    tfWidth1.setAlignment(Pos.BOTTOM_RIGHT);
    tfHeight1.setAlignment(Pos.BOTTOM_RIGHT);

    pane1.setStyle("-fx-border-color: black");
    pane1.setCenter(pane11);

    BorderPane pane2 = new BorderPane();
    pane2.setTop(new Label("Enter rectangle 2 info:"));
    GridPane pane21 = new GridPane();
    pane21.setHgap(5);
    pane21.add(new Label("X:"), 0, 0);
    pane21.add(new Label("Y:"), 0, 1);
    pane21.add(new Label("Width:"), 0, 2);
    pane21.add(new Label("Height:"), 0, 3);

    TextField tfX2 = new TextField(rectangle2.getX() + "");
    TextField tfY2 = new TextField(rectangle2.getY() + "");
    TextField tfWidth2 = new TextField(rectangle2.getWidth() + "");
    TextField tfHeight2 = new TextField(rectangle2.getHeight() + "");
    tfX2.setPrefColumnCount(3);
    tfY2.setPrefColumnCount(3);
    tfWidth2.setPrefColumnCount(3);
    tfHeight2.setPrefColumnCount(3);
    tfX2.setAlignment(Pos.BOTTOM_RIGHT);
    tfY2.setAlignment(Pos.BOTTOM_RIGHT);
    tfWidth2.setAlignment(Pos.BOTTOM_RIGHT);

    pane21.add(tfX2, 1, 0);
    pane21.add(tfY2, 1, 1);
    pane21.add(tfWidth2, 1, 2);
    pane21.add(tfHeight2, 1, 3);

    pane2.setStyle("-fx-border-color: black");
    pane2.setCenter(pane21);

    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(pane1, pane2);

    BorderPane pane = new BorderPane();
    pane.setTop(status);
    BorderPane.setAlignment(status, Pos.CENTER);
    Pane paneForRectangles = new Pane();
    rectangle1.setFill(new Color(1, 1, 1, 0));
    rectangle1.setStroke(Color.BLACK);
    rectangle2.setFill(new Color(1, 1, 1, 0));
    rectangle2.setStroke(Color.BLACK);

    paneForRectangles.getChildren().addAll(rectangle1, rectangle2);
    pane.setCenter(paneForRectangles);
    pane.setBottom(hBox);

    BorderPane bigPane = new BorderPane();
    bigPane.setCenter(pane);
    Button btRedraw = new Button("Redraw Rectangles");
    bigPane.setBottom(btRedraw);
    BorderPane.setAlignment(btRedraw, Pos.CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(bigPane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_09"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    btRedraw.setOnAction(e -> {
      rectangle1.setX(Double.parseDouble(tfX1.getText()));
      rectangle1.setY(Double.parseDouble(tfY1.getText()));
      rectangle1.setWidth(Double.parseDouble(tfWidth1.getText()));
      rectangle1.setHeight(Double.parseDouble(tfHeight1.getText()));
      rectangle2.setX(Double.parseDouble(tfX2.getText()));
      rectangle2.setY(Double.parseDouble(tfY2.getText()));
      rectangle2.setWidth(Double.parseDouble(tfWidth2.getText()));
      rectangle2.setHeight(Double.parseDouble(tfHeight2.getText()));
      updateStatus();
    });

    rectangle1.setOnMouseDragged(e -> {
      if (rectangle1.contains(e.getX(), e.getY())) {
        rectangle1.setX(e.getX() - rectangle1.getWidth() / 2);
        rectangle1.setY(e.getY() - rectangle1.getHeight() / 2);
        tfX1.setText(e.getX() - rectangle1.getWidth() / 2 + "");
        tfY1.setText(e.getY() - rectangle1.getHeight() / 2 + "");
        updateStatus();
      } 
    });
            
    rectangle2.setOnMouseDragged(e -> {
      if (rectangle2.contains(e.getX(), e.getY())) {
        rectangle2.setX(e.getX() - rectangle2.getWidth() / 2);
        rectangle2.setY(e.getY() - rectangle2.getHeight() / 2);
        tfX2.setText(e.getX() - rectangle2.getWidth() / 2 + "");
        tfY2.setText(e.getY() - rectangle2.getHeight() / 2 + "");     
        updateStatus();
      }
    });
  }

  private void updateStatus() {
    double distanceX = Math.abs(rectangle1.getX() + rectangle1.getWidth() / 2 
      - rectangle2.getX() - rectangle2.getWidth() / 2);
    double distanceY = Math.abs(rectangle1.getY() + rectangle2.getWidth() / 2 
      - rectangle2.getY() - rectangle2.getHeight() / 2);

    if (distanceX <= (rectangle1.getWidth() + rectangle2.getWidth()) / 2 &&
        distanceY <= (rectangle1.getHeight() + rectangle2.getHeight()) / 2)  {
      status.setText("Two rectangles intersect? Yes");
    } else {
      status.setText("Two rectangles intersect? No");
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
