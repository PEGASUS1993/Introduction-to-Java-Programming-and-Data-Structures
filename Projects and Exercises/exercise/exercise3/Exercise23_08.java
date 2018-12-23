import java.util.Comparator;

public class Exercise23_08 {
  public static<E extends Comparable<E>>void insertionSort(E[] list) {
    insertionSort(list, (e1, e2) -> ((Comparable<E>)e1).compareTo(e2));
  }

  public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    insertionSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }

    System.out.println();
    String[] list1 = {"ABC", "abc", "abm", "Anf", "Good", "Bad", "nice"};
    insertionSort(list1, (s1, s2) -> s1.compareToIgnoreCase(s2));
    for (int i = 0; i < list1.length; i++) {
      System.out.print(list1[i] + " ");
    }
  }

  public static<E>void insertionSort(E[] list,
      Comparator<? super E> comparator) {
    insertionSort(list, 0, list.length - 1, comparator);
  }

  private static<E>void insertionSort(E[] list, int first, int last,
      Comparator<? super E> comparator) {
    for (int i = 1; i < list.length; i++) {
      /** insert list[i] into a sorted sublist list[0..i-1] so that
           list[0..i] is sorted. */
      E currentElement = list[i];
      int k;
      for (k = i - 1; k >= 0 && comparator.compare(list[k], currentElement) > 0; k--) {
        list[k + 1] = list[k];
      }

      // Insert the current element into list[k+1]
      list[k + 1] = currentElement;
    }
  }
}
