class GenericStack<E> {
  public final static int INITIAL_SIZE = 16;
  private E[] elements;
  private int size;

  /** Construct a stack with the default initial capacity */
  public GenericStack() {
    this(INITIAL_SIZE);
  }

  /** Construct a stack with the specified initial capacity */
  public GenericStack(int initialCapacity) {
    elements = (E[])new Object[initialCapacity];
  }

  /** Push a new element into the top of the stack */
  public E push(E value) {
    if (size >= elements.length) {
      E[] temp = (E[])new Object[elements.length * 2];
      System.arraycopy(elements, 0, temp, 0, elements.length);
      elements = temp;
    }

    return elements[size++] = value;
  }

  /** Return and remove the top element from the stack */
  public E pop() {
    return elements[--size];
  }

  /** Return the top element from the stack */
  public E peek() {
    return elements[size - 1];
  }

  /** Exercise03_21 whether the stack is empty */
  public boolean isEmpty() {
    return size == 0;
  }

  /** Return the number of elements in the stack */
  public int getSize() {
    return size;
  }
}
