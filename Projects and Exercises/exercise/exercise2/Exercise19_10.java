import java.util.ArrayList;

public class Exercise19_10 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(14);
    list.add(24);
    list.add(4);
    list.add(42);
    list.add(5);
    
    System.out.print(max(list));
  }

  public static <E extends Comparable<E>> E max(ArrayList<E> list) {
    E currentMax = list.get(0);
    
    for (int i = 1; i < list.size(); i++)
      if (currentMax.compareTo(list.get(i)) < 0)
        currentMax = list.get(i);
    
    return currentMax;
  }
}
