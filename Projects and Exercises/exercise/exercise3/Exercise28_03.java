import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Exercise28_03 {
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

    Graph<String> graph = new UnweightedGraphWithNonrecursiveDFS<>(vertices, edges);
    UnweightedGraph<String>.SearchTree dfs = 
      graph.dfs(graph.getIndex("Chicago")); 

    java.util.List<Integer> searchOrders = dfs.getSearchOrder();
    System.out.println(dfs.getNumberOfVerticesFound() +
      " vertices are searched in this DFS order:");
    for (int i = 0; i < searchOrders.size(); i++)
      System.out.print(graph.getVertex(searchOrders.get(i)) + " ");
    System.out.println();

    for (int i = 0; i < searchOrders.size(); i++)
      if (dfs.getParent(i) != -1)
        System.out.println("parent of " + graph.getVertex(i) +
          " is " + graph.getVertex(dfs.getParent(i)));
  }
}

class UnweightedGraphWithNonrecursiveDFS<V> extends UnweightedGraph<V> {
  /** Construct an empty graph */
  public UnweightedGraphWithNonrecursiveDFS() {
  }
    
  /** Construct a graph from vertices and edges stored in arrays */
  public UnweightedGraphWithNonrecursiveDFS(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  /** Construct a graph from vertices and edges stored in List */
  public UnweightedGraphWithNonrecursiveDFS(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraphWithNonrecursiveDFS(List<Edge> edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraphWithNonrecursiveDFS(int[][] edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  public List<List<Edge>> cloneEdges() {
    List<List<Edge>> neigborCopy = new ArrayList<>();
    
    for (int i = 0; i < neighbors.size(); i++) {
      List<Edge> edges = new ArrayList<>();
      for (Edge e: neighbors.get(i)) {
        edges.add(e);
      }
      neigborCopy.add(edges);
    }
    
    return neigborCopy;
  }
  
  /*
   * Input: G = (V, E) and a starting vertex v
   * Output: A DFS tree rooted at v
   * 
   * Tree dfs(v) {
   *   push v to the stack;
   *   mark x visited;
   *   while (the stack is not empty) {
   *     peek a vertex from the stack, say x;
   *     if (x still has an unvisited neighbor, say y) {
   *       parent[y] = x;
   *       push y into the stack;
   *       mark y visited;
   *     }
   *     else {  
   *       pop a vertex from the stack;
   *     }
   *   }
   * }  
   */
  @Override /** Obtain a DFS tree starting from vertex v */
  public SearchTree dfs(int v) {
    List<List<Edge>> neighbors = cloneEdges();

    List<Integer> searchOrder = new ArrayList<>();
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1; // Initialize parent[i] to -1

    // Mark visited vertices
    boolean[] isVisited = new boolean[vertices.size()];

    Stack<Integer> stack = new Stack<>();
    stack.push(v);
    searchOrder.add(v);
    isVisited[v] = true; // Vertex x visited
    
    while (!stack.isEmpty()) {
      int x = stack.peek();
      if (neighbors.get(x).size() == 0) {
        stack.pop(); continue;
      }
      else {
        // Find the next unvisited neighbor of x
        for (int i = neighbors.get(x).size() - 1; i >= 0; i--) {
          Edge e = neighbors.get(x).get(i);
          if (!isVisited[e.v]) {
            parent[e.v] = x; // The parent of vertex e.v is x
            stack.push(e.v); // Add a new neighbor to the stack
            isVisited[e.v] = true; // Vertex x visited
            searchOrder.add(e.v);
            neighbors.get(x).remove(i);
            break;
          }
          else {
            neighbors.get(x).remove(i);
          }
        }
      }
    }
    
    return new SearchTree(v, parent, searchOrder);
  }
}
