import java.util.ArrayList;

public class Exercise23_01Extra {
  public static final int SIZE = 100000;
  
  private static void display(int[] list, int limit) {
    for (int i = 0; i < limit; i++)
      System.out.print(list[i] + " ");
    System.out.println();
  }
  
  public static void main(String[] args) {
    int list[] = new int[SIZE];

    // Base 10 radix with array
    for (int i = 0; i < SIZE; i++) {
      list[i] = (int) (Math.random() * 2147483647);
    }

    long startTime = System.currentTimeMillis();
    base10ArrayRadix(list);
    long estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Base 10 Radix with array: " + estimatedTime + "ms");
    display(list, 10); // Display first 10 numbers

    // Binary radix with array
    for (int i = 0; i < SIZE; i++) {
      list[i] = (int) (Math.random() * 2147483647);
    }

    startTime = System.currentTimeMillis();
    binaryArrayRadix(list);
    estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Binary Radix with array: " + estimatedTime + "ms");
    display(list, 10); // Display first 10 numbers

    // Binary Radix with arrayList
    for (int i = 0; i < SIZE; i++) {
      list[i] = (int) (Math.random() * 2147483647);
    }

    startTime = System.currentTimeMillis();
    binaryWithArrayList(list);
    estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Binary Radix with ArrayList: " + estimatedTime + "ms");
    display(list, 10); // Display first 10 numbers

    // Heap Sort
    Integer listForHeap[] = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      listForHeap[i] = (int) (Math.random() * 2147483647);
    }

    startTime = System.currentTimeMillis();
    heapSort(listForHeap);
    estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Heap Sort: " + estimatedTime + "ms");
    display(list, 10); // Display first 10 numbers

    // Quick Sort
    for (int i = 0; i < SIZE; i++) {
      list[i] = (int) (Math.random() * 2147483647);
    }

    startTime = System.currentTimeMillis();
    quickSort(list);
    estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Quick Sort: " + estimatedTime + "ms");
    display(list, 10); // Display first 10 numbers

    // Merge Sort
    for (int i = 0; i < SIZE; i++) {
      list[i] = (int) (Math.random() * 2147483647);
    }

    startTime = System.currentTimeMillis();
    mergeSort(list);
    estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Merge Sort: " + estimatedTime + "ms");
    display(list, 10); // Display first 10 numbers

    // Insertion Sort
    for (int i = 0; i < SIZE; i++) {
      list[i] = (int) (Math.random() * 2147483647);
    }

    startTime = System.currentTimeMillis();
    insertionSort(list);
    estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Insertion Sort: " + estimatedTime + "ms");
    display(list, 10); // Display first 10 numbers

    // Selection Sort
    for (int i = 0; i < SIZE; i++) {
      list[i] = (int) (Math.random() * 2147483647);
    }

    startTime = System.currentTimeMillis();
    selectionSort(list);
    estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Selection Sort: " + estimatedTime + "ms");
    display(list, 10); // Display first 10 numbers

