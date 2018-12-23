import java.util.List;
import java.util.Scanner;

public class Exercise28_01Extra {
  public static void main(String[] args) {
    String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
      "Denver", "Kansas City", "Chicago", "Boston", "New York",
      "Atlanta", "Miami", "Dallas", "Houston"};

    int[][] edges = {
      {0, 1}, {0, 3}, {0, 5},
      {1, 0}, {1, 2}, {1, 3},
      {2, 1}, {2, 3}, {2, 4}, {2, 10},
      {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
      {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
      {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
      {6, 5}, {6, 7},
      {7, 4}, {7, 5}, {7, 6}, {7, 8},
      {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
      {9, 8}, {9, 11},
      {10, 2}, {10, 4}, {10, 8}, {10, 11},
      {11, 8}, {11, 9}, {11, 10}
    };

    UnweightedGraphGetNumberOfEdges<String> graph = 
      new UnweightedGraphGetNumberOfEdges<>(vertices, edges);
    Scanner input = new Scanner(System.in);
    System.out.print("The number of edges is " + graph.getNumberOfEdges());
  }
}

class UnweightedGraphGetNumberOfEdges<V> extends UnweightedGraph<V> {
  /** Construct an empty graph */
  public UnweightedGraphGetNumberOfEdges() {
  }
    
  /** Construct a graph from vertices and edges stored in arrays */
  public UnweightedGraphGetNumberOfEdges(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  /** Construct a graph from vertices and edges stored in List */
  public UnweightedGraphGetNumberOfEdges(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraphGetNumberOfEdges(List<Edge> edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraphGetNumberOfEdges(int[][] edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  public int getNumberOfEdges() {
    int count = 0;
    for (List<Edge> list: neighbors) {
      count += list.size(); 
    }
    return count;
  }
}
