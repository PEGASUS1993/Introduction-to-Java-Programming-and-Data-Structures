import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Exercise28_14 {
  public static void main(String[] args) {
    TailModel model = new TailModel();
    System.out.println("The number of the starting patterns that have a solution: " 
        + model.tree.getNumberOfVerticesFound());
    System.out.println("The number of the starting patterns that don't have a solution: " 
        + (model.NUMBER_OF_NODES - model.tree.getNumberOfVerticesFound()));
  }
  
  static class TailModel {
    public final static int DIMENSION = 4;
    // 1 << 9 is 512; 1 << 16 is 65536;
    public final static int NUMBER_OF_NODES = 1 << DIMENSION * DIMENSION; 
    protected UnweightedGraph<Integer>.SearchTree tree; // Define a tree

    /** Construct a model */
    public TailModel() {
      // Create edges
      List<Edge> edges = getEdges();
      
      // Create a graph
      UnweightedGraph<Integer> graph = new UnweightedGraph<Integer>(
        edges, NUMBER_OF_NODES); 

      // Obtain a BSF tree rooted at the target node
      tree = graph.bfs(NUMBER_OF_NODES - 1);
    }

    /** Create all edges for the graph */
    private List<Edge> getEdges() {
      List<Edge> edges =
        new ArrayList<Edge>(); // Store edges

      for (int u = 0; u < NUMBER_OF_NODES; u++) {
        for (int k = 0; k < DIMENSION * DIMENSION; k++) {
          char[] node = getNode(u); // Get the node for vertex u
          if (node[k] == 'H') {
            int v = getFlippedNode(node, k);
            // Add edge (v, u) for a legal move from node u to node v
            edges.add(new Edge(v, u));
          }
        }
      }

      return edges;
    }

    public static int getFlippedNode(char[] node, int position) {
      int row = position / DIMENSION;
      int column = position % DIMENSION;

      flipACell(node, row, column);
      flipACell(node, row - 1, column);
      flipACell(node, row + 1, column);
      flipACell(node, row, column - 1);
      flipACell(node, row, column + 1);

      return getIndex(node);
    }

    public static void flipACell(char[] node, int row, int column) {
      if (row >= 0 && row < DIMENSION && column >= 0 && column < DIMENSION) { 
        // Within the boundary
        if (node[row * DIMENSION + column] == 'H')
          node[row * DIMENSION + column] = 'T'; // Flip from H to T
        else
          node[row * DIMENSION + column] = 'H'; // Flip from T to H
      }
    }

    public static int getIndex(char[] node) {
      int result = 0;

      for (int i = 0; i < DIMENSION * DIMENSION; i++)
        if (node[i] == 'T')
          result = result * 2 + 1;
        else
          result = result * 2 + 0;

      return result;
    }

    public static char[] getNode(int index) {
      char[] result = new char[DIMENSION * DIMENSION];

      for (int i = 0; i < DIMENSION * DIMENSION; i++) {
        int digit = index % 2;
        if (digit == 0)
          result[DIMENSION * DIMENSION - 1 - i] = 'H';
        else
          result[DIMENSION * DIMENSION - 1 - i] = 'T';
        index = index / 2;
      }

      return result;
    }
    
    public List<Integer> getShortestPath(int nodeIndex) {
      List<Integer> path = tree.getPath(nodeIndex);
      
      if (path.size() == 1 && path.get(0) != DIMENSION * DIMENSION - 1)
        return null;
      else
        return path;
    }

    public static void printNode(char[] node) {
      for (int i = 0; i < DIMENSION * DIMENSION; i++)
        if (i % DIMENSION != DIMENSION - 1)
          System.out.print(node[i]);
        else
          System.out.println(node[i]);

      System.out.println();
    }
  }
}
