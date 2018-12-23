import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Exercise29_06 {
  public static void main(String[] args) throws IOException {
    WeightedTailModel16 model = new WeightedTailModel16();

    try (ObjectOutputStream output = new ObjectOutputStream(
        new FileOutputStream("WeightedTailModel16.dat"))) {
      output.writeObject(model);
    }
    
    System.out.println("Done");
  }

  public static class WeightedTailModel16 extends TailModel16
      implements Serializable {
    /** Construct a model */
    public WeightedTailModel16() {
      // Create edges
      List<WeightedEdge> edges = getEdges();

      // Create a graph
      WeightedGraph<Integer> graph = new WeightedGraph<>(edges,
          NUMBER_OF_NODES);

      // Obtain a shortest path tree rooted at the target node
      tree = graph.getShortestPath(NUMBER_OF_NODES - 1);
    }

    /** Create all edges for the graph */
    private List<WeightedEdge> getEdges() {
      // Store edges
      List<WeightedEdge> edges = new ArrayList<>();

      for (int u = 0; u < NUMBER_OF_NODES; u++) {
        for (int k = 0; k < DIMENSION * DIMENSION; k++) {
          char[] node = getNode(u); // Get the node for vertex u
          if (node[k] == 'H') {
            int v = getFlippedNode(node, k);
            int numberOfFlips = getNumberOfFlips(u, v);

            // Add edge (v, u) for a legal move from node u to node v
            edges.add(new WeightedEdge(v, u, numberOfFlips));
          }
        }
      }

      return edges;
    }

    private static int getNumberOfFlips(int u, int v) {
      char[] node1 = getNode(u);
      char[] node2 = getNode(v);

      int count = 0; // Count the number of different cells
      for (int i = 0; i < node1.length; i++)
        if (node1[i] != node2[i])
          count++;

      return count;
    }

    public int getNumberOfFlips(int u) {
      return (int) ((WeightedGraph<Integer>.ShortestPathTree) tree)
          .getCost(u);
    }
  }

  static class TailModel16 implements Serializable {
    public final static int DIMENSION = 4;
    // 1 << 9 is 512; 1 << 16 is 65536;
    public final static int NUMBER_OF_NODES = 1 << DIMENSION
        * DIMENSION;
    protected UnweightedGraph<Integer>.SearchTree tree; // Define a tree

    /** Construct a model */
    public TailModel16() {
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
      List<Edge> edges = new ArrayList<Edge>(); // Store
                                                                            // edges

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
      if (row >= 0 && row < DIMENSION && column >= 0
          && column < DIMENSION) {
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
//      List<Integer> path = tree.getPath(nodeIndex);

      List<Integer> path = new ArrayList<>();
      int index = nodeIndex;
      do {
        path.add(index);
        index = tree.getParent(index);
      }
      while (index != -1);

      if (path.size() == 1
          && path.get(0) != NUMBER_OF_NODES - 1)
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
