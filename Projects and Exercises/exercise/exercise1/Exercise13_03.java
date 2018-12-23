import java.util.*;

public class Exercise13_03 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    ArrayList<Number> list = new ArrayList<Number>();
    System.out.print("Enter five integers and five doubles: ");
    for (int i = 0; i < 5; i++) {
      list.add(input.nextInt());
    }
    
    for (int i = 0; i < 5; i++) {
      list.add(input.nextDouble());
    }
    
    sort(list);
    
    for (int i = 0; i < list.size(); i++) 
      System.out.print(list.get(i) + " ");
  }

  public static void sort(ArrayList<Number> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      // Find the minimum in the list[i..list.length-1]
      Number currentMin = list.get(i);
      int currentMinIndex = i;

      for (int j = i + 1; j < list.size(); j++) {
        if (currentMin.doubleValue() > list.get(j).doubleValue()) {
          currentMin = list.get(j);
          currentMinIndex = j;
        }
      }

      // Swap list.get(i) with list.get(currentMinIndex) if necessary;
      if (currentMinIndex != i) {
        list.set(currentMinIndex, list.get(i));
        list.set(i, currentMin);
      }
    }
  }
}
