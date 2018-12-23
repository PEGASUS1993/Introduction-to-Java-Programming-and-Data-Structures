public class Exercise28_02 {
  public static void main(String[] args) throws java.io.FileNotFoundException {
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

    java.io.PrintWriter output = new java.io.PrintWriter("Exercise28_02.txt");
    
    int numberOfVertices = vertices.length;
    output.println(numberOfVertices);
    for (int startingVertex = 0; startingVertex < numberOfVertices; startingVertex++) {
      output.print(startingVertex + " ");
      int count = 0;
      for (int i = 0; i < edges.length; i++) {      
        if (edges[i][0] == startingVertex) {
          count++;
          if (count == 1)
            output.print(edges[i][1]);
          else
            output.print(" " + edges[i][1]);
        }
      }
      if (count > 0) output.println();
    }
      
    System.out.println("Done!");
    output.close();
  }
}
