import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;

public class Exercise32_05 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    FanPane fan = new FanPane();
    
    HBox hBox = new HBox(5);
    Button btPause = new Button("Pause");
    Button btResume = new Button("Resume");
    Button btReverse = new Button("Reverse");
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(btPause, btResume, btReverse);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(fan);
    pane.setBottom(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("Exercise32_05"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
       
    scene.widthProperty().addListener(e -> fan.setW(fan.getWidth()));
    scene.heightProperty().addListener(e -> fan.setH(fan.getHeight()));
    
    btPause.setOnAction(e -> fan.pause());
    btResume.setOnAction(e -> fan.play());
    btReverse.setOnAction(e -> fan.reverse());
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 

class FanPane extends Pane {
  private double w = 200;
  private double h = 200;
  private double radius = Math.min(w, h) * 0.45;
  private Arc arc[] = new Arc[4];   
  private double startAngle = 30;
  private Circle circle = new Circle(w / 2, h / 2, radius);
    
  private int sleepTime = 50;
  
  private Thread thread = new Thread(() -> {
    try {
      while (true) {
        Platform.runLater(() -> move());
        Thread.sleep(sleepTime);
      }
    }
    catch (InterruptedException ex) {
    }
  });
  
  public void pause() {
    thread.suspend();
  }
  
  public void play() {
    thread.resume();
  }
  
  public FanPane() {
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.WHITE);
    getChildren().add(circle);
         
    for (int i = 0; i < 4; i++) {
      arc[i] = new Arc(w / 2, h / 2, radius * 0.9, radius * 0.9, startAngle + i * 90, 35);
      arc[i].setFill(Color.RED); // Set fill color
      arc[i].setType(ArcType.ROUND);
      getChildren().addAll(arc[i]); 
    } 
    
    thread.start();
  }
  
  private double increment = 5;
  
  public void reverse() {
    increment = -increment;
  }
  
  public void move() {
    setStartAngle(startAngle + increment);
  }
    
  public void setStartAngle(double angle) {
    startAngle = angle;
    setValues();
  }
  
  public void setValues() {
    radius = Math.min(w, h) * 0.45;
    circle.setRadius(radius);
    circle.setCenterX(w / 2);
    circle.setCenterY(h / 2);
         
    for (int i = 0; i < 4; i++) {
      arc[i].setRadiusX(radius * 0.9);
      arc[i].setRadiusY(radius * 0.9);
      arc[i].setCenterX(w / 2);
      arc[i].setCenterY(h / 2);
      arc[i].setStartAngle(startAngle + i * 90);
    }     
  }
  
  public void setW(double w) {
    this.w = w;
    setValues();
  }
  
  public void setH(double h) {
    this.h = h;
    setValues();
  }
}
