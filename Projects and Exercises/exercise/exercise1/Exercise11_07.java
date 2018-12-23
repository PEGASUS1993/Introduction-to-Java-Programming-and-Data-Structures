import java.util.ArrayList;

public class Exercise11_07 {
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    
    shuffle(list);
    
    for (int i = 0; i < list.size(); i++) {
      System.out.print(list.get(i) + " ");
    }
  }
  
  public static void shuffle(ArrayList<Integer> list) {
    for (int i = 0; i < list.size(); i++) {
      int index = (int)(Math.random() * 10);
      int temp = list.get(index);
      list.set(index, list.get(i));
      list.set(i, temp);
    }
  }
}
