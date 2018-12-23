import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class Exercise15_19Extra extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    double paneWidth = 470;
    double paneHeight = 410;
    Pane pane = new Pane();

    for (int i = 0; i < 10; i++) {
      Rectangle rectangle = new Rectangle(Math.random() * paneWidth, 
        Math.random() * paneHeight, 150, 100);
      rectangle.setFill(Color.WHITE);
      rectangle.setStroke(Color.BLACK);
      pane.getChildren().add(rectangle);
    }

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, paneWidth, paneHeight);
    primaryStage.setTitle("Exercise15_19"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    pane.setOnDragDetected(e -> {
      System.out.println("Detected");
      int i = pane.getChildren().size() - 1;
      for (; i >= 0; i--) {
        if (pane.getChildren().get(i).contains(e.getX(), e.getY())) {
          break;
        }
      }
      if (i >= 0) {
        Node selectedNode = pane.getChildren().get(i);
        pane.getChildren().remove(selectedNode);
        pane.getChildren().add(selectedNode);
      }

      /* allow any transfer mode */
      Dragboard db = pane.startDragAndDrop(TransferMode.ANY);

      /* put a string on dragboard */
      ClipboardContent content = new ClipboardContent();
      content.putString("move");
      db.setContent(content);

      e.consume();
    });

    pane.setOnDragOver(e -> {
      System.out.println("DragOver");
      ((Rectangle) (pane.getChildren()
        .get(pane.getChildren().size() - 1))).setX(e.getX() - 75);
      ((Rectangle) (pane.getChildren()
        .get(pane.getChildren().size() - 1))).setY(e.getY() - 50);
      e.consume();
    });

    pane.setOnDragDone(e -> {
      System.out.println("Done");
      e.consume();
    });

    pane.setOnDragEntered(e -> {
      System.out.println("Entered");
      e.consume();
    });
  }

  /**
   * The main method is only needed for the IDE with limited JavaFX
   * support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}