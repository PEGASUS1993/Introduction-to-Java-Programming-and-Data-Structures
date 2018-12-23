import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise29_16 extends Application {
  private TextField tfVertexName = new TextField();
  private TextField tfX = new TextField();
  private TextField tfY = new TextField();
  private Button btAddVertex = new Button("Add Vertex");
  
  private TextField tfu = new TextField();
  private TextField tfv = new TextField();
  private TextField tfWeight = new TextField();
  private Button btAddEdge = new Button("Add Edge");
   
  private Button btStartOver = new Button("Start Over (Clear Graphs)");
  private Label lblStatus = new Label();

  private WeightedGraph<Vertex> graph = new WeightedGraph<>();
  private GraphView view = new GraphView(graph);
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    GridPane gridPane1 = new GridPane();
    gridPane1.add(new Label("Add a new vertex"), 0, 0);
    gridPane1.add(new Label("Vertex name:"), 0, 1);
    gridPane1.add(new Label("x-coordinate:"), 0, 2);
    gridPane1.add(new Label("y-coordinate:"), 0, 3);
    gridPane1.add(tfVertexName, 1, 1);
    gridPane1.add(tfX, 1, 2);
    gridPane1.add(tfY, 1, 3);
    gridPane1.add(btAddVertex, 1, 4);
    
    GridPane gridPane2 = new GridPane();
    gridPane2.add(new Label("Add a new edge"), 0, 0);
    gridPane2.add(new Label("Vertex u (index):"), 0, 1);
    gridPane2.add(new Label("Vertex v (index):"), 0, 2);
    gridPane2.add(new Label("Weight:"), 0, 3);
    gridPane2.add(tfu, 1, 1);
    gridPane2.add(tfv, 1, 2);
    gridPane2.add(tfWeight, 1, 3);
    gridPane2.add(btAddEdge, 1, 4);
    
    HBox hBox = new HBox(5);
    hBox.setAlignment(Pos.CENTER);
    hBox.getChildren().addAll(gridPane1, gridPane2);
    
    VBox vBox = new VBox(5);
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().addAll(hBox, btStartOver);
    BorderPane pane = new BorderPane();
    pane.setTop(lblStatus);
    pane.setCenter(view);
    pane.setBottom(vBox);
    BorderPane.setAlignment(lblStatus, Pos.CENTER);
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 650, 350);
    primaryStage.setTitle("Exercise29_16"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    btAddVertex.setOnAction(e -> {
      try {
        String name = tfVertexName.getText();
        if (graph.getSize() != Integer.parseInt(name.trim()))
          lblStatus.setText(
            "The next vertex to be added must be index " + graph.getSize());
        else {         
          double x = Integer.parseInt(tfX.getText().trim());
          double y = Integer.parseInt(tfY.getText().trim());

          graph.addVertex(new Vertex(name, x, y));
          view.paint();
        }
      }
      catch (Exception ex) {
        lblStatus.setText("The input must be an integer index");   		    		
      }
    });   
    
    btAddEdge.setOnAction(e -> {
      try {
        int u = Integer.parseInt(tfu.getText().trim());
        int v = Integer.parseInt(tfv.getText().trim());
        double weight = Integer.parseInt(tfWeight.getText().trim());

        if (u < 0 || u >= graph.getSize())
          lblStatus.setText(
            "Vertex " + u + " is not in the graph");
        else if (v < 0 || v >= graph.getSize())
          lblStatus.setText(
            "Vertex " + v + " is not in the graph");
        else if (u == v) 
          lblStatus.setText(
            "Two vertices cannot be the same");
        else {
          graph.addEdge(u, v, weight);
          graph.addEdge(v, u, weight);
          view.setTree(graph.getMinimumSpanningTree());
          view.paint();
       }
     }
     catch (Exception ex) {
        lblStatus.setText("The input must be an integer index");   		    		
     }
    });      
    
    btStartOver.setOnAction(e -> {
      graph.clear();
      view.paint();
    });
  }

  class Vertex implements Displayable {
    private double x, y;
    private String name;
    
    Vertex(String name, double x, double y) {
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
  
  class GraphView extends Pane {
    private Graph<? extends Displayable> graph;
    private UnweightedGraph<? extends Displayable>.SearchTree tree;
          
    public GraphView(Graph<? extends Displayable> graph) {
      this.graph = graph;
    }

    public void setTree(UnweightedGraph<? extends Displayable>.SearchTree tree) {
      this.tree = tree;
      paint();
    }
    
    protected void paint() {   
      getChildren().clear();
      
      // Draw vertices
      List<? extends Displayable> vertices = graph.getVertices();
      
      for (int i = 0; i < graph.getSize(); i++) {
        double x = vertices.get(i).getX();
        double y = vertices.get(i).getY();
        String name = vertices.get(i).getName();
        
        getChildren().addAll(new Circle(x, y, 8), 
          new Text(x - 12, y - 12, name));
      }
      
      // Display edges and weights
      for (int i = 0; i < graph.getSize(); i++) {
        List<Integer> neighbors = graph.getNeighbors(i);
        for (int j = 0; j < neighbors.size(); j++) {
          int v = neighbors.get(j);
          double x1 = graph.getVertex(i).getX();
          double y1 = graph.getVertex(i).getY();
          double x2 = graph.getVertex(v).getX();
          double y2 = graph.getVertex(v).getY();
          
          try {
            getChildren().addAll(new Line(x1, y1, x2, y2), 
              new Text((x1 + x2) / 2 - 4, (y1 + y2) / 2 - 6, 
                ((WeightedGraph)graph).getWeight(i, v) + ""));
          }
          catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
      
      // Highlight the edges in the spanning tree
      if (tree != null) {      
        for (int i = 0; i < tree.getNumberOfVerticesFound(); i++) {
          if (tree.getParent(i) != -1) {
            int v = tree.getParent(i);
            double x1 = graph.getVertex(i).getX();
            double y1 = graph.getVertex(i).getY();
            double x2 = graph.getVertex(v).getX();
            double y2 = graph.getVertex(v).getY();
            
            Line line = new Line(x1, y1, x2, y2);  
            line.setStroke(Color.RED);
            getChildren().add(line);
          }
        }
      }
    }
  }
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
