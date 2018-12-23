import java.util.ArrayList;
import java.util.Stack;
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

public class Exercise24_10 extends Application {  
  private Stack<Integer> stack = new Stack<>();
  private StackView view = new StackView();
  private Button btInsert = new Button("Insert (push)");
  private Button btDelete = new Button("Delete (pop)");
  private TextField tfNumber = new TextField();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {         
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Enter a value: "),
      tfNumber, btInsert, btDelete);
    hBox.setAlignment(Pos.CENTER);
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(view);
    borderPane.setBottom(hBox);
    
    Label lblStatus = new Label();
    borderPane.setTop(lblStatus);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(borderPane, 600, 200);
    primaryStage.setTitle("Exercise24_10: Stack Animation"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    view.repaint();
    tfNumber.setPrefColumnCount(2); 

    btInsert.setOnAction(e -> {
      stack.push(Integer.parseInt(tfNumber.getText()));
      view.repaint();
    });
    
    btDelete.setOnAction(e -> {
      if (stack.size() > 0) stack.pop(); 
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
     
  public class StackView extends Pane {
    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 30;
    private int boxHeight = 20;
    
    protected void repaint() {    
      getChildren().clear();
      
      if (stack.size() == 0) {
        getChildren().add(new Text(startingX, startingY, "stack is empty"));
      }
      else {
        getChildren().add(new Text(startingX, startingY, "top"));        
        int x = startingX + 10;
        int y = startingY + 10;        
        ArrayList<Integer> list = new ArrayList<>(stack);
        
        for (int i = list.size() - 1; i >= 0; i--) {
          Rectangle rectangle = new Rectangle(x, y, boxWidth, boxHeight);
          rectangle.setFill(Color.WHITE);
          rectangle.setStroke(Color.BLACK);
          getChildren().add(rectangle);
          getChildren().add(new Text(x + 10, y + 15, list.get(i) + ""));
          x = x + boxWidth;
        }
      }
    }
  }
}
