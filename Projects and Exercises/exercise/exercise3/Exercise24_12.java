import java.util.LinkedList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise24_12 extends Application {  
  private LinkedList<Integer> queue = new LinkedList<Integer>();
  private QueueView view = new QueueView();
  private Button btInsert = new Button("Insert (enqueue)");
  private Button btDelete = new Button("Delete (dequeue)");
  private TextField tfNumber = new TextField();
   
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {         
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Enter a value: "), tfNumber,
      btInsert, btDelete);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(view);
    borderPane.setBottom(hBox);
    
    Label lblStatus = new Label();
    borderPane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 800, 200);
    primaryStage.setTitle("Exercise24_12: Queue Animation"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    view.repaint();
    tfNumber.setPrefColumnCount(2); 

    btInsert.setOnAction(e -> {
      lblStatus.setText("");
      queue.addLast(Integer.parseInt(tfNumber.getText()));
      view.repaint();
    });
    
    btDelete.setOnAction(e -> {
      lblStatus.setText("");
      if (queue.size() > 0) {
        queue.removeFirst();
      } 
      else {
        lblStatus.setText("Queue is emepty");
      }
      view.repaint();
    });
  } 

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
     
  public class QueueView extends Pane {
    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 30;
    private int boxHeight = 20;
    
    protected void repaint() {
      getChildren().clear();
      
      if (queue.size() == 0) {
        getChildren().add(new Text(startingX, startingY, "queue is empty"));
      }
      else {
        getChildren().add(new Text(startingX, startingY, "queue"));        
        int x = startingX + 10;
        int y = startingY + 10;        
        
        for (int i = 0; i < queue.size(); i++) {
          Rectangle rectangle = new Rectangle(x, y, boxWidth, boxHeight);
          rectangle.setFill(Color.WHITE);
          rectangle.setStroke(Color.BLACK);
          getChildren().add(rectangle);
          getChildren().add(new Text(x + 10, y + 15, queue.get(i) + ""));
          x = x + boxWidth;
        }
      }
    }
  }
}
