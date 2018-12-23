public class Exercise23_13 {
  public static void main(String[] args) {
    System.out.printf("%10s%10s%10s%10s%10s%10s%10s%10s\n", "Array", "Selection", "Insertion", 
        "Bubble", "Merge", "Quickt", "Heap", "Radix");
    System.out.printf("%10s%10s%10s%10s%10s%10s%10s%10s\n", "Size", "Sort", "Sort", 
        "Sort", "Sort", "Sort", "Sort", "Sort");
    
    int[] list;
    int[] list1;
    Integer[] list2;
    long startTime;
    long endTime;
    long[][] executionTime = new long[6][7];

    final int BASE = 10000;
    for (int k = 0; k < 6; k++) {
      list = new int[(k + 1) * BASE];
      list1 = new int[(k + 1) * BASE];
      list2 = new Integer[(k + 1) * BASE];
      for (int i = 0; i < list.length; i++) {
        list[i] = (int)(Math.random() * 100000);
        list1[i] = list[i];
        list2[i] = list[i];
      }

      startTime = System.nanoTime();
      selectionSort(list);
      endTime = System.nanoTime();
      executionTime[k][0] = endTime - startTime;

      startTime = System.nanoTime();
      copyList(list1, list);
      insertionSort(list);
      endTime = System.nanoTime();
      executionTime[k][1] = endTime - startTime;

      startTime = System.nanoTime();
      copyList(list1, list);
      bubbleSort(list);
      endTime = System.nanoTime();
      executionTime[k][2] = endTime - startTime;

      startTime = System.nanoTime();
      copyList(list1, list);
      mergeSort(list);
      endTime = System.nanoTime();
      executionTime[k][3] = endTime - startTime;

      startTime = System.nanoTime();
      copyList(list1, list);
      quickSort(list);
      endTime = System.nanoTime();
      executionTime[k][4] = endTime - startTime;

      startTime = System.nanoTime();
      heapSort(list2);
      endTime = System.nanoTime();
      executionTime[k][5] = endTime - startTime;
      
      startTime = System.nanoTime();
      copyList(list1, list);
      radixSort(list, 5);
      endTime = System.nanoTime();
      executionTime[k][6] = endTime - startTime;
    }

		for (int i = 0; i < 6; i++) {
		  System.out.printf("%10d", BASE + i * BASE);
			for (int j = 0; j < 7; j++)
				System.out.printf("%10d", executionTime[i][j]);
			System.out.println();
    }
  }

  /** The method for sorting the numbers */
  public static void selectionSort(int[] list) {
    for (int i = list.length - 1; i >= 1; i--) {
      // Find the maximum in the list[0..i]
      int currentMax = list[0];
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

  /** The method for sorting the numbers */
  public static void insertionSort(int[] list) {
    for (int i = 1; i < list.length; i++) {
      /** insert list[i] into a sorted sublist list[0..i-1] so that
         list[0..i] is sorted. */
      int currentElement = list[i];
      int k;
      for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
        list[k + 1] = list[k];
      }

      // Insert the current element into list[k+1]
      list[k + 1] = currentElement;
    }
  }

  /** The method for sorting the numbers */
  public static void bubbleSort(int[] list) {
    boolean needNextPass = true;

    for (int k = 1; k < list.length && needNextPass; k++) {
      // Array may be sorted and next pass not needed
      needNextPass = false;
      for (int i = 0; i < list.length - k; i++) {
        if (list[i] > list[i + 1]) {
          // Swap list[i] with list[i + 1]
          int temp = list[i];
          list[i] = list[i + 1];
          list[i + 1] = temp;

          needNextPass = true; // Next pass still needed
        }
      }
    }
  }

  /** The method for sorting the numbers */
  public static void mergeSort(int[] list) {
    if (list.length > 1) {
      // Merge sort the first half
      int[] firstHalf = new int[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      mergeSort(firstHalf);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      int[] secondHalf = new int[secondHalfLength];
      System.arraycopy(list, list.length / 2,
        secondHalf, 0, secondHalfLength);
      mergeSort(secondHalf);

      // Merge firstHalf with secondHalf
      int[] temp = merge(firstHalf, secondHalf);
      System.arraycopy(temp, 0, list, 0, temp.length);
    }
  }

  private static int[] merge(int[] list1, int[] list2) {
    int[] temp = new int[list1.length + list2.length];

    int current1 = 0; // Index in list1
    int current2 = 0; // Index in list2
    int current3 = 0; // Index in temp

    while (current1 < list1.length && current2 < list2.length) {
      if (list1[current1] < list2[current2]) {
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

  public static void quickSort(int[] list) {
    quickSort(list, 0, list.length - 1);
  }

  private static void quickSort(int[] list, int first, int last) {
    if (last > first) {
      int pivotIndex = partition(list, first, last);
      quickSort(list, first, pivotIndex - 1);
      quickSort(list, pivotIndex + 1, last);
    }
  }

  /** Partition the array list[first..last] */
  private static int partition(int[] list, int first, int last) {
    int pivot = list[first]; // Choose the first element as the pivot
    int low = first + 1; // Index for forward search
    int high = last; // Index for backward search

    while (high > low) {
      // Search forward from left
      while (low <= high && list[low] <= pivot) {
        low++;
      }

      // Search backward from right
      while (low <= high && list[high] > pivot) {
        high--;
      }

      // Swap two elements in the list
      if (high > low) {
        int temp = list[high];
        list[high] = list[low];
        list[low] = temp;
      }
    }

    while (high > first && list[high] >= pivot) {
      high--;
    }

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

  public static void heapSort(Comparable[] list) {
    Heap heap = new Heap(); // Create a Heap

    // Add elements to the heap
    for (int i = 0; i < list.length; i++) {
      heap.add(list[i]);
    }

    // Remove elements from the heap
    for (int i = list.length - 1; i >= 0; i--) {
      list[i] = heap.remove();
    }
  }
  
  /** Sort the int array list. numberOfDigits is the number of digits
   * in the largest number in the array */
  public static void radixSort(int[] list, int numberOfDigits) {
    java.util.ArrayList<Integer>[] buckets = new java.util.ArrayList[10];
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new java.util.ArrayList<Integer>();
    }

    for (int position = 0; position <= numberOfDigits; position++) {
      // Clear buckets
      for (int i = 0; i < buckets.length; i++) {
        buckets[i].clear();
      }      
      
      // Distribute the elements from list to buckets
      for (int i = 0; i < list.length; i++) {
        int key = getKey(list[i], position);
        buckets[key].add(list[i]);
      }

      // Now move the elements from the buckets back to list
      int k = 0; // k is an index for list
      for (int i = 0; i < buckets.length; i++) {
        if (buckets[i] != null) {
          for (int j = 0; j < buckets[i].size(); j++)
            list[k++] = buckets[i].get(j);
        }
      }
    }
  }

  /** Return the digit at the specified position. 
   * The last digit's position is 0. */
  public static int getKey(int number, int position) {
    int result = 1;
    for (int i = 0; i < position; i++)
      result *= 10;

    return (number / result) % 10;
  }
  
  public static void copyList(int[] list1, int[] list) {
    for (int i = 0; i < list1.length; i++)
      list[i] = list1[i];
  }
}
