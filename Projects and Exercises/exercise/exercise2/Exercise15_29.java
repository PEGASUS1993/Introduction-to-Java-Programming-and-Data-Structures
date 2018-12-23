import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise15_29 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    CarPane car = new CarPane();
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(car, 200, 200);
    primaryStage.setTitle("Exercise15_29"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    Timeline animation = new Timeline(
      new KeyFrame(Duration.millis(100), e -> car.move()));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation
    
    scene.widthProperty().addListener(e -> car.setW(car.getWidth()));
    scene.heightProperty().addListener(e -> car.setH(car.getHeight()));
    
    car.setOnMousePressed(e -> animation.pause());
    car.setOnMouseReleased(e -> animation.play());
    
    car.requestFocus();
    car.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.UP) {
        animation.setRate(animation.getRate() + 1);
      }
      else if (e.getCode() == KeyCode.DOWN) {
        animation.setRate(animation.getRate() - 1);
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

  class CarPane extends Pane {
    private double w = 200;
    private double h = 200;
    private double baseX = 0;
    private double baseY = h;
    private Circle c1 = new Circle(baseX + 10 + 5, baseY - 10 + 5, 5);
    private Circle c2 = new Circle(baseX + 30 + 5, baseY - 10 + 5, 5);
    private Rectangle carBody = new Rectangle(baseX, baseY - 20, 50, 10);
    private Polygon carTop = new Polygon(baseX + 10, baseY - 20, 
              baseX + 20, baseY - 30, baseX + 30, baseY - 30, 
              baseX + 40, baseY - 20);   
    
    public CarPane() {
      carBody.setFill(Color.GREEN);
      carTop.setFill(Color.RED);
      this.getChildren().addAll(c1, c2, carBody, carTop);
    }
    
    public void move() {
      if (baseX > w)
        baseX = -20;
      else
        baseX += 1;
      
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
}
