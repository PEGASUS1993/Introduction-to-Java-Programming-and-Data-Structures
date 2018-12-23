import java.util.*;

public class Exercise22_03Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of items: ");
    int numberOfItems = input.nextInt();
    
    System.out.print("Enter the weights for each item: ");
    int[] w = new int[numberOfItems];
    for (int i = 0; i < numberOfItems; i++) {
      w[i] = input.nextInt();
    }
    
    System.out.print("Enter the weight limit for the bag: ");
    int weightLimit = input.nextInt();
    
    System.out.println("The maximum weight of the items placed in the bag is " +
      m(numberOfItems, weightLimit, w));
  }
  
  public static ArrayList<Integer> m(int numberOfItems, int weightLimit, int[] w) {
    int result = 0;
    ArrayList<Integer> largestList = new ArrayList<>();
    int[][] m = new int[numberOfItems][weightLimit + 1];
    ArrayList<Integer>[][] list = new ArrayList[numberOfItems][weightLimit + 1];
    for (int j = 0; j <= weightLimit; j++) {
      list[0][j] = new ArrayList<>();
      if (w[0] <= j) {
        m[0][j] = w[0];
        list[0][j].add(0);
      }
    }

    for (int i = 1; i < numberOfItems; i++) 
      for (int j = 0; j <= weightLimit; j++)
        if (w[i] <= j) {
          m[i][j] = Math.max(m[i - 1][j], m[i - 1][j - w[i]] + w[i]);
          if (m[i - 1][j] < m[i - 1][j - w[i]] + w[i]) {
            list[i][j] = new ArrayList<>(list[i - 1][j - w[i]]);
            list[i][j].add(i);     
          }
          else {
            list[i][j] = new ArrayList<>(list[i - 1][j]);
          }
          
          if (result < m[i][j]) {
            result = m[i][j];
            largestList = list[i][j];
          }
        }
        else {
          m[i][j] = m[i - 1][j];
          list[i][j] = new ArrayList<>(list[i - 1][j]);
        }

    return largestList;
  }
}
