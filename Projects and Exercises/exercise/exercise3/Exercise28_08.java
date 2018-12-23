import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Exercise28_08 {
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

    UnweightedGraphTestBipartite<String> graph = 
      new UnweightedGraphTestBipartite<>(vertices, edges);
      
    System.out.println("Is the graph bipartite? " + graph.isBipartite());
  }
}

class UnweightedGraphTestBipartite<V> extends UnweightedGraph<V> {
  /** Construct an empty graph */
  public UnweightedGraphTestBipartite() {
  }
    
  /** Construct a graph from vertices and edges stored in arrays */
  public UnweightedGraphTestBipartite(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  /** Construct a graph from vertices and edges stored in List */
  public UnweightedGraphTestBipartite(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraphTestBipartite(List<Edge> edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraphTestBipartite(int[][] edges, int numberOfVertices) {
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
  
  /*  Modify the BFS algorithm to obtain the depth of each vertex in the tree
  Input: G = (V, E) and a starting vertex v
  Output: a BFS tree rooted at v
  boolean isBipartite() {
    create an empty queue for storing vertices to be visited;
    
    while (there is an unvisited vertex) {
      pick an unvisited vertex, say v;
      add v into the queue;
      mark v visited;
    
      while (the queue is not empty) {
        dequeue a vertex, say u, from the queue;
        add u into a list of traversed vertices;
        for each neighbor w of u {
          if w has not been visited {
            add w into the queue;
            set u as the parent for w in the tree;
            depth[w] = depth[u] + 1;
            mark w visited;
          }
          else if (depth[w] == depth[u]) {
            return false;
          }
        }
      }
    }
    
    return true;
  */
  public boolean isBipartite() {
    List<Integer> allVertices = new ArrayList<>();
    for (int i = 0; i < vertices.size(); i++) {
      allVertices.add(i);
    }
    
    List<List<Edge>> neighbors = cloneEdges();
        
    int[] parent = new int[vertices.size()];
    int[] depth = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++) {
      parent[i] = -1; // Initialize parent[i] to -1
      depth[i] = 0;
    }
  
    java.util.LinkedList<Integer> queue =
      new java.util.LinkedList<>(); // list used as a queue
    boolean[] isVisited = new boolean[vertices.size()];
    
    while (allVertices.size() > 0) {
      int v = allVertices.get(0);
   
      queue.offer(v); // Enqueue v
      isVisited[v] = true; // Mark it visited
    
      while (!queue.isEmpty()) {
        int u = queue.poll(); // Dequeue to u
        
        allVertices.remove(new Integer(u));
        for (Edge e: neighbors.get(u)) {
          if (!isVisited[e.v]) {
            queue.offer(e.v); // Enqueue w
            parent[e.v] = u; // The parent of w is u
            depth[e.v] = depth[u] + 1;
            isVisited[e.v] = true; // Mark it visited
          }
          else if (depth[e.v] == depth[u]) {
            return false;
          }
        }
      }
    }
  
    return true;
  }
}