    // Bubble sort
    for (int i = 0; i < SIZE; i++) {
      list[i] = (int) (Math.random() * 2147483647);
    }
    startTime = System.currentTimeMillis();
    bubbleSort(list);
    estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Bubble Sort: " + estimatedTime + "ms");
    display(list, 10); // Display first 10 numbers
  }

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

  public static void insertionSort(int[] list) {
    for (int i = 1; i < list.length; i++) {
      /**
       * insert list[i] into a sorted sublist list[0..i-1] so that list[0..i] is
       * sorted.
       */
      int currentElement = list[i];
      int k;
      for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
        list[k + 1] = list[k];
      }

      // Insert the current element into list[k+1]
      list[k + 1] = currentElement;
    }
  }

  public static void mergeSort(int[] list) {
    if (list.length > 1) {
      // Merge sort the first half
      int[] firstHalf = new int[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      mergeSort(firstHalf);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      int[] secondHalf = new int[secondHalfLength];
      System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
      mergeSort(secondHalf);

      // Merge firstHalf with secondHalf into list
      merge(firstHalf, secondHalf, list);
    }
  }

  /**
   * Merge two sorted lists
   */
  public static void merge(int[] list1, int[] list2, int[] temp) {
    int current1 = 0; // Current index in list1
    int current2 = 0; // Current index in list2
    int current3 = 0; // Current index in temp

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

  /**
   * Partition the array list[first..last]
   */
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

  public static void selectionSort(int[] list) {
    for (int i = 0; i < list.length - 1; i++) {
      // Find the minimum in the list[i..list.length-1]
      int currentMin = list[i];
      int currentMinIndex = i;

      for (int j = i + 1; j < list.length; j++) {
        if (currentMin > list[j]) {
          currentMin = list[j];
          currentMinIndex = j;
        }
      }

      // Swap list[i] with list[currentMinIndex] if necessary;
      if (currentMinIndex != i) {
        list[currentMinIndex] = list[i];
        list[i] = currentMin;
      }
    }
  }

  public static <E extends Comparable<E>> void heapSort(E[] list) {
    // Create a Heap of integers
    Heap<E> heap = new Heap<>();

    // Add elements to the heap
    for (int i = 0; i < list.length; i++) {
      heap.add(list[i]);
    }

    // Remove elements from the heap
    for (int i = list.length - 1; i >= 0; i--) {
      list[i] = heap.remove();
    }
  }

  public static void binaryArrayRadix(int[] array) {

    int list[][] = new int[2][SIZE];
    int s = 1;
    for (int i = 0; i < 32; i++) {
      int listCounter[] = new int[SIZE];
      for (int j = 0; j < array.length; j++) {
        int bucket = (array[j] & s) >> i;
        list[bucket][listCounter[bucket]++] = array[j];
      }
      s = s << 1;
      int pos = 0;
      for (int q = 0; q < 10; q++) {
        for (int p = 0; p < listCounter[q]; p++) {
          array[pos++] = list[q][p];
        }
      }

    }

  }

  public static void base10ArrayRadix(int[] array) {

    int list[][] = new int[10][SIZE];
    int mod = 10;
    int dev = 1;
    for (int i = 0; i < 10; i++, dev *= 10, mod *= 10) {
      int listCounter[] = new int[10];

      for (int j = 0; j < array.length; j++) {
        int bucket = (array[j] % mod) / dev;

        list[bucket][listCounter[bucket]++] = array[j];
      }

      int pos = 0;
      for (int q = 0; q < 10; q++) {
        for (int p = 0; p < listCounter[q]; p++) {
          array[pos++] = list[q][p];
        }
      }

    }

  }

  public static void binaryWithArrayList(int[] array) {

    ArrayList<Integer>[] counter = new ArrayList[] { new ArrayList<Integer>(),
        new ArrayList<Integer>(), new ArrayList<Integer>(),
        new ArrayList<Integer>(), new ArrayList<Integer>(),
        new ArrayList<Integer>(), new ArrayList<Integer>(),
        new ArrayList<Integer>(), new ArrayList<Integer>(),
        new ArrayList<Integer>() };

    int s = 1;
    for (int i = 0; i < 32; i++) {
      for (int j = 0; j < array.length; j++) {
        int bucket = (array[j] & s) >> i;
        counter[bucket].add(array[j]);
      }
      s = s << 1;
      int pos = 0;
      for (int j = 0; j < counter.length; j++) {

        while (!counter[j].isEmpty()) {
          array[pos++] = counter[j].remove(0);
        }
      }
    }

  }

}

class Heap<E extends Comparable<E>> {

  private java.util.ArrayList<E> list = new java.util.ArrayList<>();

  /**
   * Create a default heap
   */
  public Heap() {
  }

  /**
   * Create a heap from an array of objects
   */
  public Heap(E[] objects) {
    for (int i = 0; i < objects.length; i++) {
      add(objects[i]);
    }
  }

  /**
   * Add a new object into the heap
   */
  public void add(E newObject) {
    list.add(newObject); // Append to the heap
    int currentIndex = list.size() - 1; // The index of the last node

    while (currentIndex > 0) {
      int parentIndex = (currentIndex - 1) / 2;
      // Swap if the current object is greater than its parent
      if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
        E temp = list.get(currentIndex);
        list.set(currentIndex, list.get(parentIndex));
        list.set(parentIndex, temp);
      }
      else {
        break; // the tree is a heap now
      }
      currentIndex = parentIndex;
    }
  }

  /**
   * Remove the root from the heap
   */
  public E remove() {
    if (list.size() == 0) {
      return null;
    }

    E removedObject = list.get(0);
    list.set(0, list.get(list.size() - 1));
    list.remove(list.size() - 1);

    int currentIndex = 0;
    while (currentIndex < list.size()) {
      int leftChildIndex = 2 * currentIndex + 1;
      int rightChildIndex = 2 * currentIndex + 2;

      // Find the maximum between two children
      if (leftChildIndex >= list.size()) {
        break; // The tree is a heap
      }
      int maxIndex = leftChildIndex;
      if (rightChildIndex < list.size()) {
        if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
          maxIndex = rightChildIndex;
        }
      }

      // Swap if the current node is less than the maximum
      if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
        E temp = list.get(maxIndex);
        list.set(maxIndex, list.get(currentIndex));
        list.set(currentIndex, temp);
        currentIndex = maxIndex;
      }
      else {
        break; // The tree is a heap
      }
    }

    return removedObject;
  }

  /**
   * Get the number of nodes in the tree
   */
  public int getSize() {
    return list.size();
  }
}
