import java.util.Scanner;

public class Exercise18_02Extra {
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
    
    System.out.println("The maximum weight of the items placed in the bag is " +
      m(numberOfItems - 1, weightLimit, w));
  }
  
  public static double m(int i, double weightLimit, double[] w) {
    if (i == -1 || weightLimit <= 0) {
      return 0;
    }
    else if (w[i] > weightLimit) {
      return m(i - 1, weightLimit, w);
    }
    else {
      return Math.max(m(i - 1, weightLimit, w), 
          w[i] + m(i - 1, weightLimit - w[i], w));
    }
  }
}
