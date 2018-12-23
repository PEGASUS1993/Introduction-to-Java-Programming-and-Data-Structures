import java.util.Comparator;

public class Exercise23_02 {
  /** The method for sorting the numbers */
  public static <E extends Comparable<E>> void mergeSort(E[] list) {
    mergeSort(list, (e1, e2) -> ((Comparable<E>)e1).compareTo(e2));  
  }

  public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    mergeSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }

    System.out.println();
    String[] list1 = {"ABC", "abc", "abm", "Anf", "Good", "Bad", "nice"};
    mergeSort(list1, (s1, s2) -> s1.compareToIgnoreCase(s2));
    for (int i = 0; i < list1.length; i++) {
      System.out.print(list1[i] + " ");
    }
  }

  public static <E> void mergeSort(E[] list,
	    Comparator<? super E> comparator) {
    if (list.length > 1) {
      // Merge sort the first half
      E[] firstHalf = (E[])new Object[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      mergeSort(firstHalf, comparator);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      E[] secondHalf = (E[])new Object[secondHalfLength];
      System.arraycopy(list, list.length / 2,
                       secondHalf, 0, secondHalfLength);
      mergeSort(secondHalf, comparator);

      // Merge firstHalf with secondHalf
      E[] temp = merge1(firstHalf, secondHalf, comparator);
      System.arraycopy(temp, 0, list, 0, temp.length);
    }
  }

  private static<E> E[]
    merge1(E[] list1, E[] list2, Comparator<? super E> comparator) {
    E[] temp = (E[])new Object[list1.length + list2.length];

    int current1 = 0; // Index in list1
    int current2 = 0; // Index in list2
    int current3 = 0; // Index in temp

    while (current1 < list1.length && current2 < list2.length) {
      if (comparator.compare(list1[current1], list2[current2]) < 0) {
        temp[current3++] = list1[current1++];
      }
      else {
        temp[current3++] = list2[current2++];
      }
    }

    while (current1 < list1.length) {
      temp[current3++] = list1[current1++];
    }

    while (current2 < list2.length) {
      temp[current3++] = list2[current2++];
    }

    return temp;
  }
}
