import java.util.Comparator;

public class Exercise23_09 {
  public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    heapSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }

    System.out.println();
    Circle[] list1 = {new Circle(2), new Circle(3), new Circle(2),
      new Circle(5), new Circle(6), new Circle(1), new Circle(2),
      new Circle(3), new Circle(14), new Circle(12)};
    heapSort(list1, new GeometricObjectComparator());
    for (int i = 0; i < list1.length; i++) {
      System.out.print(list1[i] + " ");
    }
  }

  public static<E extends Comparable<E>>void heapSort(E[] list) {
    HeapWithComparator<E> heap = new HeapWithComparator<E>(); // Create a Heap

    // Add elements to the heap
    for (int i = 0; i < list.length; i++) {
      heap.add(list[i]);
    }

    // Remove elements from the heap
    for (int i = list.length - 1; i >= 0; i--) {
      list[i] = heap.remove();
    }
  }

  public static<E>void heapSort(E[] list,
    Comparator<? super E>comparator) {
    // Create a Heap
    HeapWithComparator<E> heap = new HeapWithComparator<E>(comparator);

    // Add elements to the heap
    for (int i = 0; i < list.length; i++) {
      heap.add(list[i]);
    }

    // Remove elements from the heap
    for (int i = list.length - 1; i >= 0; i--) {
      list[i] = heap.remove();
    }
  }
}
