import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Exercise28_06 {
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

    UnweightedGraphDetectCycle<String> graph = 
      new UnweightedGraphDetectCycle<>(vertices, edges);

    System.out.print(graph.isCyclic());
  }
}

class UnweightedGraphDetectCycle<V> extends UnweightedGraph<V> {
  /** Construct an empty graph */
  public UnweightedGraphDetectCycle() {
  }
    
  /** Construct a graph from vertices and edges stored in arrays */
  public UnweightedGraphDetectCycle(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  /** Construct a graph from vertices and edges stored in List */
  public UnweightedGraphDetectCycle(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraphDetectCycle(List<Edge> edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraphDetectCycle(int[][] edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  /**
   * Algorithm: Modify the recursive DFS algorithm. Assume that the graph is connected.
    Input: G = (V, E) and a starting vertex v
    Output: a DFS tree rooted at v
    boolean isCyclic() {
      while (there is an unvisited vertex) {
        pick an unvisited vertex, say v;
        visit v;
        for each neighbor w of v
          if (w has not been visited) {
            set v as the parent for w in the tree;
            dfs(w);
          }
          else if (parent[v] != w) {
            return true;
          }
        }
      }
      return false;
   */
  public boolean isCyclic() {
    List<Integer> allVertices = new ArrayList<>();
    for (int i = 0; i < vertices.size(); i++) {
      allVertices.add(i);
    }  
    
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1; // Initialize parent[i] to -1

    // Mark visited vertices
    boolean[] isVisited = new boolean[vertices.size()];

    // Recursively search
    while (allVertices.size() > 0) {
      int v = allVertices.get(0);
      if (isCyclic(v, parent, allVertices, isVisited))
        return true;
    }
    return false;
  }

  /** Recursive method for DFS search */
  private boolean isCyclic(int u, int[] parent, List<Integer> allVertices,
      boolean[] isVisited) {  
    // Store the visited vertex
    allVertices.remove(new Integer(u));
    isVisited[u] = true; // Vertex v visited

    for (Edge e : neighbors.get(u)) {
      if (!isVisited[e.v]) {
        parent[e.v] = u; // The parent of vertex e.v is u
        return isCyclic(e.v, parent, allVertices, isVisited); // Recursive search
      }
      else if (parent[u] != e.v) {
        return true;
      }
    }
    
    return false;
  }
}
