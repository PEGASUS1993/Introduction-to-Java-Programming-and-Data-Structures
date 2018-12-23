import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

// Note: initialize parent[i] to -1 in the shortest path algorithm in the WeightedGraph class 
// to take care of the case of a disconnected graph. 7/1/2014

public class Exercise29_07 {
  public static void main(String[] args) throws Exception {
    // Prompt the user to enter nine coins' Hs and Ts
    System.out.print("Enter an initial 16 coins' Hs and Ts: ");
    Scanner input = new Scanner(System.in);
    String s = input.nextLine(); 
    char[] initialNode = s.toCharArray();

    Exercise29_06.WeightedTailModel16 model;

    try (ObjectInputStream inputModel = new ObjectInputStream(
        new FileInputStream("WeightedTailModel16.dat"))) {
      model = (Exercise29_06.WeightedTailModel16)(inputModel.readObject());
    }
    
    java.util.List<Integer> path =
      model.getShortestPath(Exercise29_06.TailModel16.getIndex(initialNode));

    if (path == null) {
      System.out.println("No solution");
    }
    else {
      System.out.println("The steps to flip the coins are ");
      for (int i = 0; i < path.size(); i++)
        Exercise29_06.WeightedTailModel16.printNode(Exercise29_06.TailModel16.getNode(path.get(i)));    
  
      System.out.println("The number of flips is " + 
        model.getNumberOfFlips(Exercise29_06.TailModel16.getIndex(initialNode))); 
    }
  }
}
