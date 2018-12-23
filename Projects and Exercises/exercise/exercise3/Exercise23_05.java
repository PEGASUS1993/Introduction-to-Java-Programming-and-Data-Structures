import java.util.Comparator;

public class Exercise23_05 {
  public static void main(String[] args) {
    HeapWithComparator<GeometricObject> heap = new HeapWithComparator<GeometricObject>(
        new GeometricObjectComparator());

    heap.add(new Circle(3));
    heap.add(new Circle(5));
    heap.add(new Circle(1));

    while (heap.getSize() > 0) {
      System.out.println(heap.remove());
    }
    
    HeapWithComparator<String> heap2 = new HeapWithComparator<>();

    heap2.add("Macon");
    heap2.add("Atlanta");
    heap2.add("Savannah");

    while (heap2.getSize() > 0) {
      System.out.println(heap2.remove());
    }
  }
}

class HeapWithComparator<E> {
  private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
  private Comparator<? super E> comparator;

  /** Create a heap with the specified comparator */
  public HeapWithComparator() {
    this.comparator = (e1, e2) -> ((Comparable<E>)e1).compareTo(e2); // How do you do it?
  }
  
  /** Create a heap with the specified comparator */
  public HeapWithComparator(Comparator<? super E> comparator) {
    this.comparator = comparator;
  }

  /** Add a new object into the heap */
  public void add(E newObject) {
    list.add(newObject); // Append to the heap
    int currentIndex = list.size() - 1; // The index of the last node

    while (currentIndex > 0) {
      int parentIndex = (currentIndex - 1) / 2;
      // Swap if the current object is greater than its parent
      if (comparator.compare(list.get(currentIndex), list.get(parentIndex)) > 0) {
        E temp = list.get(currentIndex);
        list.set(currentIndex, list.get(parentIndex));
        list.set(parentIndex, temp);
      }
      else
        break; // the tree is a heap now

      currentIndex = parentIndex;
    }
  }

  /** Remove the root from the heap */
  public E remove() {
    if (list.size() == 0)
      return null;

    E removedObject = list.get(0);
    list.set(0, list.get(list.size() - 1));
    list.remove(list.size() - 1);

    int currentIndex = 0;
    while (currentIndex < list.size()) {
      int leftChildIndex = 2 * currentIndex + 1;
      int rightChildIndex = 2 * currentIndex + 2;

      // Find the maximum between two children
      if (leftChildIndex >= list.size())
        break; // The tree is a heap
      int maxIndex = leftChildIndex;
      if (rightChildIndex < list.size()) {
        if (comparator.compare(list.get(maxIndex), list.get(rightChildIndex)) < 0) {
          maxIndex = rightChildIndex;
        }
      }

      // Swap if the current node is less than the maximum
      if (comparator.compare(list.get(currentIndex), list.get(maxIndex)) < 0) {
        E temp = list.get(maxIndex);
        list.set(maxIndex, list.get(currentIndex));
        list.set(currentIndex, temp);
        currentIndex = maxIndex;
      }
      else
        break; // The tree is a heap
    }

    return removedObject;
  }

  /** Get the number of nodes in the tree */
  public int getSize() {
    return list.size();
  }
}
