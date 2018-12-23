import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Exercise31_19 extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {   
    SplitPane sp1 = new SplitPane();
    sp1.setOrientation(Orientation.HORIZONTAL);

    SplitPane sp2 = new SplitPane();
    SplitPane sp3 = new SplitPane();
    
    sp2.setOrientation(Orientation.VERTICAL);
    sp3.setOrientation(Orientation.VERTICAL);
    
    StackPane pane1 = new StackPane();
    StackPane pane2 = new StackPane();
    StackPane pane3 = new StackPane();
    StackPane pane4 = new StackPane();
    
    Circle circle = new Circle(50);
    circle.setFill(null);
    pane1.getChildren().add(circle);
    circle.setStroke(Color.BLACK);
    
    Rectangle rectangle = new Rectangle(50, 60);
    rectangle.setFill(null);
    rectangle.setStroke(Color.BLACK);
    pane2.getChildren().add(rectangle);
    
    Arc arc = new Arc(60, 100, 50, 50, 78, 90);
    arc.setFill(null);
    arc.setStroke(Color.BLACK);
    pane3.getChildren().add(arc);
    
    Ellipse ellipse = new Ellipse(60, 100, 50, 30);
    ellipse.setFill(null);
    ellipse.setStroke(Color.BLACK);
    pane4.getChildren().add(ellipse);
    
    sp2.getItems().addAll(pane1, pane2);
    sp3.getItems().addAll(pane3, pane4);
    sp1.getItems().addAll(sp2, sp3);
    
    Scene scene = new Scene(sp1, 300, 250);           
    primaryStage.setTitle("Exercise31_19"); // Set the window title
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
