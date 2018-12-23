import java.util.*;

/** Modify it in the future to define a new class derived from UnweightedGraph 
 * with getHamiltonianPath and getHamiltonianCycle methods 
 */

public class Exercise28_17 {
  public static void main(String[] args) {
    new Exercise28_17();
  }
  
  public Exercise28_17() {
    String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
      "Denver", "Kansas City", "Chicago", "Boston", "New York",
      "Atlanta", "Miami", "Dallas", "Houston"};

    int[][] edges = {
      {0, 1}, {0, 3}, {0, 5},
      {1, 0}, {1, 2}, {1, 3},
      {2, 1}, {2, 3}, {2, 4}, {2, 10},
      {3, 0}, {3, 2}, {3, 4}, {3, 5},
      {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
      {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
      {6, 5}, {6, 7},
      {7, 4}, {7, 5}, {7, 6}, {7, 8},
      {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
      {9, 8}, {9, 11},
      {10, 2}, {10, 4}, {10, 8}, {10, 11},
      {11, 8}, {11, 9}, {11, 10}
    };

    Graph<String> graph1 = new UnweightedGraph<>(vertices, edges);
    System.out.println("The edges for graph1:");
    graph1.printEdges();

    edges = new int[][]{{0, 2}, {1, 2}, {2, 4}, {3, 4}};
    Graph<Integer> graph2 = new UnweightedGraph<>(edges, 5);
    System.out.println("The edges for graph2:");
    graph2.printEdges();
    
    System.out.println("A Hamiltonina path is " + graph1.getHamiltonianPath(0));
    System.out.println("A Hamiltonina cycle is " + graph1.getHamiltonianCycle());
    System.out.println("A Hamiltonina cycle from New York is " + 
      graph1.getHamiltonianCycle(graph1.getIndex("New York")));
    System.out.println("A Hamiltonina path is " + 
        graph1.getHamiltonianPath());
  }

  public interface Graph<V> {
    /** Return the number of vertices in the graph */
    public int getSize();

    /** Return the vertices in the graph */
    public java.util.List<V> getVertices();

    /** Return the object for the specified vertex index */
    public V getVertex(int index);

    /** Return the index for the specified vertex object */
    public int getIndex(V v);

    /** Return the neighbors of vertex with the specified index */
    public java.util.List<Integer> getNeighbors(int index);

    /** Return the degree for a specified vertex */
    public int getDegree(int v);

    /** Print the edges */
    public void printEdges();

    /** Clear the graph */
    public void clear();

    /** Add a vertex to the graph */  
    public boolean addVertex(V vertex);

    /** Add an edge to the graph */  
    public boolean addEdge(int u, int v);

    /** Add an edge to the graph */  
    public boolean addEdge(Edge e);

    /** Remove a vertex v from the graph */  
    public default boolean remove(V v) {
      return true; // Implementation left as an exercise
    }

    /** Remove an edge (u, v) from the graph */  
    public default boolean remove(int u, int v) {
      return true; // Implementation left as an exercise
    }
    
    /** Obtain a depth-first search tree */
    public UnweightedGraph<V>.SearchTree dfs(int v);

    /** Obtain a breadth-first search tree */
    public UnweightedGraph<V>.SearchTree bfs(int v);
    
    /** Return a Hamiltonian path from the specified vertex
     * Return null if the graph does not contain a Hamiltonian path */
    public List<Integer> getHamiltonianPath(V vertex);
    
    /** Return a Hamiltonian path from the specified vertex label
     * Return null if the graph does not contain a Hamiltonian path */
    public List<Integer> getHamiltonianPath(int v);
    
    /** Return a Hamiltonian path from the specified vertex label
     * Return null if the graph does not contain a Hamiltonian path */
    public List<Integer> getHamiltonianPath();

    /** Return a Hamiltonian cycle 
     * Return null if the graph does not contain a Hamiltonian cycle */
    public List<Integer> getHamiltonianCycle();
    
    /** Return a Hamiltonian cycle from vertex at index v */
    public List<Integer> getHamiltonianCycle(int v);
  }

  public class UnweightedGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>(); // Store vertices
    protected List<List<Edge>> neighbors 
      = new ArrayList<>(); // Adjacency lists

    /** Construct an empty graph */
    protected UnweightedGraph() {
    }
    
    /** Construct a graph from vertices and edges stored in arrays */
    protected UnweightedGraph(V[] vertices, int[][] edges) {
      for (int i = 0; i < vertices.length; i++)
        addVertex(vertices[i]);
      
      createAdjacencyLists(edges, vertices.length);
    }

    /** Construct a graph from vertices and edges stored in List */
    protected UnweightedGraph(List<V> vertices, List<Edge> edges) {
      for (int i = 0; i < vertices.size(); i++)
        addVertex(vertices.get(i));
          
      createAdjacencyLists(edges, vertices.size());
    }

    /** Construct a graph for integer vertices 0, 1, 2 and edge list */
    protected UnweightedGraph(List<Edge> edges, int numberOfVertices) {
      for (int i = 0; i < numberOfVertices; i++) 
        addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}
      
      createAdjacencyLists(edges, numberOfVertices);
    }

    /** Construct a graph from integer vertices 0, 1, and edge array */
    protected UnweightedGraph(int[][] edges, int numberOfVertices) {
      for (int i = 0; i < numberOfVertices; i++) 
        addVertex((V)(new Integer(i))); // vertices is {0, 1, ...}
      
      createAdjacencyLists(edges, numberOfVertices);
    }

    /** Create adjacency lists for each vertex */
    private void createAdjacencyLists(
        int[][] edges, int numberOfVertices) {
      for (int i = 0; i < edges.length; i++) {
        addEdge(edges[i][0], edges[i][1]);
      }
    }

    /** Create adjacency lists for each vertex */
    private void createAdjacencyLists(
        List<Edge> edges, int numberOfVertices) {
      for (Edge edge: edges) {
        addEdge(edge.u, edge.v);
      }
    }

    @Override /** Return the number of vertices in the graph */
    public int getSize() {
      return vertices.size();
    }

    @Override /** Return the vertices in the graph */
    public List<V> getVertices() {
      return vertices;
    }

    @Override /** Return the object for the specified vertex */
    public V getVertex(int index) {
      return vertices.get(index);
    }

    @Override /** Return the index for the specified vertex object */
    public int getIndex(V v) {
      return vertices.indexOf(v);
    }

    @Override /** Return the neighbors of the specified vertex */
    public List<Integer> getNeighbors(int index) {
      List<Integer> result = new ArrayList<>();
      for (Edge e: neighbors.get(index))
        result.add(e.v);
      
      return result;
    }

    @Override /** Return the degree for a specified vertex */
    public int getDegree(int v) {
      return neighbors.get(v).size();
    }

    @Override /** Print the edges */
    public void printEdges() {
      for (int u = 0; u < neighbors.size(); u++) {
        System.out.print(getVertex(u) + " (" + u + "): ");
        for (Edge e: neighbors.get(u)) {
          System.out.print("(" + getVertex(e.u) + ", " +
            getVertex(e.v) + ") ");
        }
        System.out.println();
      }
    }

    @Override /** Clear the graph */
    public void clear() {
      vertices.clear();
      neighbors.clear();
    }
    
    @Override /** Add a vertex to the graph */  
    public boolean addVertex(V vertex) {
      if (!vertices.contains(vertex)) {
        vertices.add(vertex);
        neighbors.add(new ArrayList<Edge>());
        return true;
      }
      else {
        return false;
      }
    }

    @Override /** Add an edge to the graph */  
    public boolean addEdge(Edge e) {
      if (e.u < 0 || e.u > getSize() - 1)
        throw new IllegalArgumentException("No such index: " + e.u);

      if (e.v < 0 || e.v > getSize() - 1)
        throw new IllegalArgumentException("No such index: " + e.v);
      
      if (!neighbors.get(e.u).contains(e)) {
        neighbors.get(e.u).add(e);
        return true;
      }
      else {
        return false;
      }
    }
    
    @Override /** Add an edge to the graph */  
    public boolean addEdge(int u, int v) {
      return addEdge(new Edge(u, v));
    }
    
    @Override /** Obtain a DFS tree starting from vertex v */
    /** To be discussed in Section 28.7 */
    public SearchTree dfs(int v) {
      List<Integer> searchOrder = new ArrayList<>();
      int[] parent = new int[vertices.size()];
      for (int i = 0; i < parent.length; i++)
        parent[i] = -1; // Initialize parent[i] to -1

      // Mark visited vertices
      boolean[] isVisited = new boolean[vertices.size()];

      // Recursively search
      dfs(v, parent, searchOrder, isVisited);

      // Return a search tree
      return new SearchTree(v, parent, searchOrder);
    }

    /** Recursive method for DFS search */
    private void dfs(int u, int[] parent, List<Integer> searchOrder,
        boolean[] isVisited) {
      // Store the visited vertex
      searchOrder.add(u);
      isVisited[u] = true; // Vertex v visited

      for (Edge e : neighbors.get(u)) {
        if (!isVisited[e.v]) {
          parent[e.v] = u; // The parent of vertex e.v is u
          dfs(e.v, parent, searchOrder, isVisited); // Recursive search
        }
      }
    }

    @Override /** Starting bfs search from vertex v */
    /** To be discussed in Section 28.9 */
    public SearchTree bfs(int v) {
      List<Integer> searchOrder = new ArrayList<>();
      int[] parent = new int[vertices.size()];
      for (int i = 0; i < parent.length; i++)
        parent[i] = -1; // Initialize parent[i] to -1

      java.util.LinkedList<Integer> queue =
        new java.util.LinkedList<>(); // list used as a queue
      boolean[] isVisited = new boolean[vertices.size()];
      queue.offer(v); // Enqueue v
      isVisited[v] = true; // Mark it visited

      while (!queue.isEmpty()) {
        int u = queue.poll(); // Dequeue to u
        searchOrder.add(u); // u searched
        for (Edge e: neighbors.get(u)) {
          if (!isVisited[e.v]) {
            queue.offer(e.v); // Enqueue w
            parent[e.v] = u; // The parent of w is u
            isVisited[e.v] = true; // Mark it visited
          }
        }
      }

      return new SearchTree(v, parent, searchOrder);
    }

    /** Tree inner class inside the UnweightedGraph class */
    /** To be discussed in Section 28.6 */
    public class SearchTree {
      private int root; // The root of the tree
      private int[] parent; // Store the parent of each vertex
      private List<Integer> searchOrder; // Store the search order

      /** Construct a tree with root, parent, and searchOrder */
      public SearchTree(int root, int[] parent, 
          List<Integer> searchOrder) {
        this.root = root;
        this.parent = parent;
        this.searchOrder = searchOrder;
      }

      /** Return the root of the tree */
      public int getRoot() {
        return root;
      }

      /** Return the parent of vertex v */
      public int getParent(int v) {
        return parent[v];
      }

      /** Return an array representing search order */
      public List<Integer> getSearchOrder() {
        return searchOrder;
      }

      /** Return number of vertices found */
      public int getNumberOfVerticesFound() {
        return searchOrder.size();
      }
      
      /** Return the path of vertices from a vertex to the root */
      public List<V> getPath(int index) {
        ArrayList<V> path = new ArrayList<>();

        do {
          path.add(vertices.get(index));
          index = parent[index];
        }
        while (index != -1);

        return path;
      }

      /** Print a path from the root to vertex v */
      public void printPath(int index) {
        List<V> path = getPath(index);
        System.out.print("A path from " + vertices.get(root) + " to " +
          vertices.get(index) + ": ");
        for (int i = path.size() - 1; i >= 0; i--)
          System.out.print(path.get(i) + " ");
      }

      /** Print the whole tree */
      public void printTree() {
        System.out.println("Root is: " + vertices.get(root));
        System.out.print("Edges: ");
        for (int i = 0; i < parent.length; i++) {
          if (parent[i] != -1) {
            // Display an edge
            System.out.print("(" + vertices.get(parent[i]) + ", " +
              vertices.get(i) + ") ");
          }
        }
        System.out.println();
      }
    }

    /** Return a Hamiltonian path from the specified vertex object
     * Return null if the graph does not contain a Hamiltonian path */
    public List<Integer> getHamiltonianPath() {
      for (int i = 0; i < this.getSize(); i++) {
        List<Integer> path = getHamiltonianPath(i);
        if (path != null)
          return path;
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
      
      // The vertices in the Hamiltionian path are stored in result
      List<Integer> result = null;

      // To speed up search, reorder the adjacency list for each 
      // vertex so that the vertices in the list are in increasing 
      // order of their degrees
      for (int i = 0; i < getSize(); i++)
        reorderNeigborsBasedOnDegree(getNeighbors(i));
      
      if (getHamiltonianPath(v, next, isVisited)) {
        result = new ArrayList<Integer>(); // Create a list for path        
        int vertex = v; // Starting from v
        while (vertex != -1) {
          result.add(vertex); // Add vertex to the result list
          vertex = next[vertex]; // Get the next vertex in the path
        }
      }
      
      return result; // return null if no Hamiltionian path is found
    }

    /** Reorder the adjacency list in increasing order of degrees */
    private void reorderNeigborsBasedOnDegree(List<Integer> list) {
      for (int i = list.size() - 1; i >= 1; i--) {
        // Find the maximum in the list[0..i]
        int currentMaxDegree = getDegree(list.get(0));
        int currentMaxIndex = 0;

        for (int j = 1; j <= i; j++) {
          if (currentMaxDegree < getDegree(list.get(j))) { 
            currentMaxDegree = getDegree(list.get(j));
            currentMaxIndex = j;
          }
        }

        // Swap list[i] with list[currentMaxIndex] if necessary;
        if (currentMaxIndex != i) {
          int temp = list.get(currentMaxIndex);
          list.set(currentMaxIndex, list.get(i));
          list.set(i, temp);
        }
      }
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
        
      for (int i = 0; i < getNeighbors(v).size(); i++) {
        int u = getNeighbors(v).get(i);
        if (!isVisited[u] && 
            getHamiltonianPath(u, next, isVisited)) {
          next[v] = u; // Edge (v, u) is in the path
          return true; 
        }
      }

      isVisited[v] = false; // Backtrack, v is marked unvisited now
      System.out.println("Backtrack at " + v);
      return false; // No Hamiltonian path exists from vertex v
    }
    
    /** Return a Hamiltonian cycle 
     * Return null if the graph does not contain a Hamiltonian cycle */
    public List<Integer> getHamiltonianCycle() {     
      return getHamiltonianCycle(0); 
    }
    
    /** Return a Hamiltonian cycle 
     * Return null if the graph does not contain a Hamiltonian cycle */
    public List<Integer> getHamiltonianCycle(int v) {
      // A path starts from v. (i, next[i]) represents an edge in 
      // the path. isVisited[i] tracks whether i is currently in the 
      // path.
      int[] next = new int[getSize()];       
      for (int i = 0; i < next.length; i++)
        next[i] = -1; // Indicate no subpath from i is found yet
      
      boolean[] isVisited = new boolean[getSize()]; 
      
      // The vertices in the H-path are stored in result
      List<Integer> result = null;

      // To speed up search, reorder the adjacency list for each 
      // vertex so that the vertices in the list are in increasing 
      // order of their degrees
      for (int i = 0; i < getSize(); i++)
        reorderNeigborsBasedOnDegree(getNeighbors(i));
      
      if (getHamiltonianCycle(v, next, isVisited, v)) {
        result = new ArrayList<Integer>(); // Create a list for path        
        int vertex = v;
        while (vertex != -1) {
          result.add(vertex); // Insert vertex to result
          vertex = next[vertex];
        }
      }
      
      return result; 
    }
        
    public boolean getHamiltonianCycle(int v, int[] next,
        boolean[] isVisited, int startV) {
      isVisited[v] = true; // Vertex v visited

      if (allVisited(isVisited) && isCycle(v, startV)) 
        return true;
        
      for (int i = 0; i < getNeighbors(v).size(); i++) {
        int u = getNeighbors(v).get(i);
        if (!isVisited[u] && 
            getHamiltonianCycle(u, next, isVisited, startV)) {
          next[v] = u;
          return true;
        }
      }

      isVisited[v] = false; // Backtrack, v is marked unvisited now
      return false;
    }    
    
    private boolean isCycle(int v, int startV) {
      return getNeighbors(v).contains(startV);
    }
  }
}
