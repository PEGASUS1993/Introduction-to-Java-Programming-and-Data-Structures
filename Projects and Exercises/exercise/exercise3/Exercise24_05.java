public class Exercise24_05 {
  public static void main(String[] args) {
    GenericQueue<String> queue = new GenericQueue<String>();
    queue.enqueue("Tom");
    queue.enqueue("George");
    queue.enqueue("Peter");
  }
}

// MyQueue.java: Implementing a queue using inheritance
class GenericQueue<E> extends java.util.LinkedList<E> {
  public void enqueue(E o) {
    this.addLast(o);
  }

  public Object dequeue() {
    return removeFirst();
  }

  public int getSize() {
    return size();
  }

  @Override
  public String toString() {
    return "Queue: " + toString();
  }
}
