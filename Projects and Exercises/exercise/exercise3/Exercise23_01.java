import java.util.Comparator;

public class Exercise23_01 {
	/** The method for sorting the numbers */
	public static <E extends Comparable<E>> void bubbleSort(E[] list) {
	  bubbleSort(list, (e1, e2) -> ((Comparable<E>)e1).compareTo(e2));
	}

	public static <E> void bubbleSort(E[] list,
	    Comparator<? super E> comparator) {
		boolean needNextPass = true;

		for (int k = 1; k < list.length && needNextPass; k++) {
			// Array may be sorted and next pass not needed
			needNextPass = false;
			for (int i = 0; i < list.length - k; i++) {
				if (comparator.compare(list[i], list[i + 1]) > 0) {
					// Swap list[i] with list[i + 1]
					E temp = list[i];
					list[i] = list[i + 1];
					list[i + 1] = temp;

					needNextPass = true; // Next pass still needed
				}
			}
		}
	}

	public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    bubbleSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }

    System.out.println();
    String[] list1 = {"ABC", "abc", "abm", "Anf", "Good", "Bad", "nice"};
    bubbleSort(list1, (s1, s2) -> s1.compareToIgnoreCase(s2));
    for (int i = 0; i < list1.length; i++) {
      System.out.print(list1[i] + " ");
    }
	}
}
