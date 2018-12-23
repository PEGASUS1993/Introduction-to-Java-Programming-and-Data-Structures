import java.util.List;
import java.util.ArrayList;

public class Exercise28_10Extra {
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

    UnweightedGraphHamiltonianPath<String> graph = 
      new UnweightedGraphHamiltonianPath<>(vertices, edges);
      
    System.out.println("Find a Hamiltionian path " + graph.getHamiltonianPath(5));
  }
}

class UnweightedGraphHamiltonianPath<V> extends UnweightedGraph<V> {
  /** Construct an empty graph */
  public UnweightedGraphHamiltonianPath() {
  }
    
  /** Construct a graph from vertices and edges stored in arrays */
  public UnweightedGraphHamiltonianPath(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  /** Construct a graph from vertices and edges stored in List */
  public UnweightedGraphHamiltonianPath(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraphHamiltonianPath(List<Edge> edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraphHamiltonianPath(int[][] edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
   
  /** Return a Hamiltonian path in the graph */
  public List<Integer> getHamiltonianPath() {
    for (int i = 0; i < vertices.size(); i++) {
      List<Integer> path = this.getHamiltonianPath(i);
      if (this.getHamiltonianPath(i) != null) {
        return path;
      }
    }
    
    return null;
  }

  /** Return a Hamiltonian path from the specified vertex object
   * Return null if the graph does not contain a Hamiltonian path */
  public List<Integer> getHamiltonianPath(V vertex) {
    return getHamiltonianPath(getIndex(vertex));
  }

  /** Return a Hamiltonian path from the specified vertex label
   * Return null if the graph does not contain a Hamiltonian path */
  public List<Integer> getHamiltonianPath(int v) {
    // A path starts from v. (i, next[i]) represents an edge in 
    // the path. isVisited[i] tracks whether i is currently in the 
    // path.
    int[] next = new int[getSize()];       
    for (int i = 0; i < next.length; i++)
      next[i] = -1; // Indicate no subpath from i is found yet
    
    boolean[] isVisited = new boolean[getSize()]; 
    
    // The vertices in the Hamiltonian path are stored in result
    List<Integer> result = null;
    
    if (getHamiltonianPath(v, next, isVisited)) {
      result = new ArrayList<Integer>(); // Create a list for path        
      int vertex = v; // Starting from v
      while (vertex != -1) {
        result.add(vertex); // Add vertex to the result list
        vertex = next[vertex]; // Get the next vertex in the path
      }
    }
    
    return result; // return null if no Hamiltonian path is found
  }
  
  /** Return true if all elements in array isVisited are true */
  private boolean allVisited(boolean[] isVisited) {
    boolean result = true;
    
    for (int i = 0; i < getSize(); i++) 
      result = result && isVisited[i];
    
    return result;
  }
  
  /** Search for a Hamiltonian path from v */
  private boolean getHamiltonianPath(int v, int[] next,
      boolean[] isVisited) {
    isVisited[v] = true; // Mark vertex v visited

    if (allVisited(isVisited)) 
      return true; // The path now includes all vertices, thus found
      
    for (Edge e : neighbors.get(v)) {
      int u = e.v;
      if (!isVisited[u] && getHamiltonianPath(u, next, isVisited)) {
        next[v] = u; // Edge (v, u) is in the path
        return true; 
      }
    }

    isVisited[v] = false; // Backtrack, v is marked unvisited now
    return false; // No Hamiltonian path exists from vertex v
  }
}