import java.util.ArrayList;
import java.util.List;

public class Exercise29_05 {
  public static void main(String[] args) {
    NineTailModel model1 = new NineTailModel();
    WeightedNineTailModel model2 = new WeightedNineTailModel();

    UnweightedGraph.SearchTree tree1 = model1.tree;
    UnweightedGraph.SearchTree tree2 = model2.tree;

    for (int i = 0; i < 511; i++) {
      // System.out.println(tree1.depth(i));
      if (depth(tree1, i) != depth(tree2, i))
        System.out.println(i);
    }
    
    System.out.println("Finished");
  }
  
  private static int depth(UnweightedGraph.SearchTree tree, int v) {
  	return tree.getPath(v).size();
  }
  
  public static class ModifiedWeightedNineTailModel extends NineTailModel {
    /** Construct a model */
    public ModifiedWeightedNineTailModel() {
      // Create edges
      List<WeightedEdge> edges = getEdges();
      
      // Create a graph
      WeightedGraph<Integer> graph = new WeightedGraph<Integer>(
        edges, NUMBER_OF_NODES); 

      // Obtain a BSF tree rooted at the target node
      tree = graph.getShortestPath(511);
    }

    /** Create all edges for the graph */
    private List<WeightedEdge> getEdges() {
      // Store edges
      List<WeightedEdge> edges = new ArrayList<WeightedEdge>(); 

      for (int u = 0; u < NUMBER_OF_NODES; u++) {
        for (int k = 0; k < 9; k++) {
          char[] node = getNode(u); // Get the node for vertex u
          if (node[k] == 'H') {
            int v = getFlippedNode(node, k);
            int numberOfFlips = getNumberOfFlips(u, v);
            
            // Add edge (v, u) for a legal move from node u to node v
            edges.add(new WeightedEdge(v, u, numberOfFlips));
          }
        }
      }

      return edges;
    }
    
    private static int getNumberOfFlips(int u, int v) {
      char[] node1 = getNode(u);
      char[] node2 = getNode(v);

      int count = 0; // Count the number of different cells
      for (int i = 0; i < node1.length; i++)
        if (node1[i] != node2[i]) count++;

      return 3 * count;
    }

    public int getNumberOfFlips(int u) {
      return (int)((WeightedGraph<Integer>.ShortestPathTree)tree)
        .getCost(u);
    }
  }
}
