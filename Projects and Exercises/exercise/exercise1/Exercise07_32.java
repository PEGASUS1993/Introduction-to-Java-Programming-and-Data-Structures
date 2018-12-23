public class Exercise07_32 {
  public static void main (String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter values for list1
    System.out.print("Enter the list size: ");
    int size = input.nextInt();
    int[] list = new int[size];    
    System.out.print("Enter the list content: ");
    for (int i = 0; i < list.length; i++) 
      list[i] = input.nextInt();

    partition(list);
    
    System.out.print("After the partition, the list is ");
    for (int i = 0; i < list.length; i++)
      System.out.print(list[i] + " ");
  }

  /** Partition the array list[first..last] */
  public static int partition(int[] list) {
  	int first = 0;
  	int last = list.length - 1;
    int pivot = list[first]; // Choose the first element as the pivot
    int low = first + 1; // Index for forward search
    int high = last; // Index for backward search

    while (high > low) {
      // Search forward from left
      while (low <= high && list[low] <= pivot)
        low++;

      // Search backward from right
      while (low <= high && list[high] > pivot)
        high--;

      // Swap two elements in the list
      if (high > low) {
        int temp = list[high];
        list[high] = list[low];
        list[low] = temp;
      }
    }

    while (high > first && list[high] >= pivot)
      high--;

    // Swap pivot with list[high]
    if (pivot > list[high]) {
      list[first] = list[high];
      list[high] = pivot;
      return high;
    }
    else {
      return first;
    }
  }
}
