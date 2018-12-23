import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Exercise24_01Extra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter 10 numbers: ");
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(input.nextInt());
    }
    reverse(list);
    for (int i: list) {
      System.out.print(i + " ");
    }
    System.out.println();
    
    LinkedList<Integer> list1 = new LinkedList<>(list);
    reverse(list1);
    for (int i: list1) {
      System.out.print(i + " ");
    }
    System.out.println();    
  }

  // Reverse the list and return it in O(n) time
  public static <E> ArrayList<E> reverse(ArrayList<E> list) {
    for (int i = 0, j = list.size() - 1; i < list.size() / 2; i++, j--) {
      E temp = list.get(i);
      list.set(i,  list.get(j));
      list.set(j, temp);
    }
    
    return list;
  }
  
  // Reverse the list and return it in O(n) time
  public static <E> LinkedList<E> reverse(LinkedList<E> list) {
    LinkedList<E> result = new LinkedList<>();
    int size = list.size();
    for (int i = 0; i < size; i++) {
      result.add(list.removeLast());
    }

    for (E e: result) {
      list.add(e);
    }
    
    return list;
  }
}

