import java.util.List;
import java.util.ArrayList;

public class Exercise28_13Extra {
  public static void main(String[] args) {
    String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
      "Denver", "Kansas City", "Chicago", "Boston", "New York",
      "Atlanta", "Miami", "Dallas", "Houston"};

    int[][] edges = {
      {0, 1}, 
      {1, 0}, {1, 2}, {1, 3},
      {2, 1}, {2, 3}, {2, 4}, {2, 10},
       {3, 1}, {3, 2}, {3, 4}, {3, 5},
      {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
      {5, 3}, {5, 4}, {5, 6}, {5, 7},
      {6, 5}, {6, 7},
      {7, 4}, {7, 5}, {7, 6}, {7, 8},
      {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
      {9, 8}, {9, 11},
      {10, 2}, {10, 4}, {10, 8}, {10, 11},
      {11, 8}, {11, 9}, {11, 10}
    };

    UnweightedGraphLongestPath<String> graph = 
      new UnweightedGraphLongestPath<>(vertices, edges);
      
    System.out.println("Find a longest path from Seattle: " + graph.getLongestPath("Seattle"));
    System.out.println("Find a longest: " + graph.getLongestPath());
  }
}

class UnweightedGraphLongestPath<V> extends UnweightedGraph<V> {
  /** Construct an empty graph */
  public UnweightedGraphLongestPath() {
  }
    
  /** Construct a graph from vertices and edges stored in arrays */
  public UnweightedGraphLongestPath(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  /** Construct a graph from vertices and edges stored in List */
  public UnweightedGraphLongestPath(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraphLongestPath(List<Edge> edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraphLongestPath(int[][] edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
   
  /** Return a longest path in the graph */
  public List<Integer> getLongestPath() {
    int length = Integer.MIN_VALUE;
    List<Integer> result = null;
    for (int i = 0; i < vertices.size(); i++) {
      List<Integer> path = this.getLongestPath(i);
      if (length < path.size()) {
        result = path;
      }
    }
    
    return result;
  }

  /** Return a longest path from the specified vertex object
   * Return null if the graph does not contain a longest path */
  public List<Integer> getLongestPath(V vertex) {
    return getLongestPath(getIndex(vertex));
  }

  /** Return a longest path from the specified vertex label
   * Return null if the graph does not contain a longest path */
  public List<Integer> getLongestPath(int v) {   
    boolean[] isVisited = new boolean[getSize()]; 
    return longestPath(v, isVisited);
  }
  
  /** Search for a longest path from v */
  private List<Integer> longestPath(int v, boolean[] isVisited) {
    List<Integer> result = null;
    result = new ArrayList<>();
    result.add(v);
    isVisited[v] = true; // Mark vertex v visited
        
    int height = Integer.MIN_VALUE;
    for (Edge e : neighbors.get(v)) {
      int u = e.v;
      if (!isVisited[u]) {
        List<Integer> k = longestPath(u, isVisited);
        if (height < k.size() + 1) {
          height = k.size() + 1;
          result.clear();
          result.add(v);
          result.addAll(k);
        }
      }
    }

    isVisited[v] = false; // Backtrack, v is marked unvisited now
    return result;
  }
}