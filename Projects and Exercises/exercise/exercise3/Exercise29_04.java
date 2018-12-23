import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise29_04 {
  public static void main(String[] args) {
    new Exercise29_04();
  }

  public Exercise29_04() {
    // Prompt the user to enter nine coins H’s and T's
    System.out.print("Enter an initial nine coin H’s and T's: ");
    Scanner input = new Scanner(System.in);
    String s = input.nextLine(); 
    char[] initialNode = s.toCharArray();

    ModifiedWeightedNineTailModel model = new ModifiedWeightedNineTailModel();
    java.util.List<Integer> path =
      model.getShortestPath(NineTailModel.getIndex(initialNode));

    System.out.println("The steps to flip the coins are ");
    for (int i = 0; i < path.size(); i++)
      NineTailModel.printNode(
        NineTailModel.getNode(path.get(i).intValue()));    

    System.out.println("The number of flips is " + 
      model.getNumberOfFlips(NineTailModel.getIndex(initialNode)));    
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
