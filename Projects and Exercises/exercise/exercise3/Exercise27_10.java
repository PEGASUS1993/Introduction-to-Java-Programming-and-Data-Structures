import java.util.Scanner;
import java.util.*;

public class Exercise27_10 { 
  public static void main(String[] args) {
    MyHashSet<Double> set = new MyHashSet<>();
    MyArrayList<Double> list = new MyArrayList<>();
    
    for (int i = 0; i < 1000000; i++) {
      double value = Math.random() * 100000;
      set.add(value);
      list.add(value);
    }
    
    List<Double> list1 = new ArrayList<>();
    for (int i = 0; i < 1999999; i++) {
      list1.add(Math.random() * (1999999 + 1));
    }
    
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < 1999999; i++) {
      set.contains(list1.get(i));
    }
    long elaspedTime = System.currentTimeMillis() - startTime;
    System.out.println("The time for MyHashSet is " 
      + elaspedTime);
    
    startTime = System.currentTimeMillis();
    for (int i = 0; i < 1999999; i++) {
      list.contains(list1.get(i));
    }
    elaspedTime = System.currentTimeMillis() - startTime;
    System.out.println("The time for MyHashSet is " 
      + elaspedTime);
  }
  
  public static interface MyList<E> extends java.lang.Iterable<E> {
    /** Add a new element at the end of this list */
    public void add(E e);

    /** Add a new element at the specified index in this list */
    public void add(int index, E e);

    /** Clear the list */
    public void clear();

    /** Return true if this list contains the element */
    public boolean contains(E e);

    /** Return the element from this list at the specified index */
    public E get(int index);

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(E e);

    /** Return true if this list contains no elements */
    public boolean isEmpty();

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e);

    /** Remove the first occurrence of the element o from this list.
     *  Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public boolean remove(E e);

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index);

    /** Replace the element at the specified position in this list
     *  with the specified element and returns the new set. */
    public Object set(int index, E e);

    /** Return the number of elements in this list */
    public int size();
  }

  public abstract static class MyAbstractList<E> implements MyList<E> {
    protected int size = 0; // The size of the list

    /** Create a default list */
    protected MyAbstractList() {
    }

    /** Create a list from an array of objects */
    protected MyAbstractList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]);
    }

    @Override /** Add a new element at the end of this list */
    public void add(E e) {
      add(size, e);
    }

    @Override /** Return true if this list contains no elements */
    public boolean isEmpty() {
      return size == 0;
    }

    @Override /** Return the number of elements in this list */
    public int size() {
      return size;
    }

    @Override /** Remove the first occurrence of the element e 
     *  from this list. Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public boolean remove(E e) {
      if (indexOf(e) >= 0) {
        remove(indexOf(e));
        return true;
      }
      else
        return false;
    }
  }

  public static class MyArrayList<E> extends MyAbstractList<E> {
    public static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[])new Object[INITIAL_CAPACITY];

    /** Create a default list */
    public MyArrayList() {
    }

    /** Create a list from an array of objects */
    public MyArrayList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]); // Warning: don’t use super(objects)! 
    }

    @Override /** Add a new element at the specified index */
    public void add(int index, E e) {   
      ensureCapacity();

      // Move the elements to the right after the specified index
      for (int i = size - 1; i >= index; i--)
        data[i + 1] = data[i];

      // Insert new element to data[index]
      data[index] = e;

      // Increase size by 1
      size++;
    }

    /** Create a new larger array, double the current size + 1 */
    private void ensureCapacity() {
      if (size >= data.length) {
        E[] newData = (E[])(new Object[size * 2 + 1]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      }
    }

    @Override /** Clear the list */
    public void clear() {
      data = (E[])new Object[INITIAL_CAPACITY];
      size = 0;
    }

    @Override /** Return true if this list contains the element */
    public boolean contains(E e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return true;

      return false;
    }

    @Override /** Return the element at the specified index */
    public E get(int index) {
      checkIndex(index);
      return data[index];
    }

    private void checkIndex(int index) {
      if (index < 0 || index >= size)
        throw new IndexOutOfBoundsException
          ("Index: " + index + ", Size: " + size);
    }
    
    @Override /** Return the index of the first matching element 
     *  in this list. Return -1 if no match. */
    public int indexOf(E e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return i;

      return -1;
    }

    @Override /** Return the index of the last matching element 
     *  in this list. Return -1 if no match. */
    public int lastIndexOf(E e) {
      for (int i = size - 1; i >= 0; i--)
        if (e.equals(data[i])) return i;

      return -1;
    }

    @Override /** Remove the element at the specified position 
     *  in this list. Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index) {
      checkIndex(index);
      
      E e = data[index];

      // Shift data to the left
      for (int j = index; j < size - 1; j++)
        data[j] = data[j + 1];

      data[size - 1] = null; // This element is now null

      // Decrement size
      size--;

      return e;
    }

    @Override /** Replace the element at the specified position 
     *  in this list with the specified element. */
    public E set(int index, E e) {
      checkIndex(index);
      E old = data[index];
      data[index] = e;
      return old;
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder("[");

      for (int i = 0; i < size; i++) {
        result.append(data[i]);
        if (i < size - 1) result.append(", ");
      }

      return result.toString() + "]";
    }

    /** Trims the capacity to current size */
    public void trimToSize() {
      if (size != data.length) { 
        E[] newData = (E[])(new Object[size]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      } // If size == capacity, no need to trim
    }

    @Override /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
      return new ArrayListIterator();
    }
   
    private class ArrayListIterator 
        implements java.util.Iterator<E> {
      private int current = 0; // Current index 

      @Override
      public boolean hasNext() {
        return (current < size);
      }

      @Override
      public E next() {
        return data[current++];
      }

      @Override
      public void remove() {
        MyArrayList.this.remove(current);
      }
    }
  }

  public static interface MySet<E> extends java.lang.Iterable<E> {
    /** Remove all elements from this set */
    public void clear();
    
    /** Return true if the element is in the set */
    public boolean contains(E e);
    
    /** Add an element to the set */
    public boolean add(E e);

    /** Remove the element from the set */
    public boolean remove(E e);

    /** Return true if the set contains no elements */
    public boolean isEmpty();

    /** Return the number of elements in the set */
    public int size();
  }

  public static class MyHashSet<E> implements MySet<E> {
    // Define the default hash table size. Must be a power of 2
    private static int DEFAULT_INITIAL_CAPACITY = 4;
    
    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30; 
    
    // Current hash table capacity. Capacity is a power of 2
    private int capacity;
    
    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f; 

    // Specify a load factor threshold used in the hash table
    private float loadFactorThreshold; 
    
    // The number of elements in the set
    private int size = 0; 
    
    // Hash table is an array with each cell that is a linked list
    private LinkedList<E>[] table;

    /** Construct a set with the default capacity and load factor */
    public MyHashSet() {  
      this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);    
    }
    
    /** Construct a set with the specified initial capacity and 
     * default load factor */
    public MyHashSet(int initialCapacity) { 
      this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);    
    }
    
    /** Construct a set with the specified initial capacity 
     * and load factor */
    public MyHashSet(int initialCapacity, float loadFactorThreshold) { 
      if (initialCapacity > MAXIMUM_CAPACITY)
        this.capacity = MAXIMUM_CAPACITY;
      else
        this.capacity = trimToPowerOf2(initialCapacity);
      
      this.loadFactorThreshold = loadFactorThreshold;    
      table = new LinkedList[capacity];
    }
    
    @Override /** Remove all elements from this set */ 
    public void clear() {
      size = 0;
      removeElements();
    }

    @Override /** Return true if the element is in the set */
    public boolean contains(E e) {
      int bucketIndex = hash(e.hashCode());
      if (table[bucketIndex] != null) {
        LinkedList<E> bucket = table[bucketIndex]; 
        for (E element: bucket)
          if (element.equals(e)) 
            return true;
      }
      
      return false;
    }
    
    @Override /** Add an element to the set */
    public boolean add(E e) {
      if (contains(e)) // Duplicate element not stored
        return false;
      
      if (size > capacity * loadFactorThreshold) {
        if (capacity == MAXIMUM_CAPACITY)
          throw new RuntimeException("Exceeding maximum capacity");
      
        rehash();
      }
      
      int bucketIndex = hash(e.hashCode());
      
      // Create a linked list for the bucket if it is not created
      if (table[bucketIndex] == null) {
        table[bucketIndex] = new LinkedList<E>();
      }

      // Add e to hashTable[index]
      table[bucketIndex].add(e);

      size++; // Increase size
      
      return true;
    }

    @Override /** Remove the element from the set */
    public boolean remove(E e) {
      if (!contains(e))
        return false;
      
      int bucketIndex = hash(e.hashCode());
      
      // Create a linked list for the bucket if it is not created
      if (table[bucketIndex] != null) {
        LinkedList<E> bucket = table[bucketIndex]; 
        for (E element: bucket)
          if (e.equals(element)) {
            bucket.remove(element);
            break;
          }
      }

      size--; // Decrease size
      
      return true;
    }

    @Override /** Return true if the set contains no elements */
    public boolean isEmpty() {
      return size == 0;
    }

    @Override /** Return the number of elements in the set */
    public int size() {
      return size;
    }

    @Override /** Return an iterator for the elements in this set */
    public java.util.Iterator<E> iterator() {
      return new MyHashSetIterator(this);
    }
    
    /** Inner class for iterator */
    private class MyHashSetIterator implements java.util.Iterator<E> {
      // Store the elements in a list
      private java.util.ArrayList<E> list;
      private int current = 0; // Point to the current element in list
      private MyHashSet<E> set;
      
      /** Create a list from the set */
      public MyHashSetIterator(MyHashSet<E> set) {
        this.set = set;
        list = setToList();
      }

      @Override /** Next element for traversing? */
      public boolean hasNext() {
        if (current < list.size())
          return true;

        return false;
      }

      @Override /** Get current element and move cursor to the next */
      public E next() {
        return list.get(current++);
      }

      @Override /** Remove the current element and refresh the list */
      public void remove() {
        // Delete the current element from the hash set
        set.remove(list.get(current)); 
        list.remove(current); // Remove current element from the list
      }
    }  
    
    /** Hash function */
    private int hash(int hashCode) {
      return supplementalHash(hashCode) & (capacity - 1);
    }
    
    /** Ensure the hashing is evenly distributed */
    private static int supplementalHash(int h) {
      h ^= (h >>> 20) ^ (h >>> 12);
      return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /** Return a power of 2 for initialCapacity */
    private int trimToPowerOf2(int initialCapacity) {
      int capacity = 1;
      while (capacity < initialCapacity) {
        capacity <<= 1;
      }
      
      return capacity;
    }
    
    /** Remove all e from each bucket */
    private void removeElements() {
      for (int i = 0; i < capacity; i++) {
        if (table[i] != null) {
          table[i].clear();
        }
      }
    }
    
    /** Rehash the set */
    private void rehash() {
      java.util.ArrayList<E> list = setToList(); // Copy to a list
      capacity <<= 1; // Double capacity      
      table = new LinkedList[capacity]; // Create a new hash table
      size = 0; // Reset size 
      
      for (E element: list) {
        add(element); // Add from the old table to the new table
      }
    }

    /** Copy elements in the hash set to an array list */
    private java.util.ArrayList<E> setToList() {
      java.util.ArrayList<E> list = new java.util.ArrayList<E>();
      
      for (int i = 0; i < capacity; i++) {
        if (table[i] != null) {
          for (E e: table[i]) {
            list.add(e);
          }
        }
      }  
      
      return list;
    }

    @Override
    public String toString() {
      java.util.ArrayList<E> list = setToList();
      StringBuilder builder = new StringBuilder("[");
      
      // Add the elements except the last one to the string builder
      for (int i = 0; i < list.size() - 1; i++) {
        builder.append(list.get(i) + ", ");
      }
      
      // Add the last element in the list to the string builder
      if (list.size() == 0)
        builder.append("]");
      else
        builder.append(list.get(list.size() - 1) + "]");
      
      return builder.toString();
    }
  }

  
}