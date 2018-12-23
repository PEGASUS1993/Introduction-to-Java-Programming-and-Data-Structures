import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/** Use the old Graph.java, UnweightedGraph.java, UnweightedGraph.java. Need to replace it in the future.*/
public class Exercise28_18 extends Application {
  private Model model = new Model();
  private static final int SIZE = 8;
  private int startX = 0;
  private int startY = 0;
  private ArrayList<Point2D> moveHistory = null;

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    BorderPane pane = new BorderPane();
    ChessBoard board = new ChessBoard();
    pane.setCenter(board);
    Button btPath = new Button("Path");
    Button btCycle = new Button("Cycle");

    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(btPath, btCycle);
    hBox.setAlignment(Pos.CENTER);

    pane.setBottom(hBox);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 250, 250);
    primaryStage.setTitle("Exercise28_18"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage

    board.repaint();

    btPath.setOnAction(e -> {
      resetMoveHistory();

      List<Integer> list = model.findHamiltonianPath(startX, startY);
      for (int i = 0; i < list.size(); i++) {
        addMoveHistory(list.get(i) / 8, list.get(i) % 8);
      }

      board.repaint();
    });

    btCycle.setOnAction(e -> {
      resetMoveHistory();

      List<Integer> list = model.findHamiltonianCycle(startX, startY);
      for (int i = 0; i < list.size(); i++) {
        addMoveHistory(list.get(i) / 8, list.get(i) % 8);
      }

      board.repaint();
    });

    scene.widthProperty().addListener(ov -> board.repaint());
    scene.heightProperty().addListener(ov -> board.repaint());
  }

  public void resetMoveHistory() {
    moveHistory = new ArrayList(63);
  }

  public void addMoveHistory(int x, int y) {
    moveHistory.add(new Point2D(x, y));
  }

  public void removeLastMoveHistory() {
    moveHistory.remove(moveHistory.size() - 1);
  }

  private class ChessBoard extends Pane {

    ImageView knightImageView = new ImageView("image/knight.jpg");

    ChessBoard() {
      this.setOnMouseClicked(e -> {
        startX = (int) (e.getX() / (getWidth() / SIZE));
        startY = (int) (e.getY() / (getHeight() / SIZE));
        resetMoveHistory();
        repaint();
      });
    }

    protected void repaint() {
      // Clear previous drawing
      this.getChildren().clear();

      // Add the Knight image
      this.getChildren().add(knightImageView);
      knightImageView.setX(startX * getWidth() / SIZE);
      knightImageView.setY(startY * getHeight() / SIZE);
      knightImageView.setFitWidth(getWidth() / SIZE);
      knightImageView.setFitHeight(getHeight() / SIZE);
      // Draw the lines
      for (int i = 1; i <= SIZE; i++) {
        this.getChildren().add(
                new Line(0, i * getHeight() / SIZE, getWidth(), i * getHeight() / SIZE));
        this.getChildren().add(
                new Line(i * getWidth() / SIZE, 0, i * getWidth() / SIZE, getHeight()));
      }

      // Draw the moves
      if (moveHistory != null) {
        for (int i = 1; i < moveHistory.size(); i++) {
          Point2D p1 = moveHistory.get(i - 1);
          Point2D p2 = moveHistory.get(i);
          this.getChildren().add(
                  new Line(p1.getX() * (getWidth() / SIZE) + getWidth() / SIZE / 2,
                  p1.getY() * (getHeight() / SIZE) + (getHeight() / SIZE / 2),
                  p2.getX() * (getWidth() / SIZE) + getWidth() / SIZE / 2,
                  p2.getY() * (getHeight() / SIZE) + getHeight() / SIZE / 2));
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

  public interface Graph {

    /**
     * Return the number of vertices in the graph
     */
    public int getSize();

    /**
     * Return the vertices in the graph
     */
    public Object[] getVertices();

    /**
     * Return the object for the specified vertex index
     */
    public Object getVertex(int index);

    /**
     * Return the index for the specified vertex object
     */
    public int getIndex(Object v);

    /**
     * Return the neighbors of vertex v
     */
    public java.util.List getNeighbors(int v);

    /**
     * Return the degree for a specified vertex
     */
    public int getDegree(int v);

    /**
     * Return the adjacency matrix
     */
    public int[][] getAdjacencyMatrix();

    /**
     * Print the adjacency matrix
     */
    public void printAdjacencyMatrix();

    /**
     * Print the edges
     */
    public void printEdges();

    /**
     * Obtain a depth-first search tree
     */
    public UnweightedGraph.Tree dfs(int v);

    /**
     * Obtain a breadth-first search tree
     */
    public UnweightedGraph.Tree bfs(int v);

    /**
     * Obtain a depth-first search tree
     */
    public List<Integer> getHamiltonianPath(int v);

    /**
     * Obtain a depth-first search tree
     */
    public List<Integer> getHamiltonianCycle(int v);

    /** Return a Hamiltonian cycle 
     * Return null if the graph does not contain a Hamiltonian cycle */
    public List<Integer> getHamiltonianCycle();
  }

  public class UnweightedGraph implements Graph {

    protected Object[] vertices; // Store vertices
    protected LinkedList<Integer>[] neighbors; // Adjacency lists

    /**
     * Construct a graph from edges and vertices stored in arrays
     */
    protected UnweightedGraph(int[][] edges, Object[] vertices) {
      this.vertices = vertices;
      createAdjacencyLists(edges, vertices.length);
    }

    /**
     * Construct a graph from edges and vertices stored in ArrayList
     */
    protected UnweightedGraph(List<Edge> edges, List<Object> vertices) {
      this.vertices = vertices.toArray();
      createAdjacencyLists(edges, vertices.size());
    }

    /**
     * Construct a graph from edges and vertices in ArrayList
     */
    protected UnweightedGraph(List<Edge> edges, int numberOfVertices) {
      vertices = new Integer[numberOfVertices]; // Create vertices
      for (int i = 0; i < numberOfVertices; i++) {
        vertices[i] = new Integer(i); // vertices is {0, 1, 2, ...}
      }
      createAdjacencyLists(edges, numberOfVertices);
    }

    /**
     * Construct a graph from edges in array
     */
    protected UnweightedGraph(int[][] edges, int numberOfVertices) {
      vertices = new Integer[numberOfVertices]; // Create vertices
      for (int i = 0; i < numberOfVertices; i++) {
        vertices[i] = new Integer(i); // vertices is {0, 1, 2, ...}
      }
      createAdjacencyLists(edges, numberOfVertices);
    }

    /**
     * Create adjacency lists for each vertex
     */
    @SuppressWarnings("unchecked")
    private void createAdjacencyLists(
            int[][] edges, int numberOfVertices) {
      // Create a linked list
      neighbors = new LinkedList[numberOfVertices];
      for (int i = 0; i < numberOfVertices; i++) {
        neighbors[i] = new java.util.LinkedList<Integer>();
      }

      for (int i = 0; i < edges.length; i++) {
        int u = edges[i][0];
        int v = edges[i][1];
        neighbors[u].add(v);
      }
    }

    /**
     * Create adjacency lists for each vertex
     */
    @SuppressWarnings("unchecked")
    private void createAdjacencyLists(
            List<Edge> edges, int numberOfVertices) {
      // Create a linked list
      neighbors = new LinkedList[numberOfVertices];
      for (int i = 0; i < numberOfVertices; i++) {
        neighbors[i] = new java.util.LinkedList<Integer>();
      }

      for (Edge edge : edges) {
        neighbors[edge.u].add(edge.v);
      }
      for (int i = 0; i < numberOfVertices; i++) {
        System.out.println("Vertex: " + i);
        sort(neighbors[i]);
      }
    }

    /**
     * Return the number of vertices in the graph
     */
    public int getSize() {
      return vertices.length;
    }

    /**
     * Return the vertices in the graph
     */
    public Object[] getVertices() {
      return vertices;
    }

    /**
     * Return the object for the specified vertex
     */
    public Object getVertex(int v) {
      return vertices[v];
    }

    /**
     * Return the index for the specified vertex object
     */
    public int getIndex(Object vertex) {
      for (int i = 0; i < getSize(); i++) {
        if (vertex.equals(vertices[i])) {
          return i;
        }
      }

      return -1; // If vertex is not in the graph
    }

    /**
     * Return the neighbors of vertex v
     */
    public java.util.List<Integer> getNeighbors(int v) {
      return neighbors[v];
    }

    /**
     * Return the degree for a specified vertex
     */
    public int getDegree(int v) {
      return neighbors[v].size();
    }

    /**
     * Return the adjacency matrix
     */
    public int[][] getAdjacencyMatrix() {
      int[][] adjacencyMatrix = new int[getSize()][getSize()];

      for (int i = 0; i < neighbors.length; i++) {
        for (int j = 0; j < neighbors[i].size(); j++) {
          int v = neighbors[i].get(j);
          adjacencyMatrix[i][v] = 1;
        }
      }

      return adjacencyMatrix;
    }

    /**
     * Print the adjacency matrix
     */
    public void printAdjacencyMatrix() {
      int[][] adjacencyMatrix = getAdjacencyMatrix();
      for (int i = 0; i < adjacencyMatrix.length; i++) {
        for (int j = 0; j < adjacencyMatrix[0].length; j++) {
          System.out.print(adjacencyMatrix[i][j] + " ");
        }

        System.out.println();
      }
    }

    /**
     * Print the edges
     */
    public void printEdges() {
      for (int u = 0; u < neighbors.length; u++) {
        System.out.print("Vertex " + u + ": ");
        for (int j = 0; j < neighbors[u].size(); j++) {
          System.out.print("(" + u + ", "
                  + neighbors[u].get(j) + ") ");
        }
        System.out.println();
      }
    }

    //Moved to the top-level class to keep the static modifier
//    /** Edge inner class inside the UnweightedGraph class */
//    public class Edge {
//      public int u; // Starting vertex of the edge
//      public int v; // Ending vertex of the edge
//
//      /** Construct an edge for (u, v) */
//      public Edge(int u, int v) {
//        this.u = u;
//        this.v = v;
//      }
//    }
    /**
     * Obtain a DFS tree starting from vertex v
     */
    /**
     * To be discussed in Section 13.6
     */
    public UnweightedGraph.Tree dfs(int v) {
      List<Integer> searchOrders = new ArrayList<Integer>();
      int[] parent = new int[vertices.length];
      for (int i = 0; i < parent.length; i++) {
        parent[i] = -1; // Initialize parent[i] to -1
      }
      // Mark visited vertices
      boolean[] isVisited = new boolean[vertices.length];

      // Recursively search
      dfs(v, parent, searchOrders, isVisited);

      // Return a search tree
      return new UnweightedGraph.Tree(v, parent, searchOrders);
    }

    /**
     * Recursive method for DFS search
     */
    private void dfs(int v, int[] parent, List<Integer> searchOrders,
            boolean[] isVisited) {

      // Store the visited vertex
      searchOrders.add(v);
      isVisited[v] = true; // Vertex v visited

      for (int i : neighbors[v]) {
        if (!isVisited[i]) {
          parent[i] = v; // The parent of vertex i is v
          dfs(i, parent, searchOrders, isVisited); // Recursive search
        }
      }
    }

    /**
     * Obtain a Hamiltonian Path tree starting from vertex v
     */
    /**
     * To be discussed in Section 13.6
     */
    public List<Integer> getHamiltonianPath(int v) {
      List<Integer> searchOrders = new ArrayList<Integer>();
      int[] parent = new int[vertices.length];
      for (int i = 0; i < parent.length; i++) {
        parent[i] = -1; // Initialize parent[i] to -1
      }
      // Mark visited vertices
      boolean[] isVisited = new boolean[vertices.length];

      // Recursively search
      hamiltonianPath(v, parent, searchOrders, isVisited);

      // Return a search tree
      return searchOrders;
    }

    /**
     * Recursive method for hamiltonianPath search
     */
    private void hamiltonianPath(int v, int[] parent, List<Integer> searchOrders,
            boolean[] isVisited) {
      //System.out.println("Visiting: " + v + "which has " + neighbors[v].size() + " neighbors.");
      // Store the visited vertex
      searchOrders.add(v);
      isVisited[v] = true; // Vertex v visited

      for (int i : neighbors[v]) {
        if (!isVisited[i]) {
          parent[i] = v; // The parent of vertex i is v
          hamiltonianPath(i, parent, searchOrders, isVisited); // Recursive search
        }

      }
      if (!allVisited(isVisited)) {
        //System.out.println("Backtracking...");
        isVisited[v] = false;
        parent[v] = -1;
        searchOrders.remove(new Integer(v));
      }
    }

    /**
     * Obtain a Hamiltonian Cycle tree starting from vertex v
     */
    /**
     * To be discussed in Section 13.6
     */
    public List<Integer> getHamiltonianCycle(int v) {
      List<Integer> searchOrders = new ArrayList<>();
      int[] parent = new int[vertices.length];
      for (int i = 0; i < parent.length; i++) {
        parent[i] = -1; // Initialize parent[i] to -1
      }
      // Mark visited vertices
      boolean[] isVisited = new boolean[vertices.length];

      // Recursively search
      hamiltonianCycle(v, parent, searchOrders, isVisited);

      // Return a search tree
      return searchOrders;
    }

    /**
     * Recursive method for hamiltonianPath search
     */
    private void hamiltonianCycle(int v, int[] parent, List<Integer> searchOrders,
            boolean[] isVisited) {
      //System.out.println("Visiting: " + v + "which has " + neighbors[v].size() + " neighbors.");
      // Store the visited vertex
      searchOrders.add(v);
      isVisited[v] = true; // Vertex v visited

      for (int i : neighbors[v]) {
        if (!isVisited[i]) {
          parent[i] = v; // The parent of vertex i is v
          hamiltonianCycle(i, parent, searchOrders, isVisited); // Recursive search
        }

      }
      if (!allVisited(isVisited) || !checkCycle(searchOrders)) {
        //System.out.println("Backtracking...");
        isVisited[v] = false;
        parent[v] = -1;
        searchOrders.remove(new Integer(v));
      }
    }

    private boolean checkCycle(List<Integer> searchOrders) {
      int startVertex = (Integer) searchOrders.get(0);
      int lastVertex = (Integer) searchOrders.get(searchOrders.size() - 1);
      //System.out.println("startVertex: " + startVertex + "lastVertex: " + lastVertex);
      if ((Math.abs((startVertex / 8) - (lastVertex / 8)) == 2 && Math.abs((startVertex % 8) - (lastVertex % 8)) == 1)
              || (Math.abs((startVertex / 8) - (lastVertex / 8)) == 1 && Math.abs((startVertex % 8) - (lastVertex % 8)) == 2)) {
        return true;
      }

      return false;
    }

    private void sort(List<Integer> list) {
      Integer currentElement = 0;
      for (int i = 1; i < list.size(); i++) {
        currentElement = (Integer) list.get(i);
        int j;
        for (j = i - 1; j >= 0 && neighbors[(Integer) list.get(j)].size() >= neighbors[currentElement].size(); j--) {
          list.set(j + 1, list.get(j));
        }
        list.set(j + 1, currentElement);
      }
    }

    private boolean allVisited(boolean[] isVisited) {
      for (int i = 0; i < isVisited.length; i++) {
        if (!isVisited[i]) {
          return false;
        }
      }
      return true;
    }

    /**
     * Starting bfs search from vertex v
     */
    /**
     * To be discussed in Section 13.7
     */
    public UnweightedGraph.Tree bfs(int v) {
      List<Integer> searchOrders = new ArrayList<Integer>();
      int[] parent = new int[vertices.length];
      for (int i = 0; i < parent.length; i++) {
        parent[i] = -1; // Initialize parent[i] to -1
      }
      java.util.LinkedList<Integer> queue =
              new java.util.LinkedList<>(); // list used as a queue
      boolean[] isVisited = new boolean[vertices.length];
      queue.offer(v); // Enqueue v
      isVisited[v] = true; // Mark it visited

      while (!queue.isEmpty()) {
        int u = queue.poll(); // Dequeue to u
        searchOrders.add(u); // u searched
        for (int w : neighbors[u]) {
          if (!isVisited[w]) {
            queue.offer(w); // Enqueue w
            parent[w] = u; // The parent of w is u
            isVisited[w] = true; // Mark it visited
          }
        }
      }

      return new UnweightedGraph.Tree(v, parent, searchOrders);
    }

    /**
     * Tree inner class inside the UnweightedGraph class
     */
    /**
     * To be discussed in Section 13.5
     */
    public class Tree {

      private int root; // The root of the tree
      private int[] parent; // Store the parent of each vertex
      private List<Integer> searchOrders; // Store the search order

      /**
       * Construct a tree with root, parent, and searchOrder
       */
      public Tree(int root, int[] parent, List<Integer> searchOrders) {
        this.root = root;
        this.parent = parent;
        this.searchOrders = searchOrders;
      }

      /**
       * Construct a tree with root and parent without a particular order
       */
      public Tree(int root, int[] parent) {
        this.root = root;
        this.parent = parent;
      }

      /**
       * Return the root of the tree
       */
      public int getRoot() {
        return root;
      }

      /**
       * Return the parent of vertex v
       */
      public int getParent(int v) {
        return parent[v];
      }

      /**
       * Return an array representing search order
       */
      public List<Integer> getSearchOrders() {
        return searchOrders;
      }

      /**
       * Return number of vertices found
       */
      public int getNumberOfVerticesFound() {
        return searchOrders.size();
      }

      /**
       * Return the iterator for a path starting from the root to v
       */
      public java.util.Iterator<Integer> pathIterator(int v) {
        return new UnweightedGraph.Tree.PathIterator(v);
      }

      /**
       * PathIterator inner class inside the tree
       */
      public class PathIterator implements java.util.Iterator<Integer> {

        private Stack<Integer> stack;

        /**
         * Construct an iterator for the vertices on the path
         */
        public PathIterator(int v) {
          stack = new Stack<>();
          do {
            stack.add(v);
            v = parent[v];
          } while (v != -1);
        }

        /**
         * Has next element in the iterator?
         */
        public boolean hasNext() {
          return !stack.isEmpty();
        }

        /* Get the current element in the iterator and move the
         * iterator to point to the next element */
        public Integer next() {
          return (Integer) vertices[stack.pop()];
        }

        /* This remove method is defined in the Iterator interface
         * do not implement it */
        public void remove() {
          // Do nothing
        }
      }

      /**
       * Print a path from the root to vertex v
       */
      public void printPath(int v) {
        Iterator<Integer> iterator = pathIterator(v);
        System.out.print("A path from " + vertices[root] + " to "
                + vertices[v] + ": ");
        while (iterator.hasNext()) {
          System.out.print(iterator.next() + " ");
        }
      }

      /**
       * Print the whole tree
       */
      public void printTree() {
        System.out.println("Root is: " + vertices[root]);
        System.out.print("Edges: ");
        for (int i = 0; i < parent.length; i++) {
          if (parent[i] != -1) {
            // Display an edge
            System.out.print("(" + vertices[parent[i]] + ", "
                    + vertices[i] + ") ");
          }
        }
        System.out.println();
      }
    }
    
        /** Return a Hamiltonian cycle 
     * Return null if the graph does not contain a Hamiltonian cycle */
    public List<Integer> getHamiltonianCycle() {
      return getHamiltonianCycle(0);
    }
  }

  public class Model {

    Graph graph = null;

    public Model() {
      //Construct the model 

      List<Object> vertices = new ArrayList<Object>();
      List<Edge> edges = new ArrayList<Edge>();
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          //First the vertices
          //vertices.add(new String(i + ", " + j));
          vertices.add(new String(i * 8 + j + ""));

          //Second the Edges
          int nextX = 0;
          int nextY = 0;
          for (int g = -2; g <= 2; g += 4) {
            for (int h = -1; h <= 1; h += 2) {
              nextX = i + g;
              nextY = j + h;
              if (nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8) {
                edges.add(new Edge(i * 8 + j, nextX * 8 + nextY));
              }

              nextX = i + h;
              nextY = j + g;
              if (nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8) {
                edges.add(new Edge(i * 8 + j, nextX * 8 + nextY));
              }
            }
          }
        }

      }

      graph = new UnweightedGraph(edges, vertices);
    }

    public List<Integer> findHamiltonianPath(int startX, int startY) {
      System.out.println("findHamiltonianPath()");
      return graph.getHamiltonianPath(startX * 8 + startY);
    }

    public List<Integer> findHamiltonianCycle(int startX, int startY) {
      System.out.println("findHamiltonianCycle()");
      return graph.getHamiltonianCycle(startX * 8 + startY);
    }
  }
}
