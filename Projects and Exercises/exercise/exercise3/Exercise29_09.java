public class Exercise29_09 {
  public static void main(String[] args) throws Exception {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a URL: ");
    java.net.URL url = new java.net.URL(input.nextLine());  
    // "https://liveexample.pearsoncmg.com/test/WeightedGraphSample1.txt"
    java.util.Scanner inFile = new java.util.Scanner(url.openStream());
    
    // Read the number of vertices
    String s = inFile.nextLine();
    int numberOfVertices = Integer.parseInt(s);
    System.out.println("The number of vertices is " + numberOfVertices);
    
    java.util.List<WeightedEdge> list = 
      new java.util.ArrayList<>();
    
    while (inFile.hasNext()) {
      s = inFile.nextLine();   
      String[] triplets = s.split("[\\|]"); // Get the triplets
      
      for (String triplet: triplets) {
        String[] tokens = triplet.split("[,]");
        int u = Integer.parseInt(tokens[0].trim());
        int v = Integer.parseInt(tokens[1].trim());
        double w = Double.parseDouble(tokens[2].trim());
        
        list.add(new WeightedEdge(u, v, w));
        list.add(new WeightedEdge(v, u, w));
      }
    }
    
    WeightedGraph<Integer> graph = new WeightedGraph<>(list, numberOfVertices);
    
    graph.printWeightedEdges();
    WeightedGraph<Integer>.MST tree = graph.getMinimumSpanningTree();
    System.out.println("Total weight in MST is " + tree.getTotalWeight());
    tree.printTree();
  }
}
