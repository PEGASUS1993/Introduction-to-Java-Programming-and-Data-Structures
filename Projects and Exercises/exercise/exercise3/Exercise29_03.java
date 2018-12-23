/** Solution provided by Næsje Frode, April 2013 */
import java.util.*;

public class Exercise29_03 {
  public static void main(String[] args) {
    new Exercise29_03();
  }
  
  public Exercise29_03() {
    int [][] edges = new int[][]{
      {0, 1, 2}, {0, 3, 8}, 
      {1, 0, 2}, {1, 2, 7}, {1, 3, 3},
      {2, 1, 7}, {2, 3, 4}, {2, 4, 5},
      {3, 0, 8}, {3, 1, 3}, {3, 2, 4}, {3, 4, 6},
      {4, 2, 5}, {4, 3, 6}
    };
    
    Integer[][] adjacencyMatrix = {
    		{null,   2,null,   8,null},
    		{   2,null,   7,   3,null},
    		{null,   7,null,   4,   5},
    		{   8,   3,   4,null,   6},
    		{null,null,   5,   6,null}
    };
    
    System.out.println("\nSolution with adjacency matrix:");
    WeightedGraphAdj<Integer> graph = new WeightedGraphAdj<>(edges, 5);
    WeightedGraphAdj<Integer>.ShortestPathTree tree = graph.getShortestPath(3, adjacencyMatrix);
    tree.printAllPaths();
  }
  
  public class WeightedGraphAdj<V> extends WeightedGraph<V> {
    /** Construct a WeightedGraph from edges and vertices in arrays */
    public WeightedGraphAdj() {
    }

    /** Construct a WeightedGraph from edges and vertices in arrays */
    public WeightedGraphAdj(V[] vertices, int[][] edges) {
      super(vertices, edges);
    }

    /** Construct a WeightedGraph from edges and vertices in List */
    public WeightedGraphAdj(int[][] edges, int numberOfVertices) {
      super(edges, numberOfVertices);
    }

    /** Construct a WeightedGraph for vertices 0, 1, 2 and edge list */
    public WeightedGraphAdj(List<V> vertices, List<WeightedEdge> edges) {
      super(vertices, edges);
    }

    /** Construct a WeightedGraph from vertices 0, 1, and edge array */
    public WeightedGraphAdj(List<WeightedEdge> edges, int numberOfVertices) {
      super(edges, numberOfVertices);
    }

    // Exercise 31.3 Find single source shortest paths with adjacency matrix
    public ShortestPathTree getShortestPath(int sourceVertex,
        Integer[][] adjacencyMatrix) {
      // T stores the vertices whose path found so far
      List<Integer> T = new ArrayList<Integer>();
      // T initially contains the sourceVertex;
      T.add(sourceVertex);

      // vertices is defined in UnweightedGraph
      int numberOfVertices = vertices.size();

      // parent[v] stores the previous vertex of v in the path
      int[] parent = new int[numberOfVertices];
      parent[sourceVertex] = -1; // The parent of source is set to -1

      // cost[v] stores the cost of the path from v to the source
      double[] cost = new double[numberOfVertices];
      for (int i = 0; i < cost.length; i++) {
        cost[i] = Double.MAX_VALUE; // Initial cost set to infinity
      }
      cost[sourceVertex] = 0; // Cost of source is 0

      AdjacencyMatrix am = new AdjacencyMatrix(adjacencyMatrix);

      // Expand T
      while (T.size() < numberOfVertices) {
        int v = -1; // Vertex to be determined
        double smallestCost = Double.MAX_VALUE; // Set to infinity
        for (int u : T) {
          while (!am.isEmpty(u) && T.contains(am.getMin(u))) {
            am.remove(u, am.getMin(u));
          }

          if (am.isEmpty(u)) {
            continue;
          }
          
          int _v = am.getMin(u);
          int w = am.getWeight(u, _v);
          WeightedEdge e = new WeightedEdge(u, _v, w);
          if (cost[u] + e.weight < smallestCost) {
            v = e.v;
            smallestCost = cost[u] + e.weight;
            // If v is added to the tree, u will be its parent
            parent[v] = u;
          }
        } // End of for

        T.add(v); // Add a new vertex to T
        cost[v] = smallestCost;
      } // End of while

      // Create a ShortestPathTree
      return new ShortestPathTree(sourceVertex, parent, T, cost);
    }

    // Exercise 31.3: Inner class that represents an AdjacencyMatrix
    public class AdjacencyMatrix {
      // null in table indicates no edge between nodes
      // -1 in table indicates deleted / removed node
      Integer[][] am;

      public AdjacencyMatrix(Integer[][] adjm) {
        am = new Integer[adjm.length][adjm[0].length];
        for (int i = 0; i < adjm.length; i++)
          for (int j = 0; j < adjm[0].length; j++)
            am[i][j] = adjm[i][j];

      }

      boolean isEmpty(int index) {
        boolean empty = true;
        for (int i = 0; i < am[index].length; i++) {
          if (am[index][i] != null && am[index][i] != -1) {
            empty = false;
            break;
          }

        }
        return empty;
      }

      // find the adjacent node with smallest cost, return the index to that node
      int getMin(int u) {
        int smallest = Integer.MAX_VALUE;
        int indexOfSmallest = 0;
        for (int i = 0; i < am[u].length; i++) {
          if (am[u][i] != null && am[u][i] != -1)
            if (am[u][i] < smallest) {
              smallest = am[u][i];
              indexOfSmallest = i;
            }

        }
        return indexOfSmallest;
      }

      int getWeight(int u, int v) {
        return am[u][v];
      }

      void remove(int u, int v) {
        am[u][v] = -1;
      }

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
  }
}
