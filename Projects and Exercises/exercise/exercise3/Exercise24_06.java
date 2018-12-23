import java.util.Comparator;

public class Exercise24_06 {
  public static void main(String[] args) {
    new Exercise24_06();
  }

  public Exercise24_06() {
    MyPriorityQueue<String> queue = new MyPriorityQueue<String>(
        (s1, s2) -> s1.compareToIgnoreCase(s2));

    queue.enqueue("Macon");
    queue.enqueue("Atlanta");
    queue.enqueue("Savannah");
    queue.enqueue("Augusta");
    queue.enqueue("Columbus");

    while (queue.getSize() > 0) {
      System.out.print(queue.dequeue() + " ");
    }

    MyPriorityQueue<String> queue1 = new MyPriorityQueue<String>(
        (s1, s2) -> s1.length() - s2.length());

    queue1.enqueue("ABC");
    queue1.enqueue("A");
    queue1.enqueue("AB");
    queue1.enqueue("ABCDE");
    queue1.enqueue("ABCD");

    System.out.println();
    while (queue1.getSize() > 0) {
      System.out.print(queue1.dequeue() + " ");
    }
  }

  static class MyPriorityQueue<E> {
    HeapWithComparator<E> heap;

    // Create a priority queue with default natural comparator
    public MyPriorityQueue(Comparator<? super E> comparator) {
      heap = new HeapWithComparator<E>(comparator);
    }

    public void enqueue(E newObject) {
      heap.add(newObject);
    }

    public Object dequeue() {
      return heap.remove();
    }

    public int getSize() {
      return heap.getSize();
    }
  }
}
