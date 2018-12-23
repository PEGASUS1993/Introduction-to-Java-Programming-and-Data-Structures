public class SelectionSort {
  /** Main method */
  public static void main(String[] args) {
    // Initialize the list
    double[] myList = {5.0, 4.4, 1.9, 2.9, 3.4, 3.5};

    // Print the original list
    System.out.println("My list before sort is: ");
    printList(myList);

    // Sort the list
    selectionSort(myList);

    // Print the sorted list
    System.out.println();
    System.out.println("My list after sort is: ");
    printList(myList);
  }

  /** The method for printing numbers */
  static void printList(double[] list) {
    for (int i = 0; i < list.length; i++)
      System.out.print(list[i] + "  ");
    System.out.println();
  }

  /** The method for sorting the numbers */
  static void selectionSort(double[] list) {
    for (int i = list.length - 1; i >= 1; i--) {
      // Find the maximum in the list[0..i]
      double currentMax = list[0];
      int currentMaxIndex = 0;

      for (int j = 1; j <= i; j++) {
        if (currentMax < list[j]) {
          currentMax = list[j];
          currentMaxIndex = j;
        }
      }

      // Swap list[i] with list[currentMaxIndex] if necessary;
      if (currentMaxIndex != i) {
        list[currentMaxIndex] = list[i];
        list[i] = currentMax;
      }
    }
  }
}
