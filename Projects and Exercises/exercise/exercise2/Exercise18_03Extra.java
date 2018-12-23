import java.util.*;

public class Exercise18_03Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the number of items: ");
    int numberOfItems = input.nextInt();
    
    System.out.print("Enter the weights for each item: ");
    double[] w = new double[numberOfItems];
    for (int i = 0; i < numberOfItems; i++) {
      w[i] = input.nextDouble();
    }
    
    System.out.print("Enter the weight limit for the bag: ");
    double weightLimit = input.nextDouble();
    
    ArrayList<Integer> bag = m(numberOfItems - 1, weightLimit, w);
    System.out.println("The maximum weight of the items placed in the bag is " +
      bagWeight(bag, w));
    System.out.print("The items in the bag are ");
    for (Integer i: bag) {
      System.out.print(i + 1 + " ");
    }
    System.out.print("\nThe weights of the items in the bag are ");
    for (Integer i: bag) {
      System.out.print(w[i] + " ");
    }
  }
  
  public static ArrayList<Integer> m(int i, double weightLimit, double[] w) {
    if (i == -1 || weightLimit <= 0) {
      return new ArrayList<>();
    }
    else if (w[i] > weightLimit) {
      return m(i - 1, weightLimit, w);
    }
    else {
      ArrayList<Integer> bag1 = m(i - 1, weightLimit, w);
      ArrayList<Integer> bag2 = m(i - 1, weightLimit - w[i], w);
      double weight1 = bagWeight(bag1, w);
      bag2.add(i);
      double weight2 = bagWeight(bag2, w);
      if (weight1 > weight2) {
        return bag1;
      }
      else {
        return bag2;
      }
    }
  }
  
  public static double bagWeight(ArrayList<Integer> bag, double[] w) {
    double total = 0;
    for (Integer i: bag) {
      total += w[i];
    }
    return total;
  }
}
