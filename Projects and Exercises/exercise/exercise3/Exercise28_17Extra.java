import java.util.List;

public class Exercise28_17Extra {
  public static void main(String[] args) {
    String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
      "Denver", "Kansas City", "Chicago", "Boston", "New York",
      "Atlanta", "Miami", "Dallas", "Houston"};

    // Edge array for graph in Figure 28.1
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

    UnweightedGraphWithVertexRemoval<String> graph1 = 
        new UnweightedGraphWithVertexRemoval<>(vertices, edges);
    graph1.removeVertex("Seattle");
    System.out.println("The number of vertices in graph1: " 
      + graph1.getSize());
    System.out.println("The vertex with index 1 is " 
      + graph1.getVertex(1));
    System.out.println("The index for Miami is " + 
      graph1.getIndex("Miami"));
    System.out.println("The edges for graph1:");
    graph1.printEdges();
  }
}

class UnweightedGraphWithVertexRemoval<V> extends UnweightedGraph<V> {
  /** Construct an empty graph */
  public UnweightedGraphWithVertexRemoval() {
  }
    
  /** Construct a graph from vertices and edges stored in arrays */
  public UnweightedGraphWithVertexRemoval(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }

  /** Construct a graph from vertices and edges stored in List */
  public UnweightedGraphWithVertexRemoval(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraphWithVertexRemoval(List<Edge> edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraphWithVertexRemoval(int[][] edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  public boolean removeVertex(V v) {
    if (vertices.contains(v)) {
      int index = this.getIndex(v);
      vertices.remove(v);
      neighbors.remove(index);
      
      // Remove the edges adjacent to v
      for (java.util.List<Edge> list: this.neighbors) {
        for (int i = 0; i < list.size(); ) {
          if (list.get(i).v == index) {
            list.remove(i);
          }
          else {
            i++;
          }
        }
      }
      
      // Reassign labels to vertices after index
      for (java.util.List<Edge> list: this.neighbors) {
        for (int i = 0; i < list.size(); i++) {
          if (list.get(i).u >= index) {
            list.get(i).u = list.get(i).u - 1;
          }
          if (list.get(i).v >= index) {
            list.get(i).v = list.get(i).v - 1;
          }
        }
      }      
      
      return true;
    }
    else {
      return false;
    }
  }
}
