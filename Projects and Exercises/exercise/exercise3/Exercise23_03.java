import java.util.Comparator;

public class Exercise23_03 {
  public static<E extends Comparable<E>>void quickSort(E[] list) {
    quickSort(list, (e1, e2) -> ((Comparable<E>)e1).compareTo(e2));
  }

  public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    quickSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }

    System.out.println();
    String[] list1 = {"ABC", "abc", "abm", "Anf", "Good", "Bad", "nice"};
    quickSort(list1, (s1, s2) -> s1.compareToIgnoreCase(s2));
    for (int i = 0; i < list1.length; i++) {
      System.out.print(list1[i] + " ");
    }
  }

  public static<E>void quickSort(E[] list,
	    Comparator<? super E> comparator) {
    quickSort(list, 0, list.length - 1, comparator);
  }

  private static<E>void quickSort(E[] list, int first, int last,
	    Comparator<? super E> comparator) {
    if (last > first) {
      int pivotIndex = partition(list, first, last, comparator);
      quickSort(list, first, pivotIndex - 1, comparator);
      quickSort(list, pivotIndex + 1, last, comparator);
    }
  }

  /** Partition the array list[first..last] */
  private static <E> int partition(E[] list, int first, int last,
	    Comparator<? super E> comparator) {
    E pivot = list[first]; // Choose the first element as the pivot
    int low = first + 1; // Index for forward search
    int high = last; // Index for backward search

    while (high > low) {
      // Search forward from left
      while (low <= high && comparator.compare(list[low], pivot) <= 0) {
        low++;
      }

      // Search backward from right
      while (low <= high && comparator.compare(list[high], pivot) > 0) {
        high--;
      }

      // Swap two elements in the list
      if (high > low) {
        E temp = list[high];
        list[high] = list[low];
        list[low] = temp;
      }
    }

    while (high > first && comparator.compare(list[high], pivot) >= 0) {
      high--;
    }

    // Swap pivot with list[high]
    if (comparator.compare(pivot, list[high]) > 0) {
      list[first] = list[high];
      list[high] = pivot;
      return high;
    }
    else {
      return first;
    }
  }
}
