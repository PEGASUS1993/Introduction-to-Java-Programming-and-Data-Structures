import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.util.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Exercise16_14Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ClockControl control1 = new ClockControl();
    ClockControl control2 = new ClockControl();
    ClockControl control3 = new ClockControl();
    
    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(control1, control2, control3);
    hBox.setAlignment(Pos.CENTER);

    HBox hBoxForButtons = new HBox(10);
    Button btStop = new Button("Stop");
    Button btStart = new Button("Start");
    hBoxForButtons.setAlignment(Pos.CENTER);    
    hBoxForButtons.getChildren().addAll(btStop, btStart);
    hBoxForButtons.setStyle("-fx-border-width: 4");
    
    BorderPane pane = new BorderPane();
    pane.setCenter(hBox);
    pane.setBottom(hBoxForButtons);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 250, 270);
    primaryStage.setTitle("Exercise16_14"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btStart.setOnAction(e -> {
      control1.start();
      control2.start();
      control3.start();
    });
    
    btStop.setOnAction(e -> {
      control1.stop();
      control2.stop();
      control3.stop();
    });
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

  class ClockControl extends BorderPane {
    private ClockPane clock = new ClockPane(); // Create a clock

    public ClockControl() {
      HBox hBox = new HBox(5);
      Button btStop = new Button("Stop");
      Button btStart = new Button("Start");
      hBox.getChildren().addAll(btStop, btStart);
      hBox.setAlignment(Pos.CENTER);
      hBox.setStyle("-fx-border-width: 3; -fx-border-color: green");
      this.setCenter(clock);
      this.setBottom(hBox);

      btStart.setOnAction(e -> clock.start());
      btStop.setOnAction(e -> clock.stop());

      clock.widthProperty().addListener(ov -> {
        clock.setW(clock.getWidth());
      });

      clock.heightProperty().addListener(ov -> {
        clock.setH(clock.getHeight());
      });
    }    
    
    public void start() {
      clock.start();
    }
 
    public void stop() {
      clock.stop();
    }
   }
  
  class ClockPane extends Pane {
    private int hour;
    private int minute;
    private int second;

    // Clock pane's width and height
    private double w = 250, h = 250;

    // Create a handler for animation
    private EventHandler<ActionEvent> eventHandler = e -> {
      setCurrentTime(); // Set a new clock time
    };

    // Create an animation for a running clock
    private Timeline animation = new Timeline(
            new KeyFrame(Duration.millis(1000), eventHandler));

    /**
     * Construct a default clock with the current time
     */
    public ClockPane() {
      setCurrentTime();

      animation.setCycleCount(Timeline.INDEFINITE);
      animation.play(); // Start animation
    }

    /**
     * Construct a clock with specified hour, minute, and second
     */
    public ClockPane(int hour, int minute, int second) {
      this.hour = hour;
      this.minute = minute;
      this.second = second;
      paintClock();

      animation.setCycleCount(Timeline.INDEFINITE);
      animation.play(); // Start animation
    }

    public void pause() {
      animation.pause();
    }

    public void start() {
      animation.play();
    }

    public void stop() {
      animation.stop();
    }

    /**
     * Return hour
     */
    public int getHour() {
      return hour;
    }

    /**
     * Set a new hour
     */
    public void setHour(int hour) {
      this.hour = hour;
      paintClock();
    }

    /**
     * Return minute
     */
    public int getMinute() {
      return minute;
    }

    /**
     * Set a new minute
     */
    public void setMinute(int minute) {
      this.minute = minute;
      paintClock();
    }

    /**
     * Return second
     */
    public int getSecond() {
      return second;
    }

    /**
     * Set a new second
     */
    public void setSecond(int second) {
      this.second = second;
      paintClock();
    }

    /**
     * Return clock pane's width
     */
    public double getW() {
      return w;
    }

    /**
     * Set clock pane's width
     */
    public void setW(double w) {
      this.w = w;
      paintClock();
    }

    /**
     * Return clock pane's height
     */
    public double getH() {
      return h;
    }

    /**
     * Set clock pane's height
     */
    public void setH(double h) {
      this.h = h;
      paintClock();
    }

    /* Set the current time for the clock */
    public void setCurrentTime() {
      // Construct a calendar for the current date and time
      Calendar calendar = new GregorianCalendar();

      // Set current hour, minute and second
      this.hour = calendar.get(Calendar.HOUR_OF_DAY);
      this.minute = calendar.get(Calendar.MINUTE);
      this.second = calendar.get(Calendar.SECOND);

      paintClock(); // Repaint the clock
    }

    /**
     * Paint the clock
     */
    private void paintClock() {
      // Initialize clock parameters
      double clockRadius = Math.min(w, h) * 0.8 * 0.5;
      double centerX = w / 2;
      double centerY = h / 2;

      // Draw circle
      Circle circle = new Circle(centerX, centerY, clockRadius);
      circle.setFill(Color.WHITE);
      circle.setStroke(Color.BLACK);
      Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
      Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
      Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
      Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");

      // Draw second hand
      double sLength = clockRadius * 0.8;
      double secondX = centerX + sLength
              * Math.sin(second * (2 * Math.PI / 60));
      double secondY = centerY - sLength
              * Math.cos(second * (2 * Math.PI / 60));
      Line sLine = new Line(centerX, centerY, secondX, secondY);
      sLine.setStroke(Color.RED);

      // Draw minute hand
      double mLength = clockRadius * 0.65;
      double xMinute = centerX + mLength
              * Math.sin(minute * (2 * Math.PI / 60));
      double minuteY = centerY - mLength
              * Math.cos(minute * (2 * Math.PI / 60));
      Line mLine = new Line(centerX, centerY, xMinute, minuteY);
      mLine.setStroke(Color.BLUE);

      // Draw hour hand
      double hLength = clockRadius * 0.5;
      double hourX = centerX + hLength
              * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
      double hourY = centerY - hLength
              * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
      Line hLine = new Line(centerX, centerY, hourX, hourY);
      hLine.setStroke(Color.GREEN);

      getChildren().clear();
      getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
    }
  }
}
