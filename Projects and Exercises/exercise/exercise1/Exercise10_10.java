public class Exercise10_10 {
  public static void main(String[] args) {
    Queue queue = new Queue();
    for (int i = 0; i <= 20; i++)
      queue.enqueue(i);
    
    while (!queue.empty())
      System.out.print(queue.dequeue() + " ");
  }
}

class Queue {
  private int[] elements;
  private int size;

  /** Construct a queue with the default capacity 8 */
  public Queue() {
    elements = new int[8];
  }

  /** Add a new integer into the queue */
  public void enqueue(int value) {
    if (size >= elements.length) {
      int[] temp = new int[elements.length * 2];
      System.arraycopy(elements, 0, temp, 0, elements.length);
      elements = temp;
    }

    elements[size++] = value;
  }

  /** Remove and return an element from the queue */
  public int dequeue() {
    int v = elements[0];
    
    // Shift all elements in the array left
    for (int i = 0; i < size - 1; i++) {
      elements[i] = elements[i + 1];
    }
    
    size--;
    
    return v;
  }

  /** Test whether the queue is empty */
  public boolean empty() {
    return size == 0;
  }

  /** Return the number of elements in the queue */
  public int getSize() {
    return size;
  }
}
