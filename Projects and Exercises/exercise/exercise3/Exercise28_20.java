import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Use the old Graph.java, UnweightedGraph.java, UnweightedGraph.java. Need to
 * replace it in the future.
 */
public class Exercise28_20 extends Application {
  private List<Vertex> vertices = new ArrayList<>();
  private List<Edge> edges = new ArrayList<>();

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    readGraph();
    
    Graph<Vertex> graph1 = new UnweightedGraph<Vertex>(vertices, edges);
    GraphView view = new GraphView(graph1);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(view, 250, 450);
    primaryStage.setTitle("Exercise28_20"); // Set the stage title
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

  public void readGraph() {
    try {
      System.out.print("Enter a file name for the graph: ");
      Scanner input = new Scanner(System.in);
      String filename = input.nextLine();
      
      Scanner file = new Scanner(new File(filename));
      int n = Integer.parseInt(file.nextLine());
      
      for (int i = 0; i < n; i++) {
        String[] tokens = file.nextLine().split("[\\s+]");
        int x = Integer.parseInt(tokens[1].trim()); 
        int y = Integer.parseInt(tokens[2].trim()); 
        vertices.add(new Vertex(i + "", x, y));
        
        for (int k = 3; k < tokens.length; k++)
          edges.add(new Edge(i, 
            Integer.parseInt(tokens[k].trim())));
      }    
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }
  
  class Vertex implements Displayable {
    private double x, y;
    private String name;
    
    Vertex(String name, int x, int y) {
      this.name = name;
      this.x = x;
      this.y = y;
    }
    
    public double getX() {
      return x;
    }
    
    public double getY() {
      return y;
    }
    
    public String getName() {
      return name;
    }
    
    public boolean equals(Object o) {
      return ((Vertex)o).name.equals(this.name);
    }
  }
}
