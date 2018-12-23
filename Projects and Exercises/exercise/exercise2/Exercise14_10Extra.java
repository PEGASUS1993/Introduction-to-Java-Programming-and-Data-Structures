import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import java.util.*;

public class Exercise14_10Extra extends Application {
  int[][] position;
  ArrayList<Integer>[] edge;
  
  public Exercise14_10Extra() throws Exception {
    System.out.print("Enter an URL for the graph file: ");
    Scanner input = new Scanner(System.in);
    String url = input.nextLine();
    
    Scanner file = new Scanner(new java.net.URL(url).openStream());
    int n = Integer.parseInt(file.nextLine());
    
    position = new int[n][2];
    edge = new ArrayList[n];
    
    for (int i = 0; i < n; i++) {
      String[] tokens = file.nextLine().split(" ");
      position[i][0] = Integer.parseInt(tokens[1]); 
      position[i][1] = Integer.parseInt(tokens[2]); 
      
      edge[i] = new ArrayList<>();
      for (int k = 3; k < tokens.length; k++)
        edge[i].add(Integer.parseInt(tokens[k]));
    }
  }
  
  public void start(Stage primaryStage) {         
    double paneWidth = 570;
    double paneHeight = 310;  
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(new GraphView1(position, edge), paneWidth, paneHeight);
    primaryStage.setTitle("Exercise14_09"); // Set the stage title
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

class GraphView1 extends Pane {
  private int[][] position;
  private ArrayList<Integer>[] edge;
  
  public GraphView1(int[][] position, ArrayList[] edge) {
    this.position = position;
    this.edge = edge;
    paint();
  }
  
  private void paint() {    
    // Draw vertices
    for (int i = 0; i < position.length; i++) {
      this.getChildren().add(new Circle(position[i][0], position[i][1], 8));
      this.getChildren().add(new Text(position[i][0] - 12, position[i][1] - 12, i + ""));
    }
    
    // Draw edges
    for (int i = 0; i < edge.length; i++) {
      for (int j = 0; j < edge[i].size(); j++) {
        int v = edge[i].get(j);
        this.getChildren().add(new Line(position[i][0], position[i][1],
            position[v][0], position[v][1]));
      }
    }
  }
}
}

