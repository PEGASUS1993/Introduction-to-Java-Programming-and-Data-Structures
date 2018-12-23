import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise31_01 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    HBox pane = new HBox(5);
    Scene scene = new Scene(pane, 300, 250);        
    scene.getStylesheets().add("mystyle.css"); // Load the stylesheet          

    StackPane pane1 = new StackPane();
    Circle circle1 = new Circle(50, 50, 30);
    pane1.getChildren().add(circle1);
    
    StackPane pane2 = new StackPane();
    Circle circle2 = new Circle(50, 50, 30);
    pane2.getChildren().add(circle2);

    StackPane pane3 = new StackPane();
    Circle circle3 = new Circle(50, 50, 30);
    pane3.getChildren().add(circle3);

    StackPane pane4 = new StackPane();
    Circle circle4 = new Circle(50, 50, 30);
    pane4.getChildren().add(circle4);

    pane1.getStyleClass().add("border");
    pane4.getStyleClass().add("border");
   
    circle1.getStyleClass().add("plaincircle"); // Add a style class
    circle2.getStyleClass().add("plaincircle"); // Add a style class
    circle3.setId("redcircle"); // Add a style id
   
    pane.getChildren().addAll(pane1, pane2, pane3, pane4);
    pane.setAlignment(Pos.CENTER);

    primaryStage.setTitle("Exercise31_01"); // Set the window title
    primaryStage.setScene(scene); // Place the scene in the window
    primaryStage.show(); // Display the window
  }

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
