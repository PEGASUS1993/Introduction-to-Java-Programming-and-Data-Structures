import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_25 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {    
    TextField tfCar1 = new TextField();
    TextField tfCar2 = new TextField();
    TextField tfCar3 = new TextField();
    TextField tfCar4 = new TextField();
    tfCar1.setPrefColumnCount(2);
    tfCar2.setPrefColumnCount(2);
    tfCar3.setPrefColumnCount(2);
    tfCar4.setPrefColumnCount(2);
    
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(new Label("Car 1: "), tfCar1,
            new Label("Car 2: "), tfCar2, new Label("Car 3: "), tfCar3,
            new Label("Car 4: "), tfCar4);
    
    VBox vBox = new VBox(5);
    CarPane1 car1 = new CarPane1();
    CarPane1 car2 = new CarPane1();
    CarPane1 car3 = new CarPane1();
    CarPane1 car4 = new CarPane1();
    
    vBox.getChildren().addAll(car1, car2, car3, car4);
    
    BorderPane pane = new BorderPane();
    pane.setTop(hBox);
    pane.setCenter(vBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 400, 200);
    primaryStage.setTitle("Exercise16_25"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    tfCar1.setOnAction(e -> car1.setSpeed(
            Integer.parseInt(tfCar1.getText())));
    tfCar2.setOnAction(e -> car2.setSpeed(
            Integer.parseInt(tfCar2.getText())));
    tfCar3.setOnAction(e -> car3.setSpeed(
            Integer.parseInt(tfCar3.getText())));
    tfCar4.setOnAction(e -> car4.setSpeed(
            Integer.parseInt(tfCar4.getText())));
    
    car1.widthProperty().addListener(e -> car1.setW(car1.getWidth()));
    car2.widthProperty().addListener(e -> car2.setW(car2.getWidth()));
    car3.widthProperty().addListener(e -> car3.setW(car3.getWidth()));
    car4.widthProperty().addListener(e -> car4.setW(car4.getWidth()));
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}

class CarPane1 extends Pane {

  private double w = 200;
  private double h = 40;
  private double baseX = 0;
  private double baseY = h;
  private Circle c1 = new Circle(baseX + 10 + 5, baseY - 10 + 5, 5);
  private Circle c2 = new Circle(baseX + 30 + 5, baseY - 10 + 5, 5);
  
  private Rectangle carBody = new Rectangle(baseX, baseY - 20, 50, 10);
  private Polygon carTop = new Polygon(baseX + 10, baseY - 20,
          baseX + 20, baseY - 30, baseX + 30, baseY - 30,
          baseX + 40, baseY - 20);  
  private int speed = 50;
  
  KeyFrame kf = new KeyFrame(Duration.millis(150 - speed), e -> move());
  
  Timeline animation = new Timeline(
    new KeyFrame(Duration.millis(150 - speed), e -> move()));
  
  public void setSpeed(int speed) {    
    animation.setRate(speed);
  }
  
  public CarPane1() {
    this.setStyle("-fx-border-color: black");
    
    carBody.setFill(Color.GREEN);
    carTop.setFill(Color.RED);
    this.getChildren().addAll(c1, c2, carBody, carTop);
    
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation
    
    setOnMousePressed(e -> {
      animation.pause();
    });
    setOnMouseReleased(e -> {
      animation.play();
    });
    
    requestFocus();
    setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.UP) {
        animation.setRate(animation.getRate() + 1);
      } else if (e.getCode() == KeyCode.DOWN) {
        animation.setRate(animation.getRate() - 1);
      }
    });
    
  }
  
  public void move() {
    if (baseX > w) {
      baseX = -20;
    } else {
      baseX += 1;
    }
    
    setValues();
  }
  
  public void setValues() {
    c1.setCenterX(baseX + 10 + 5);
    c1.setCenterY(baseY - 10 + 5);
    c2.setCenterX(baseX + 30 + 5);
    c2.setCenterY(baseY - 10 + 5);
    
    carBody.setX(baseX);
    carBody.setY(baseY - 20);
    
    carTop.getPoints().clear();
    carTop.getPoints().addAll(baseX + 10, baseY - 20,
            baseX + 20, baseY - 30, baseX + 30, baseY - 30,
            baseX + 40, baseY - 20);    
  }
  
  public void setW(double w) {
    this.w = w;
    setValues();
  }
  
  public void setH(double h) {
    this.h = h;
    baseY = h;
    setValues();
  }
}
