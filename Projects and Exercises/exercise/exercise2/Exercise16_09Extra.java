import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise16_09Extra extends Application {
  final static double HGAP = 20;
  final static double VGAP = 20;
  final static double RADIUS = 5;
  final static double LENGTH_OF_SLOTS = 40;
  final static double LENGTH_OF_OPENNING = 15;
  final static double Y_FOR_FIRST_NAIL = 50;
  private double paneWidth = 300;
  private double paneHeight = 250;

  private double shift = paneWidth / 2;
  private int numberOfBallsDropped = 0;
  private int moveCount = 0;
  private int position = 0;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BeanMachine beanMachine = new BeanMachine();
    BorderPane pane = new BorderPane();
    pane.setCenter(beanMachine);
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    TextField tfNumberOfSlots = new TextField();
    tfNumberOfSlots.setPrefColumnCount(2);
    Button btStart = new Button("Start");
    Button btStop = new Button("Stop");
    hBox.getChildren().addAll(new Label("Number of slots: "), tfNumberOfSlots,
      btStart, btStop);
    pane.setBottom(hBox);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise16_09"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    tfNumberOfSlots.setOnAction(e -> {
      beanMachine.setNumberOfSlots(Integer.parseInt(tfNumberOfSlots.getText()));
    });
    
    btStart.setOnAction(e -> beanMachine.start());
    btStop.setOnAction(e -> beanMachine.stop());
    
    pane.widthProperty().addListener(e -> paneWidth = pane.getWidth());
    pane.heightProperty().addListener(e -> paneHeight = pane.getHeight());
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }

  class BeanMachine extends Pane {
    private int numberOfSlots = 9;
    private int numberOfRows = numberOfSlots - 2;
    private int[] slots = new int[numberOfSlots];

    final static int HGAP = 20;
    final static int VGAP = 20;
    final static int RADIUS = 5;
    final static int LENGTH_OF_SLOTS = 40;
    final static int LENGTH_OF_OPENNING = 15;
    final static int Y_FOR_FIRST_NAIL = 50;
    final static int RED_BALL_START_Y = Y_FOR_FIRST_NAIL - RADIUS;

    private int yRed = RED_BALL_START_Y;
    private boolean hideRedBall = false;
    private Circle currentBall;
    Timeline animation = new Timeline(
            new KeyFrame(Duration.millis(500), e -> move()));

    private double y_for_bottom_line;

    public void start() {
      animation.play();
      paint();
      dropABall();
    }
    
    public void stop() {
      animation.stop();
    }
    
    public BeanMachine() {
      animation.setCycleCount(Timeline.INDEFINITE);      
    }
    
    public void setNumberOfSlots(int numberOfSlots) {
      this.numberOfSlots = numberOfSlots;
      numberOfRows = numberOfSlots - 2;
      slots = new int[numberOfSlots];
      paint();
    }
    
    private void paint() {
      this.getChildren().clear();    

      double y = Y_FOR_FIRST_NAIL;
      double centerX = paneWidth / 2;

      // Draw pegs in multiple lines
      for (double i = 0; i < numberOfRows; i++) {
        y += VGAP;
        for (double k = 0; k <= i; k++) {
          Circle c = new Circle(centerX - i * HGAP / 2 + k * HGAP, y, RADIUS);
          c.setFill(Color.GREEN);
          getChildren().add(c);
        }
      }

      // Draw vertical lines for slots
      y = y + RADIUS;
      for (double i = 0; i < numberOfSlots; i++) {
        double x = centerX - (numberOfRows - 1) * HGAP / 2 + (i - 1) * HGAP;
        getChildren().add(new Line(x, y, x, y + LENGTH_OF_SLOTS));
      }

      y_for_bottom_line = y + LENGTH_OF_SLOTS;
      // Draw a horizontal line for bottom
      getChildren().add(new Line(centerX - (numberOfRows - 1) * HGAP / 2 - HGAP,
              y + LENGTH_OF_SLOTS, centerX - (numberOfRows - 1) * HGAP / 2 + numberOfRows * HGAP,
              y + LENGTH_OF_SLOTS));

      // Draw two side lines
      getChildren().add(new Line(centerX + HGAP / 2, Y_FOR_FIRST_NAIL + RADIUS,
              centerX - (numberOfRows - 1) * HGAP / 2 + numberOfRows * HGAP, y));
      getChildren().add(new Line(centerX - HGAP / 2, Y_FOR_FIRST_NAIL + RADIUS,
              centerX - (numberOfRows - 1) * HGAP / 2 - HGAP, y));
      // Draw two vertical lines for the openning
      getChildren().add(new Line(centerX - HGAP / 2, Y_FOR_FIRST_NAIL + RADIUS,
              centerX - HGAP / 2, Y_FOR_FIRST_NAIL - LENGTH_OF_OPENNING));
      getChildren().add(new Line(centerX + HGAP / 2, Y_FOR_FIRST_NAIL + RADIUS,
              centerX + HGAP / 2, Y_FOR_FIRST_NAIL - LENGTH_OF_OPENNING));
    }

    /**
     * Move the red ball down right
     */
    public void dropABall() {
      shift = paneWidth / 2;
      moveCount = 0;
      position = 0;
      yRed = RED_BALL_START_Y;
      currentBall = new Circle(shift, yRed, RADIUS);
      currentBall.setFill(Color.RED);
      getChildren().add(currentBall);
    }

    /**
     * Move the red ball down left
     */
    public void moveRedBallLeft() {
      shift -= HGAP / 2;
      yRed += VGAP;
      currentBall.setCenterX(shift);
      currentBall.setCenterY(yRed);
    }

    /**
     * Move the red ball down right
     */
    public void moveRedBallRight() {
      shift += HGAP / 2;
      yRed += VGAP;
      currentBall.setCenterX(shift);
      currentBall.setCenterY(yRed);
    }

    public void moveRedBallDown() {
      currentBall.setCenterY(y_for_bottom_line - slots[position] * 2 * RADIUS + RADIUS);
    }

    public void move() {
      moveCount++;
      if (moveCount <= numberOfRows) {
        if (Math.random() < 0.5) {
          moveRedBallLeft();
        } else {
          moveRedBallRight();
          position++;
        }
      } else {
        slots[position]++;
        moveRedBallDown();
        numberOfBallsDropped++;

        if (numberOfBallsDropped == 10) {
          animation.stop();
        } else {
          dropABall();
        }
      }
    }
  }
}
