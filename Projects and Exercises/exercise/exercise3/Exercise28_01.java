import java.util.*;

public class Exercise28_01 {
  public static void main(String[] args) throws Exception {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a URL: ");
    java.net.URL url = new java.net.URL(input.nextLine());  
    // "http://liveexample.pearsoncmg.com/test/GraphSample1.txt"
    java.util.Scanner inFile = new java.util.Scanner(url.openStream());
    
    // Read the number of vertices
    String s = inFile.nextLine();
    int numberOfVertices = Integer.parseInt(s);
    System.out.println("The number of vertices is " 
      + numberOfVertices);
    
    java.util.List<Edge> list = new java.util.ArrayList<>();
    
    while (inFile.hasNext()) {
      s = inFile.nextLine();
      
      String[] tokens = s.split("[\\s+]");
      
      int startingVertex = Integer.parseInt(tokens[0].trim());
      for (int i = 1; i < tokens.length; i++) {
        int adjacentVertex = Integer.parseInt(tokens[i].trim());
        list.add(new Edge(startingVertex, adjacentVertex));
      }
    }
    
    Graph<Integer> graph = new UnweightedGraph<>(list, numberOfVertices);
    
    graph.printEdges();
    UnweightedGraph<Integer>.SearchTree tree = graph.dfs(0);
    if (tree.getNumberOfVerticesFound() == numberOfVertices)
      System.out.println("The graph is connected");
    else
      System.out.println("The graph is not connected");
  }
}

/*
public class Exercise28_01 {
  public static void main(String[] args) throws Exception {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a file name: ");
    java.io.File file = new java.io.File(input.nextLine());
   
    if (!file.exists()) {
      System.out.println("File does not exist");
      System.exit(1);
    }
    
    java.util.Scanner inFile = new java.util.Scanner(file);
    
    // Read the number of vertices
    String s = inFile.nextLine();
    int numberOfVertices = Integer.parseInt(s);
    System.out.println("The number of vertices is " 
      + numberOfVertices);
    
    java.util.List<Edge> list = new java.util.ArrayList<>();
    
    while (inFile.hasNext()) {
      s = inFile.nextLine();
      
      String[] tokens = s.split("[\\s+]");
      
      int startingVertex = Integer.parseInt(tokens[0].trim());
      for (int i = 1; i < tokens.length; i++) {
        int adjacentVertex = Integer.parseInt(tokens[i].trim());
        list.add(new Edge(startingVertex, adjacentVertex));
      }
    }
    
    Graph<Integer> graph = new UnweightedGraph<>(list, numberOfVertices);
    
    graph.printEdges();
    UnweightedGraph<Integer>.SearchTree tree = graph.dfs(0);
    if (tree.getNumberOfVerticesFound() == numberOfVertices)
      System.out.println("The graph is connected");
    else
      System.out.println("The graph is not connected");
  }
} */
