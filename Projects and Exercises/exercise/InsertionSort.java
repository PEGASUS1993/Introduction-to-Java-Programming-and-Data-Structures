public class InsertionSort {
  /** Main method */
  public static void main(String[] args) {
    // Initialize the list
    double[] myList = {5.0, 4.4, 1.9, 2.9, 3.4, 3.5};

    // Print the original list
    System.out.println("My list before sort is: ");
    printList(myList);

    // Sort the list
    insertionSort(myList);

    // Print the sorted list
    System.out.println();
    System.out.println("My list after sort is: ");
    printList(myList);
  }

  /** The method for printing numbers */
  static void printList(double[] list) {
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + "  ");
    }
    System.out.println();
  }

  /** The method for sorting the numbers */
  public static void insertionSort(double[] list) {
    for (int i = 1; i < list.length; i++) {
      /** insert list[i] into a sorted sublist list[0..i-1] so that
           list[0..i] is sorted. */
      double currentElement = list[i];
      int k;
      for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
        list[k + 1] = list[k];
      }

      // Insert the current element into list[k+1]
      list[k + 1] = currentElement;
    }
  }
}
