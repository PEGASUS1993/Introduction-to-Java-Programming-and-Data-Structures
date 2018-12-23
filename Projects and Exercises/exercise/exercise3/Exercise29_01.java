import java.util.*;

public class Exercise29_01 {
  public static void main(String[] args) {
    String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
      "Denver", "Kansas City", "Chicago", "Boston", "New York",
      "Atlanta", "Miami", "Dallas", "Houston"};

    int[][] edges = {
      {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
      {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
      {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
      {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, 
        {3, 5, 1003},
      {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260},
        {4, 8, 864}, {4, 10, 496},
      {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, 
        {5, 6, 983}, {5, 7, 787},
      {6, 5, 983}, {6, 7, 214},
      {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
      {8, 4, 864}, {8, 7, 888}, {8, 9, 661}, 
        {8, 10, 781}, {8, 11, 810},
      {9, 8, 661}, {9, 11, 1187},
      {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
      {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
    };

    WeightedGraph<String> graph1 = 
      new WeightedGraph<>(vertices, edges);
    WeightedGraph<String>.MST tree1 = graph1.getMinimumSpanningTree();
    System.out.println("Total weight is " + tree1.getTotalWeight());
    tree1.printTree();
  }

  public static class WeightedGraph<V> extends UnweightedGraph<V> {
    /** Construct an empty */
    public WeightedGraph() {
    }

    /** Construct a WeightedGraph from vertices and edged in arrays */
    public WeightedGraph(V[] vertices, int[][] edges) {
      createWeightedGraph(java.util.Arrays.asList(vertices), edges);
    }

    /** Construct a WeightedGraph from vertices and edges in list */
    public WeightedGraph(int[][] edges, int numberOfVertices) {
      List<V> vertices = new ArrayList<>();
      for (int i = 0; i < numberOfVertices; i++)
        vertices.add((V) (new Integer(i)));

      createWeightedGraph(vertices, edges);
    }

    /** Construct a WeightedGraph for vertices 0, 1, 2 and edge list */
    public WeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
      createWeightedGraph(vertices, edges);
    }

    /** Construct a WeightedGraph from vertices 0, 1, and edge array */
    public WeightedGraph(List<WeightedEdge> edges,
        int numberOfVertices) {
      List<V> vertices = new ArrayList<>();
      for (int i = 0; i < numberOfVertices; i++)
        vertices.add((V) (new Integer(i)));

      createWeightedGraph(vertices, edges);
    }

    /** Create adjacency lists from edge arrays */
    private void createWeightedGraph(List<V> vertices, int[][] edges) {
      this.vertices = vertices;

      for (int i = 0; i < vertices.size(); i++) {
        neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
      }

      for (int i = 0; i < edges.length; i++) {
        neighbors.get(edges[i][0]).add(
            new WeightedEdge(edges[i][0], edges[i][1], edges[i][2]));
      }
    }

    /** Create adjacency lists from edge lists */
    private void createWeightedGraph(List<V> vertices,
        List<WeightedEdge> edges) {
      this.vertices = vertices;

      for (int i = 0; i < vertices.size(); i++) {
        neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
      }

      for (WeightedEdge edge : edges) {
        neighbors.get(edge.u).add(edge); // Add an edge into the list
      }
    }

    /** Return the weight on the edge (u, v) */
    public double getWeight(int u, int v) throws Exception {
      for (Edge edge : neighbors.get(u)) {
        if (edge.v == v) {
          return ((WeightedEdge) edge).weight;
        }
      }

      throw new Exception("Edge does not exit");
    }

    /** Display edges with weights */
    public void printWeightedEdges() {
      for (int i = 0; i < getSize(); i++) {
        System.out.print(getVertex(i) + " (" + i + "): ");
        for (Edge edge : neighbors.get(i)) {
          System.out.print("(" + edge.u + ", " + edge.v + ", "
              + ((WeightedEdge) edge).weight + ") ");
        }
        System.out.println();
      }
    }

    /** Add edges to the weighted graph */
    public boolean addEdge(int u, int v, double weight) {
      return addEdge(new WeightedEdge(u, v, weight));
    }

    /** Get a minimum spanning tree using the Kruskal's algorithm */
    public MST getMinimumSpanningTree() {
      WeightedGraph<V> t = new WeightedGraph<>();
      for (int i = 0; i < this.getSize(); i++) {
        t.addVertex(this.vertices.get(i));
      }
      
      PriorityQueue<WeightedEdge> edgeList = getEdges();

      double totalWeight = 0;
      while (!edgeList.isEmpty()) {
        WeightedEdge e = edgeList.remove();
        t.neighbors.get(e.u).add(new WeightedEdge(e.u, e.v, e.weight));
        t.neighbors.get(e.v).add(new WeightedEdge(e.v, e.u, e.weight));
        if (t.getACycle() != null) {
          t.neighbors.get(e.u).remove(t.neighbors.get(e.u).size() - 1);
          t.neighbors.get(e.v).remove(t.neighbors.get(e.v).size() - 1);
        }
        else {
          totalWeight += e.weight;
        }
      }
      
      SearchTree tree = t.dfs(0);
      int[] parent = new int[vertices.size()];
      for (int i = 0; i < parent.length; i++) {
        parent[i] = tree.getParent(i);
      }
      return new MST(tree.getRoot(), parent, tree.getSearchOrder(), totalWeight);
    }

    /** MST is an inner class in WeightedGraph */
    public class MST extends SearchTree {
      private double totalWeight; // Total weight of all edges in the tree

      public MST(int root, int[] parent, List<Integer> searchOrder,
          double totalWeight) {
        super(root, parent, searchOrder);
        this.totalWeight = totalWeight;
      }

      public double getTotalWeight() {
        return totalWeight;
      }
    }

    /** Find single source shortest paths */
    public ShortestPathTree getShortestPath(int sourceVertex) {
      // cost[v] stores the cost of the path from v to the source
      double[] cost = new double[getSize()];
      for (int i = 0; i < cost.length; i++) {
        cost[i] = Double.POSITIVE_INFINITY; // Initial cost set to infinity
      }
      cost[sourceVertex] = 0; // Cost of source is 0

      // parent[v] stores the previous vertex of v in the path
      int[] parent = new int[getSize()];
      parent[sourceVertex] = -1; // The parent of source is set to -1

      // T stores the vertices whose path found so far
      List<Integer> T = new ArrayList<>();

      // Expand T
      while (T.size() < getSize()) {
        // Find smallest cost v in V - T
        int u = -1; // Vertex to be determined
        double currentMinCost = Double.POSITIVE_INFINITY;
        for (int i = 0; i < getSize(); i++) {
          if (!T.contains(i) && cost[i] < currentMinCost) {
            currentMinCost = cost[i];
            u = i;
          }
        }

        T.add(u); // Add a new vertex to T

        // Adjust cost[v] for v that is adjacent to u and v in V - T
        for (Edge e : neighbors.get(u)) {
          if (!T.contains(e.v)
              && cost[e.v] > cost[u] + ((WeightedEdge) e).weight) {
            cost[e.v] = cost[u] + ((WeightedEdge) e).weight;
            parent[e.v] = u;
          }
        }
      } // End of while

      // Create a ShortestPathTree
      return new ShortestPathTree(sourceVertex, parent, T, cost);
    }

    /** ShortestPathTree is an inner class in WeightedGraph */
    public class ShortestPathTree extends SearchTree {
      private double[] cost; // cost[v] is the cost from v to source

      /** Construct a path */
      public ShortestPathTree(int source, int[] parent,
          List<Integer> searchOrder, double[] cost) {
        super(source, parent, searchOrder);
        this.cost = cost;
      }

      /** Return the cost for a path from the root to vertex v */
      public double getCost(int v) {
        return cost[v];
      }

      /** Print paths from all vertices to the source */
      public void printAllPaths() {
        System.out.println("All shortest paths from "
            + vertices.get(getRoot()) + " are:");
        for (int i = 0; i < cost.length; i++) {
          printPath(i); // Print a path from i to the source
          System.out.println("(cost: " + cost[i] + ")"); // Path cost
        }
      }
    }

    public PriorityQueue<WeightedEdge> getEdges() {
      PriorityQueue<WeightedEdge> list = new PriorityQueue<>();
      for (int i = 0; i < this.getSize(); i++) {
        for (Edge e : this.neighbors.get(i)) {
          if (e.u < e.v)
            list.add((WeightedEdge) e);
        }
      }
      return list;
    }

    public List<Integer> getACycle() {
      List<Integer> allVertices = new ArrayList<>();
      for (int i = 0; i < this.vertices.size(); i++) {
        allVertices.add(i);
      }

      List<List<Edge>> neighbors = cloneEdges();

      List<Integer> searchOrder = new ArrayList<>();
      int[] parent = new int[vertices.size()];
      for (int i = 0; i < parent.length; i++)
        parent[i] = -1; // Initialize parent[i] to -1

      // Mark visited vertices
      boolean[] isVisited = new boolean[vertices.size()];

      while (allVertices.size() > 0) {
        int v = allVertices.get(0);

        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        searchOrder.add(v);
        allVertices.remove(new Integer(v));
        isVisited[v] = true; // Vertex x visited

        while (!stack.isEmpty()) {
          int x = stack.peek();
          if (neighbors.get(x).size() == 0) {
            stack.pop();
            continue;
          } else {
            // Find the next unvisited neighbor of x
            for (int i = neighbors.get(x).size() - 1; i >= 0; i--) {
              Edge e = neighbors.get(x).get(i);
              if (!isVisited[e.v]) {
                parent[e.v] = x; // The parent of vertex e.v is x
                stack.push(e.v); // Add a new neighbor to the stack
                isVisited[e.v] = true; // Vertex x visited
                searchOrder.add(e.v);
                allVertices.remove(new Integer(e.v));
                neighbors.get(x).remove(i);
                break;
              } else if (e.v != parent[x]) {
                // A path is found
                List<Integer> list = new ArrayList<>();

                list.add(e.v);
                while (x != e.v) {
                  list.add(x);
                  x = parent[x];
                }

                return list;
              } else {
                neighbors.get(x).remove(i);
              }
            }
          }
        }
      }

      return null;
    }

    public List<List<Edge>> cloneEdges() {
      List<List<Edge>> neigborCopy = new ArrayList<>();

      for (int i = 0; i < neighbors.size(); i++) {
        List<Edge> edges = new ArrayList<>();
        for (Edge e : neighbors.get(i)) {
          edges.add(e);
        }
        neigborCopy.add(edges);
      }

      return neigborCopy;
    }

    public boolean removeEdge(int u, int v) {
      List<Edge> list = this.neighbors.get(u);

      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).v == v) {
          list.remove(i);
          return true;
        } 
      }
      
      return false;
    }
  }
}
