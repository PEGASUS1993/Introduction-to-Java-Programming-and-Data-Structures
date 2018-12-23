import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.geometry.*;
import java.util.*;
import javafx.scene.media.*;

public class Exercise16_06Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    ClockPane clock = new ClockPane(); // Create a clock

    // Create a handler for animation
    EventHandler<ActionEvent> eventHandler = e -> {
      clock.setCurrentTime(); // Set a new clock time
    };
    
    // Create an animation for a running clock
    Timeline animation = new Timeline(
      new KeyFrame(Duration.millis(1000), eventHandler));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation
    
    BorderPane pane = new BorderPane();
    pane.setCenter(clock);
    Button btSetAlarm = new Button("Set Alarm");
    pane.setBottom(btSetAlarm);
    BorderPane.setAlignment(btSetAlarm, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 250, 250);
    primaryStage.setTitle("Exercise16_06"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    clock.widthProperty().addListener(ov ->
      clock.setW(clock.getWidth())
    );
    
    clock.heightProperty().addListener(ov ->
      clock.setH(clock.getHeight())
    );

    btSetAlarm.setOnAction(e -> {
      new AlarmStage();
    });
  }
   

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
  
  private class AlarmStage extends Stage {
    private int hour, minute, second;
    private TextField tfHour = new TextField();
    private TextField tfMinute = new TextField();
    private TextField tfSecond = new TextField();
    private Button btOK = new Button("OK");
    private Button btCancel = new Button("Cancel");
    
    public AlarmStage() {
      GridPane pane = new GridPane();
      pane.add(new Label("Hour"), 0, 0);
      pane.add(new Label("Minute"), 0, 1);
      pane.add(new Label("Second"), 0, 2);
      pane.add(tfHour, 1, 0);
      pane.add(tfMinute, 1, 1);
      pane.add(tfSecond, 1, 2);
      
      HBox hBox = new HBox(5);
      hBox.getChildren().addAll(btOK, btCancel);
      hBox.setAlignment(Pos.BOTTOM_RIGHT);
      
      VBox vBox = new VBox(5);
      vBox.getChildren().addAll(
        new Label("Enter Hour, Minute, and Second"), pane, hBox);
      
      Scene scene = new Scene(vBox);
      this.setScene(scene);
      this.show();
      
      btOK.setOnAction(e -> {
        hour = Integer.parseInt(tfHour.getText());
        minute = Integer.parseInt(tfMinute.getText());
        second = Integer.parseInt(tfSecond.getText());
        
        // Create a Timeline to check timer
        Timeline animation = new Timeline(
          new KeyFrame(Duration.millis(1000), ev -> checkAlarm(hour, minute, second)));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation
        this.hide();
      });
      btCancel.setOnAction(e -> this.hide());
    }
    
    String URL = "http://cs.armstrong.edu/liang/common/audio/anthem/anthem1.mp3";
    AudioClip audioClip = new AudioClip(URL);
    
    private void checkAlarm(int hour, int minute, int second) {
      Calendar calendar = new GregorianCalendar();
      if (calendar.get(Calendar.HOUR) == hour && 
          calendar.get(Calendar.MINUTE) == minute && 
          calendar.get(Calendar.SECOND) == second) {
        audioClip.play();
      }
    }
  }
}
