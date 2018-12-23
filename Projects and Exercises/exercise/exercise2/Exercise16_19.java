import javax.swing.GroupLayout.Alignment;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_19 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {       
    HBox hBox = new HBox(3);
    
    FanControl fan1 = new FanControl();
    FanControl fan2 = new FanControl();
    FanControl fan3 = new FanControl();
    fan1.setStyle("-fx-border-color: black");
    fan2.setStyle("-fx-border-color: black");
    fan3.setStyle("-fx-border-color: black");
    
    hBox.getChildren().addAll(fan1, fan2, fan3);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane pane = new BorderPane();
    pane.setCenter(hBox);
    
    Button btStartAll = new Button("Start All");
    btStartAll.setOnAction(e -> {
      fan1.start(); fan2.start(); fan3.start();
    });
    
    Button btStopAll = new Button("Stop All");
    btStopAll.setOnAction(e -> {
      fan1.pause(); fan2.pause(); fan3.pause();
    });

    HBox hBox1 = new HBox(10);
    hBox1.setAlignment(Pos.CENTER);
    hBox1.getChildren().addAll(btStartAll, btStopAll);
    pane.setBottom(hBox1);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 850, 400 + 20);
    primaryStage.setTitle("Exercise16_19"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
} 

class FanControl extends BorderPane {
  private FanPane3 fan = new FanPane3();
  private Timeline animation = new Timeline(
      new KeyFrame(Duration.millis(100), e -> fan.move()));
      
  public FanControl() {      
    HBox hBox = new HBox(5);
    Button btPause = new Button("Pause");
    Button btResume = new Button("Resume");
    Button btReverse = new Button("Reverse");
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(btPause, btResume, btReverse);
    
    this.setCenter(fan);
    this.setTop(hBox);
    
    Slider slSpeed = new Slider();
    slSpeed.setValue(10);
    this.setBottom(slSpeed);
    
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation
        
    btPause.setOnAction(e -> animation.pause());
    btResume.setOnAction(e -> animation.play());
    btReverse.setOnAction(e -> fan.reverse());
    
    slSpeed.setMax(20);
    animation.rateProperty().bind(slSpeed.valueProperty());
    
    this.widthProperty().addListener(e -> fan.setW(fan.getWidth()));
    this.heightProperty().addListener(e -> fan.setH(fan.getHeight()));
  }  
  
  public void start() {
    animation.play();
  }
  
  public void pause() {
    animation.pause();
  }
  
  public void reverse() {
    fan.reverse();
  }
}

class FanPane3 extends Pane {
  private double w = 200;
  private double h = 200;
  private double radius = Math.min(w, h) * 0.45;
  private Arc arc[] = new Arc[4];   
  private double startAngle = 30;
  private Circle circle = new Circle(w / 2, h / 2, radius);
    
  public FanPane3() {
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.WHITE);
    getChildren().add(circle);
         
    for (int i = 0; i < 4; i++) {
      arc[i] = new Arc(w / 2, h / 2, radius * 0.9, 
        radius * 0.9, startAngle + i * 90, 35);
      arc[i].setFill(Color.RED); // Set fill color
      arc[i].setType(ArcType.ROUND);
      getChildren().addAll(arc[i]); 
    } 
    
    setWidth(w);
    setHeight(h);
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
