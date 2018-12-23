import java.util.*;

public class Exercise20_06 {
  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<Integer>();
    
    for (int i = 0; i < 5000000; i++)
      list.add(i);
    
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < list.size(); i++)
      list.get(i);
    long endTime = System.currentTimeMillis();
    System.out.print("Travese time using index is " + (endTime - startTime));
    
    int x;
    startTime = System.currentTimeMillis();
    for (int i: list) {
      x = i;
    }
    endTime = System.currentTimeMillis();
    System.out.print("Travese time using iterator is " + (endTime - startTime));    
    
  }
}
