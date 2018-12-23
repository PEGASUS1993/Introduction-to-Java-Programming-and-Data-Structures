import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise15_03 extends Application {
  @Override
  // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BallPane ball = new BallPane();
    
    // Create four buttons
    HBox hBox = new HBox(5);
    Button btLeft = new Button("Left");
    Button btRight = new Button("Right");
    Button btUp = new Button("Up");
    Button btDown = new Button("Down");
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(btLeft, btRight, btUp, btDown);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(ball);
    borderPane.setBottom(hBox);
    BorderPane.setAlignment(hBox, Pos.TOP_CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 200, 150);
    primaryStage.setTitle("Exercise15_03"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btLeft.setOnAction(e -> {
      ball.left();
    });

    btRight.setOnAction(e -> {
      ball.right();
    });

    btUp.setOnAction(e -> {
      ball.up();
    });

    btDown.setOnAction(e -> {
      ball.down();
    });
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
  
  class BallPane extends Pane {
    Circle circle = new Circle(30, 40, 10);
    
    BallPane() {
      getChildren().add(circle);
      circle.setFill(Color.WHITE);
      circle.setStroke(Color.BLACK);
    }
    
    public void left() {
      circle.setCenterX(circle.getCenterX() > 0 ? circle.getCenterX() - 2 : 0);
    }
    
    public void right() {
      circle.setCenterX(circle.getCenterX() < getWidth() ? 
          circle.getCenterX() + 2 : getWidth());
    }

    public void up() {
      circle.setCenterY(circle.getCenterY() > 0 ? circle.getCenterY() - 2 : 0);
    }
    
    public void down() {
      circle.setCenterY(circle.getCenterY() < getHeight() ? circle.getCenterY() + 2 : 
        getHeight());
    }
  }
} 

/* Another solution
 * 
 * import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise15_03 extends Application {
  @Override
  // Override the start method in the Application class
  public void start(Stage primaryStage) {
    Pane pane = new Pane();
    Circle circle = new Circle(40, 50, 20);
    circle.setFill(Color.WHITE);
    circle.setStroke(Color.BLACK);
    pane.getChildren().add(circle);
    
    // Create four buttons
    HBox hBox = new HBox(5);
    Button btLeft = new Button("Left");
    Button btRight = new Button("Right");
    Button btUp = new Button("Up");
    Button btDown = new Button("Down");
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(btLeft, btRight, btUp, btDown);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);
    BorderPane.setAlignment(hBox, Pos.TOP_CENTER);

    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 200, 150);
    primaryStage.setTitle("Exercise15_03"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btLeft.setOnAction(e -> {
      circle.setCenterX(circle.getCenterX() > 0 ? circle.getCenterX() - 2 : 0);
    });

    btRight.setOnAction(e -> {
      circle.setCenterX(circle.getCenterX() < scene.getWidth() ? circle.getCenterX() + 2 : 0);
    });

    btUp.setOnAction(e -> {
      circle.setCenterY(circle.getCenterY() > 0 ? circle.getCenterY() - 2 : 0);
    });

    btDown.setOnAction(e -> {
      circle.setCenterY(circle.getCenterY() < scene.getHeight() ? circle.getCenterY() + 2 : 0);
    });
  }
  
  public static void main(String[] args) {
    launch(args);
  }
} 
*/
